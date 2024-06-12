package com.app.ClinicaMedica.Schedule;

import Enum.WeekDays;
import com.app.ClinicaMedica.Constraints.IsTimeBetweenExistent;
import com.app.ClinicaMedica.Constraints.StartHourBeforeEndHour;
import com.app.ClinicaMedica.Doctor.Doctor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@StartHourBeforeEndHour
@IsTimeBetweenExistent
public class Schedule {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "schedule_sequence")
    @SequenceGenerator(name = "schedule_sequence", sequenceName = "sch_seq")
    private Long idSchedule;

    @NotNull
    @Enumerated(EnumType.STRING)
    private WeekDays weekDay;

    @NotNull
    private LocalTime startHour;

    @NotNull
    private LocalTime endHour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crm", referencedColumnName = "crm", nullable = false)
    private Doctor doctor;
}
