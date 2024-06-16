package com.app.ClinicaMedica.Doctor.DTO;

import com.app.ClinicaMedica.Doctor.Doctor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class DoctorCreateDTO {
    @NotEmpty(message = "CRM is mandatory")
    private String crm;

    @NotEmpty(message = "Doctor name is mandatory")
    private String doctorName;

    @NotEmpty(message = "Doctor phone is mandatory")
    @Size(min = 8, max = 11)
    private String doctorPhone;

    @NotNull(message = "Percentage is mandatory")
    @Positive(message = "Percentage must be a positive number")
    private Float percentage;

    private Set<Long> idSpecialities;

    public Doctor converter() {
        Doctor doctor = new Doctor();
        doctor.setCrm(this.crm);
        doctor.setDoctorName(this.doctorName);
        doctor.setDoctorPhone(this.doctorPhone);
        doctor.setPercentage(this.percentage);

        return doctor;
    }
}
