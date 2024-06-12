package com.app.ClinicaMedica.Constraints;

import com.app.ClinicaMedica.Validators.ExistsTimePeriodValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = ExistsTimePeriodValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsTimePeriod {
    String message() default "Time period is between existent time period";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
