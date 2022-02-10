package com.aplazo.api.services;

import com.aplazo.api.dto.Input;
import com.aplazo.api.dto.Output;
import com.aplazo.api.dto.OutputResponse;
import com.aplazo.api.repositories.InputRepository;
import com.aplazo.api.repositories.OutputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentsService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    OutputRepository outputRepository;

    public List<OutputResponse> getPayments(Input input){
        Input response = inputRepository.save(input);
        Double realAmount = input.getAmount() * (1 + (input.getRate() * input.getTerms()));
        List<OutputResponse> result = new ArrayList<>();
        Double pendingAmount = realAmount;
        Double paymentAmount = realAmount/input.getTerms();
        LocalDate payDate = LocalDate.now();
        for (int i = 1; i <= input.getTerms(); i++) {
            payDate = payDate.plusWeeks(1);
            if(pendingAmount - paymentAmount < 0){
                paymentAmount = pendingAmount;
            }
            pendingAmount = pendingAmount - paymentAmount;
            result.add(new OutputResponse(i,paymentAmount,pendingAmount,payDate.toString()));
            outputRepository.save(new Output(i,paymentAmount,pendingAmount,payDate.toString(),response.getId()));
        }
        return result;
    }
}
