package com.app.ClinicaMedica.Validators;


import com.app.ClinicaMedica.Constraints.IsTimeBetweenExistent;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Schedule.Schedule;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class IsTimeBetweenExistentValidator implements ConstraintValidator<IsTimeBetweenExistent, Schedule> {
    @Override
    public void initialize(IsTimeBetweenExistent constraintAnnotation) {
    }

    @Override
    public boolean isValid(Schedule schedule, ConstraintValidatorContext context) {

        Doctor doctor = schedule.getDoctor();
        List<Schedule> schedules = doctor.getSchedules();

        for (Schedule s:schedules) {
            if (
                schedule.getStartHour().isBefore(s.getEndHour())
                && schedule.getEndHour().isAfter(s.getStartHour())
                && schedule.getWeekDay().equals(s.getWeekDay())
            ) return false;
        }

        return true;
    }
}
