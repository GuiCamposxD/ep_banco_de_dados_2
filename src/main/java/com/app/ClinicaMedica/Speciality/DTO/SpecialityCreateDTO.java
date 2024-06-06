package com.app.ClinicaMedica.Speciality.DTO;

import com.app.ClinicaMedica.Speciality.Speciality;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpecialityCreateDTO {
    @NotEmpty(message = "Speciality name is mandatory")
    private String specialityName;

    @NotNull(message = "Doctor phone is mandatory")
    @Min(0)
    private Integer index;

    public Speciality converter() {
        Speciality speciality = new Speciality();
        speciality.setSpecialityName(this.specialityName);
        speciality.setIndex(this.getIndex());

        return speciality;
    }
}
