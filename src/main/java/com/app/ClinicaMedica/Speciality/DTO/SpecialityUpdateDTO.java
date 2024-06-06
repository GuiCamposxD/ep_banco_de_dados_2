package com.app.ClinicaMedica.Speciality.DTO;

import com.app.ClinicaMedica.Speciality.Speciality;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpecialityUpdateDTO {
    @NotEmpty(message = "Speciality name is mandatory")
    private String specialityName;

    @NotNull(message = "Index is mandatory")
    private Integer index;

    public void update(Speciality speciality) {
        speciality.setSpecialityName(this.getSpecialityName());
        speciality.setIndex(this.getIndex());
    }
}
