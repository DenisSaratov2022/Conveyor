package com.Neoflex.conveyor.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class ChekDateOfBirthValidator implements ConstraintValidator<ChekDateOfBirth, LocalDate> {

    @Override
    public void initialize(ChekDateOfBirth constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        Period period = Period.between(LocalDate.now(), value);
        int age = period.getYears();
        if (age<=-18&&age>=-60) {
            return true;
        }
        else {
            return false;
        }
    }
}
