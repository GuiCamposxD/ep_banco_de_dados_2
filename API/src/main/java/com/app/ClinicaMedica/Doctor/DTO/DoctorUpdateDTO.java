package com.app.ClinicaMedica.Doctor.DTO;

import com.app.ClinicaMedica.Doctor.Doctor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
public class DoctorUpdateDTO {
    @NotEmpty(message = "Doctor name is mandatory")
    private String doctorName;

    @NotEmpty(message = "Doctor phone is mandatory")
    @Size(min = 8, max = 11)
    private String doctorPhone;

    @NotNull(message = "Percentage is mandatory")
    @Positive(message = "Percentage must be a positive number")
    private Float percentage;

    public void update(Doctor doctor) {
        doctor.setDoctorName(this.getDoctorName());
        doctor.setDoctorPhone(this.getDoctorPhone());
        doctor.setPercentage(this.getPercentage());
    }
}
