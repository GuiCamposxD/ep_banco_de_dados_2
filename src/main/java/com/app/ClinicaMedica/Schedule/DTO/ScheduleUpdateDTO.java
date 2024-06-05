package com.app.ClinicaMedica.Schedule.DTO;

import com.app.ClinicaMedica.Doctor.DTO.DoctorDTO;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Enum.WeekDays;
import com.app.ClinicaMedica.Schedule.Schedule;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@ToString
public class ScheduleUpdateDTO {
    @NotEmpty(message = "Week Day is mandatory")
    private WeekDays weekDay;

    @NotEmpty(message = "Start Hour is mandatory")
    private LocalTime startHour;

    @NotEmpty(message = "End Hour is mandatory")
    private LocalTime endHour;

    @NotEmpty(message = "Doctor is mandatory")
    private String crm;

    public void update(Schedule schedule, Doctor doctor) {
        schedule.setWeekDay(this.getWeekDay());
        schedule.setStartHour(this.getStartHour());
        schedule.setEndHour(this.getEndHour());
        schedule.setDoctor(doctor);
    }
}
