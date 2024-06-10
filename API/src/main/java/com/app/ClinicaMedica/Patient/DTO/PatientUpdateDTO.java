package com.app.ClinicaMedica.Patient.DTO;

import Enum.Gender;
import com.app.ClinicaMedica.Patient.Patient;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatientUpdateDTO {
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
    private Gender gender;

    public void update(Patient patient) {
        patient.setPatientName(this.getPatientName());
        patient.setPatientPhone(this.getPatientPhone());
        patient.setAddress(this.getAddress());
        patient.setAge(this.getAge());
        patient.setGender(this.getGender());
    }
}
