package com.aplazo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutputResponse{
   Integer payment_number;
   Double payment_amount;
   Double pending_amount;
   String payment_date;
}
