package com.app.ClinicaMedica.Validators;

import com.app.ClinicaMedica.Constraints.StartHourBeforeEndHour;
import com.app.ClinicaMedica.Schedule.Schedule;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartHourBeforeEndHourValidator implements ConstraintValidator<StartHourBeforeEndHour, TimeInterval> {
    @Override
    public void initialize(StartHourBeforeEndHour constraintAnnotation) {
    }

    @Override
    public boolean isValid(TimeInterval timeInterval, ConstraintValidatorContext context) {
        if (timeInterval.getStartHour() == null || timeInterval.getEndHour() == null) {
            return true;
        }

        return timeInterval.getStartHour().isBefore(timeInterval.getEndHour());
    }
}
