package com.Neoflex.conveyor.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChekPassportIssueDateValidator.class )
public @interface ChekPassportIssueDate {

    public String message () default "Passport issue date must be at least 14 years old from today";
    public Class<?>[] groups () default {};
    public Class <? extends Payload> [] payload () default {};
}
