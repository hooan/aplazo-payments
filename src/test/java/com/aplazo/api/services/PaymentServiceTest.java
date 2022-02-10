package com.aplazo.api.services;


import com.aplazo.api.dto.Input;
import com.aplazo.api.dto.Output;
import com.aplazo.api.dto.OutputResponse;
import com.aplazo.api.repositories.InputRepository;
import com.aplazo.api.repositories.OutputRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {
    @InjectMocks
    PaymentsService service;

    @Mock
    InputRepository inputRep;

    @Mock
    OutputRepository outpuRep;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getPaymentsTest(){
        Input input = new Input();
            input.setAmount(1000.0);
            input.setRate(2.0);
            input.setTerms(14);
        when(inputRep.save(input)).thenReturn(input);
        when(outpuRep.save(any(Output.class))).thenReturn( new Output());

        List<OutputResponse> list = service.getPayments(input);

        assertEquals(input.getTerms(),list.size());
        assertEquals(Double.valueOf(0),list.get(list.size()-1).getPending_amount());
        verify(outpuRep,times(input.getTerms())).save(any(Output.class));
    }
}
