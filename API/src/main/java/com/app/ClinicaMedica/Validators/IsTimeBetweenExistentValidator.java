package com.app.ClinicaMedica.Validators;


import com.app.ClinicaMedica.Constraints.ExistsTimePeriod;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Schedule.DTO.ScheduleDTO;
import com.app.ClinicaMedica.Schedule.Schedule;
import com.app.ClinicaMedica.Schedule.ScheduleService;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidator;

import java.util.List;

public class ExistsTimePeriodValidator implements ConstraintValidator<ExistsTimePeriod, Schedule> {

    @Override
    public void initialize(ExistsTimePeriod constraintAnnotation){
    }

    @Override
    public boolean isValid(Schedule schedule, ConstraintValidatorContext context){

        Doctor doctor = schedule.getDoctor();
        List<Schedule> schedules = doctor.getSchedules();

        for (Schedule s:schedules){
            if (schedule.getStartHour().isBefore(s.getEndHour()) && schedule.getEndHour().isAfter(s.getStartHour()) && schedule.getWeekDay().equals(s.getWeekDay())){
                return false;
            }
        }
        return true;
    }
}
