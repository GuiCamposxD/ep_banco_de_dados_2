package com.app.ClinicaMedica.Doctor;

import com.app.ClinicaMedica.Schedule.Schedule;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    private String crm;

    @NotEmpty
    private String doctorName;

    @NotEmpty
    private String doctorPhone;

    @Positive
    @NotNull
    private Float percentage;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Schedule> schedules;
}
