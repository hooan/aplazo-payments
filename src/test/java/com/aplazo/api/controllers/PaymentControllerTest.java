package com.aplazo.api.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.aplazo.api.dto.Input;
import com.aplazo.api.dto.OutputResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(PaymentsController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private PaymentsController service;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {

        Input input = new Input();
            input.setAmount(1000.0);
            input.setRate(2.0);
            input.setTerms(14);
        List<OutputResponse> output = Arrays.asList(new OutputResponse(0,0.0,0.0, Instant.now().toString()));
        when(service.getPayments(input)).thenReturn(output);
        mockMvc.perform(post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(input)))
                        .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void greetingShouldReturnInvalidMessageFromService() throws Exception {

        Input input = new Input();
        input.setAmount(00.0);
        input.setRate(1.0);
        input.setTerms(100);
        this.mockMvc.perform(post("/payments").content(mapper.writeValueAsString(input)).contentType("application/json")).andExpect(status().is4xxClientError());
    }

}
