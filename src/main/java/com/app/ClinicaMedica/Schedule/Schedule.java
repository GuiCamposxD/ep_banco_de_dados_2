package com.app.ClinicaMedica.Schedule;

import com.app.ClinicaMedica.Constraints.StartHourBeforeEndHour;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Enum.WeekDays;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@StartHourBeforeEndHour
public class Schedule {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID, generator="schedule_sequence")
    @SequenceGenerator(name="schedule_sequence", sequenceName="sch_seq")
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
    @JsonBackReference
    private Doctor doctor;
}
