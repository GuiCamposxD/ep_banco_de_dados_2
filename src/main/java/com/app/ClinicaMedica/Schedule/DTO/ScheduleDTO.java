package com.app.ClinicaMedica.Schedule.DTO;

import com.app.ClinicaMedica.Doctor.DTO.DoctorDTO;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Enum.WeekDays;
import com.app.ClinicaMedica.Schedule.Schedule;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScheduleDTO {
    @NotEmpty(message = "Schedule id is mandatory")
    private final Long idSchedule;

    @NotEmpty(message = "Week Day is mandatory")
    private final WeekDays weekDay;

    @NotEmpty(message = "Start Hour is mandatory")
    private final LocalTime startHour;

    @NotEmpty(message = "End Hour is mandatory")
    private final LocalTime endHour;

    @NotEmpty(message = "Doctor is mandatory")
    private final DoctorDTO doctor;

    public ScheduleDTO(Schedule schedule) {
        this.idSchedule = schedule.getIdSchedule();
        this.weekDay = schedule.getWeekDay();
        this.startHour = schedule.getStartHour();
        this.endHour = schedule.getEndHour();
        this.doctor = new DoctorDTO(schedule.getDoctor());
    }

    public static List<ScheduleDTO> converter(List<Schedule> schedules) {
        return schedules.stream().map(ScheduleDTO::new).collect(Collectors.toList());
    }
}
