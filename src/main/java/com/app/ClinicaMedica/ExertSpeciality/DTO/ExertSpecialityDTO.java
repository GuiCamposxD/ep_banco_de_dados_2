package com.app.ClinicaMedica.ExertSpeciality.DTO;

import com.app.ClinicaMedica.Doctor.DTO.DoctorDTO;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.ExertSpeciality.ExertSpeciality;
import com.app.ClinicaMedica.Speciality.DTO.SpecialityDTO;
import com.app.ClinicaMedica.Speciality.Speciality;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ExertSpecialityDTO {
    @NotEmpty(message = "CRM is mandatory")
    private final DoctorDTO doctor;

    @NotNull(message = "Id Speciality is mandatory")
    private final SpecialityDTO speciality;

    public ExertSpecialityDTO(ExertSpeciality exertSpeciality) {
        this.doctor = new DoctorDTO(exertSpeciality.getDoctor());
        this.speciality = new SpecialityDTO(exertSpeciality.getSpeciality());
    }

    public static List<ExertSpecialityDTO> converter(List<ExertSpeciality> exertSpecialities) {
        return exertSpecialities.stream().map(ExertSpecialityDTO::new).collect(Collectors.toList());
    }
}
