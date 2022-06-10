package com.Neoflex.conveyor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanOfferDTO {

    @Min(1)
    private Long applicationId;
    @Min(10000)
    @Max(100000000)
    private BigDecimal requestedAmount;
    @Min(10000)
    @Max(100000000)
    private BigDecimal totalAmount;
    @Min(6)
    @Max(60)
    private Integer term;
    @Min(100)
    @Max(100000)
    private BigDecimal monthlyPayment;
    @Min(1)
    @Max(25)
    private BigDecimal rate;
    private boolean isInsuranceEnabled;
    private boolean isSalaryClient;
}
