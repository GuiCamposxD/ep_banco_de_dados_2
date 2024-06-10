package com.app.ClinicaMedica.Speciality.DTO;

import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Speciality.Speciality;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SpecialityDTO {
    @NotEmpty(message = "Id is mandatory")
    private final Long idSpeciality;

    @NotEmpty(message = "Speciality name is mandatory")
    private final String specialityName;

    @NotNull(message = "Index is mandatory")
    private final Integer index;

    public SpecialityDTO(Speciality speciality) {
        this.idSpeciality = speciality.getIdSpeciality();
        this.specialityName = speciality.getSpecialityName();
        this.index = speciality.getIndex();
    }

    public static List<SpecialityDTO> converter(List<Speciality> specialities) {
        return specialities.stream().map(SpecialityDTO::new).collect(Collectors.toList());
    }
}
