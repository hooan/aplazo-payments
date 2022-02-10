package com.aplazo.api.controllers;

import com.aplazo.api.dto.Input;
import com.aplazo.api.dto.OutputResponse;
import com.aplazo.api.services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController("/api/v1")
public class PaymentsController {

    @Autowired
    PaymentsService paymentsService;

    @PostMapping(value = "/payments", consumes = "application/json",produces = "application/json")
    public List<OutputResponse> getPayments(@Valid @RequestBody Input request){

        return paymentsService.getPayments(request);
    }
}
