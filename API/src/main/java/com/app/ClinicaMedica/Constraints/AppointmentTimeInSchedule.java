package com.app.ClinicaMedica.Constraints;
import com.app.ClinicaMedica.Validators.AppointmentTimeInScheduleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AppointmentTimeInScheduleValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AppointmentTimeInSchedule {
    String message() default "Doctor's schedule do not include this appointment time period";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}