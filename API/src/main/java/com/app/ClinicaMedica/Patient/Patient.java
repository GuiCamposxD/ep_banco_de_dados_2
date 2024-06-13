package com.app.ClinicaMedica.Patient;

import com.app.ClinicaMedica.Appointment.Appointment;
import Enum.Genders;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "patient_sequence")
    @SequenceGenerator(name = "patient_sequence", sequenceName = "pat_seq")
    private Long idPatient;

    @NotEmpty
    @Column(unique = true)
    @Size(min = 11, max = 11)
    private String cpf;

    @NotEmpty
    private String patientName;

    private String patientPhone;

    private String address;

    @NotNull
    @Max(value = 125)
    @Min(value = 0)
    private Integer age;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genders gender;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments;
}
