package com.app.ClinicaMedica.ExertSpeciality.DTO;

import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.ExertSpeciality.ExertSpeciality;
import com.app.ClinicaMedica.ExertSpeciality.ExertSpecialityId;
import com.app.ClinicaMedica.Speciality.Speciality;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExertSpecialityCreateDTO {
    @NotEmpty(message = "CRM is mandatory")
    private String crm;

    @NotNull(message = "Speciality Id is mandatory")
    private Long idSpeciality;

    public ExertSpeciality converter(Doctor doctor, Speciality speciality) {
        ExertSpecialityId id = new ExertSpecialityId(this.crm, this.idSpeciality);

        return new ExertSpeciality(id, doctor, speciality);
    }
}
