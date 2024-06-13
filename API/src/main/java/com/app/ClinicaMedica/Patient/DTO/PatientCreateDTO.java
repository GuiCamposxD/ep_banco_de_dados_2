package com.app.ClinicaMedica.Patient.DTO;

import Enum.Genders;
import com.app.ClinicaMedica.Patient.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatientCreateDTO {
    @NotEmpty(message = "CPF is mandatory")
    @Column(unique = true)
    @Size(min = 11, max = 11)
    private String cpf;

    @NotEmpty(message = "Patient Name is mandatory")
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

    public Patient converter() {
        Patient patient = new Patient();
        patient.setCpf(this.cpf);
        patient.setPatientName(this.patientName);
        patient.setPatientPhone(this.patientPhone);
        patient.setAddress(this.address);
        patient.setAge(this.age);
        patient.setGender(this.gender);

        return patient;
    }
}
