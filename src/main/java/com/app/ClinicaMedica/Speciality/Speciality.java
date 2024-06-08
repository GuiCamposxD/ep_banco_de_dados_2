package com.app.ClinicaMedica.Speciality;

import com.app.ClinicaMedica.Appointment.Appointment;
import com.app.ClinicaMedica.ExertSpeciality.ExertSpeciality;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Speciality {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idSpeciality;

    @NotEmpty
    private String specialityName;

    @NotNull
    private Integer index;

    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ExertSpeciality> exertSpecialities;

    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments;
}
