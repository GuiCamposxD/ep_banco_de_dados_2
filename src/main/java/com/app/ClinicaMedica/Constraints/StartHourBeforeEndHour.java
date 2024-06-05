package com.app.ClinicaMedica.Constraints;

import com.app.ClinicaMedica.Validators.StartHourBeforeEndHourValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StartHourBeforeEndHourValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StartHourBeforeEndHour {
    String message() default "Start Hour must be before End Hour";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
