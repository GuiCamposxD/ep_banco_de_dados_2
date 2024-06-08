package com.app.ClinicaMedica.Schedule.DTO;

import com.app.ClinicaMedica.Doctor.Doctor;
import Enum.WeekDays;
import com.app.ClinicaMedica.Schedule.Schedule;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class ScheduleCreateDTO {
    @NotNull(message = "Week Day is mandatory")
    @Enumerated(EnumType.STRING)
    private WeekDays weekDay;

    @NotNull(message = "Start Hour Day is mandatory")
    private LocalTime startHour;

    @NotNull(message = "End Hour is mandatory")
    private LocalTime endHour;

    @NotNull(message = "Crm is mandatory")
    private String crm;

    public Schedule converter(Doctor doctor) {
        Schedule schedule = new Schedule();

        schedule.setWeekDay(this.weekDay);
        schedule.setStartHour(this.startHour);
        schedule.setEndHour(this.endHour);
        schedule.setDoctor(doctor);

        return schedule;
    }
}
