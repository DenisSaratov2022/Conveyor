package com.Neoflex.conveyor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentDTO {

    private EmploymentStatus employmentStatus;
    @NotBlank
    @Size(min = 12, max = 12)
    private String employerINN;
    @Min(10000)
    private BigDecimal salary;
    private Position position;
    @Min(0)
    private Integer workExperienceTotal;
    @Min(0)
    private Integer workExperienceCurrent;

}
