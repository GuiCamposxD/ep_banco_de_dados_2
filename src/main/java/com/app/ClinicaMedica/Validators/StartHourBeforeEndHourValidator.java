package com.app.ClinicaMedica.Validators;

import com.app.ClinicaMedica.Constraints.StartHourBeforeEndHour;
import com.app.ClinicaMedica.Schedule.Schedule;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartHourBeforeEndHourValidator implements ConstraintValidator<StartHourBeforeEndHour, Schedule> {
    @Override
    public void initialize(StartHourBeforeEndHour constraintAnnotation) {
    }

    @Override
    public boolean isValid(Schedule schedule, ConstraintValidatorContext context) {
        if (schedule.getStartHour() == null || schedule.getEndHour() == null) {
            return true;
        }

        return schedule.getStartHour().isBefore(schedule.getEndHour());
    }
}
