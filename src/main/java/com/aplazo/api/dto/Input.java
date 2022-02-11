package com.aplazo.api.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Data
public class Input{

    @DecimalMin( value = "1.00",message = "Amount min $1.00")
    @DecimalMax( value = "999999.00", message = "Amount max $999,999.00")
    private Double amount;

    @Min( value= 4, message = "Terms min 4")
    @Max( value = 52,message = "Terms max 52")
    private Integer terms;

    @Min( value= 0, message = "Rate min 0")
    @Max( value = 100,message = "Rate max 100")
    private Double rate;

    @Id
    @GeneratedValue
    private Long id;
}
