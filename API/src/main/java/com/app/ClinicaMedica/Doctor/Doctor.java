package com.app.ClinicaMedica.Doctor;

import com.app.ClinicaMedica.Appointment.Appointment;
import com.app.ClinicaMedica.ExertSpeciality.ExertSpeciality;
import com.app.ClinicaMedica.Schedule.Schedule;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

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
    @Size(min = 8, max = 11)
    private String doctorPhone;

    @Positive
    @NotNull
    private Float percentage;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExertSpeciality> exertSpecialities;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments;
}
