package com.Neoflex.conveyor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationRequestDTO {

    @Min(10000)
    @Max(100000000)
    private BigDecimal amount;
    @Min(6)
    @Max(60)
    private Integer term;
    @NotBlank
    @Size(min = 2, max = 30)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 30)
    private String lastName;
    @Size(min = 2, max = 30)
    private String middleName;
    @Email
    private String email;
    private LocalDate birthdate;
    @NotBlank
    @Size(min = 4, max = 4)
    private String passportSeries;
    @NotBlank
    @Size(min = 6, max = 6)
    private String passportNumber;

}
