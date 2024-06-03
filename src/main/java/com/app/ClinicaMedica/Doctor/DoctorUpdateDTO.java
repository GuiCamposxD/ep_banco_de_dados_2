package com.app.ClinicaMedica.Doctor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DoctorUpdateDTO {
    @NotEmpty(message = "Doctor name is mandatory")
    private String doctorName;

    @NotEmpty(message = "Doctor phone is mandatory")
    private String doctorPhone;

    @NotNull(message = "Percentage is mandatory")
    @Positive(message = "Percentage must be a positive number")
    private Float percentage;

    public void update(Doctor doctor) {
        doctor.setDoctorName(doctor.getDoctorName());
        doctor.setDoctorPhone(doctor.getDoctorPhone());
        doctor.setPercentage(doctor.getPercentage());
    }
}
