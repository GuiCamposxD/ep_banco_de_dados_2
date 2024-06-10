package com.app.ClinicaMedica.ExertSpeciality.DTO;

import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.ExertSpeciality.ExertSpeciality;
import com.app.ClinicaMedica.Speciality.Speciality;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExertSpecialityUpdateDTO {
    @NotNull(message = "CRM is mandatory")
    private String crm;

    @NotNull(message = "Id Speciality is mandatory")
    private Long idSpeciality;

    public void update(ExertSpeciality exertSpeciality, Doctor doctor, Speciality speciality) {
        exertSpeciality.setDoctor(doctor);
        exertSpeciality.setSpeciality(speciality);
    }
}
