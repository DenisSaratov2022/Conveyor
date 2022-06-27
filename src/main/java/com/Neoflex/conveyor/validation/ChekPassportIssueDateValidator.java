package com.Neoflex.conveyor.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class ChekPassportIssueDateValidator implements ConstraintValidator<ChekPassportIssueDate, LocalDate> {

    @Override
    public void initialize(ChekPassportIssueDate constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        Period period = Period.between(LocalDate.now(), value);
        int dateIssue = period.getYears();
        if (dateIssue<=-14&&dateIssue>=-46) {
            return true;
        }
        else {
            return false;
        }
    }
}
