package com.app.ClinicaMedica.Doctor.DTO;

import com.app.ClinicaMedica.Doctor.Doctor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DoctorDTO {
    @NotEmpty(message = "CRM is mandatory")
    private final String crm;

    @NotEmpty(message = "Doctor name is mandatory")
    private final String doctorName;

    @NotEmpty(message = "Doctor phone is mandatory")
    private final String doctorPhone;

    @NotNull(message = "Percentage is mandatory")
    @Positive(message = "Percentage must be a positive number")
    private final Float percentage;

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
