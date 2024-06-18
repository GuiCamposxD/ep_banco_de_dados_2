package com.app.ClinicaMedica.Constraints;

import com.app.ClinicaMedica.Validators.AppointmentDontExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AppointmentDontExistValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AppointmentDontExist {
    String message() default "Appointment Already Exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}