package com.app.ClinicaMedica.Doctor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DoctorDTO {
    @NotEmpty(message = "CRM is mandatory")
    private String crm;

    @NotEmpty(message = "Doctor name is mandatory")
    private String doctorName;

    @NotEmpty(message = "Doctor phone is mandatory")
    private String doctorPhone;

    @NotNull(message = "Percentage is mandatory")
    @Positive(message = "Percentage must be a positive number")
    private Float percentage;

    public DoctorDTO(Doctor doctor) {
        this.crm = doctor.getCrm();
        this.doctorName = doctor.getDoctorName();
        this.doctorPhone = doctor.getDoctorPhone();
        this.percentage = doctor.getPercentage();
    }

    public static List<DoctorDTO> converter(List<Doctor> doctors) {
        return doctors.stream().map(DoctorDTO::new).collect(Collectors.toList());
    }
}
