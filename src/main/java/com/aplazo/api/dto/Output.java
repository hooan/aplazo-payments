package com.aplazo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Output{

    private Integer payment_number;
    private Double payment_amount;
    private Double pending_amount;
    private String payment_date;
    private Long input_id;

    @Id
    @GeneratedValue
    private Long id;

    public Output(int paymentNumber, Double paymentAmount, Double pendingAmount, String paymentDate, Long inputId) {
        this.payment_number = paymentNumber;
        this.payment_amount = paymentAmount;
        this.pending_amount = pendingAmount;
        this.payment_date = paymentDate;
        this.input_id = inputId;
    }
}
