package com.app.ClinicaMedica.Patient.DTO;

import Enum.Genders;
import com.app.ClinicaMedica.Patient.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PatientDTO {
    @Id
    private final Long idPatient;

    @NotEmpty(message = "CPF is mandatory")
    @Column(unique = true)
    @Size(min = 11, max = 11)
    private final String cpf;

    @NotEmpty(message = "Patient Name is mandatory")
    private final String patientName;

    private final String patientPhone;

    private final String address;

    @NotNull
    @Max(value = 125)
    @Min(value = 0)
    private final Integer age;

    @NotNull
    @Enumerated(EnumType.STRING)
    private final Genders gender;

    public PatientDTO(Patient patient) {
        this.idPatient = patient.getIdPatient();
        this.cpf = patient.getCpf();
        this.patientName = patient.getPatientName();
        this.patientPhone = patient.getPatientPhone();
        this.address = patient.getAddress();
        this.age = patient.getAge();
        this.gender = patient.getGender();
    }

    public static List<PatientDTO> converter(List<Patient> patients) {
        return patients.stream().map(PatientDTO::new).collect(Collectors.toList());
    }
}
