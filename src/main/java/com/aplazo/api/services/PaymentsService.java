package com.aplazo.api.services;

import com.aplazo.api.dto.Input;
import com.aplazo.api.dto.Output;
import com.aplazo.api.dto.OutputResponse;
import com.aplazo.api.repositories.InputRepository;
import com.aplazo.api.repositories.OutputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        Double realAmount = input.getAmount() * (1 + (input.getRate()/100));
        List<OutputResponse> result = new ArrayList<>();
        BigDecimal pendingAmount = new BigDecimal(realAmount).setScale(2);
        BigDecimal paymentAmount = new BigDecimal(realAmount/input.getTerms()).setScale(2,RoundingMode.UP);
        LocalDate payDate = LocalDate.now();
        for (int i = 1; i <= input.getTerms(); i++) {
            payDate = payDate.plusWeeks(1);
            if(pendingAmount.subtract(paymentAmount).doubleValue() < 0.0){
                paymentAmount = pendingAmount;
            }
            pendingAmount = pendingAmount.subtract(paymentAmount);
            result.add(new OutputResponse(i,paymentAmount.doubleValue(),pendingAmount.doubleValue(),payDate.toString()));
            outputRepository.save(new Output(i,paymentAmount.doubleValue(),pendingAmount.doubleValue(),payDate.toString(),response.getId()));
        }
        return result;
    }
}
