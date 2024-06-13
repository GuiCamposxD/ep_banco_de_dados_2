package com.app.ClinicaMedica.Disease.DTO;

import com.app.ClinicaMedica.Disease.Disease;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DiseaseDTO {
    @Id
    private final Long idDisease;

    @NotEmpty(message = "Disease Name is mandatory")
    private final String diseaseName;

    public DiseaseDTO(Disease disease) {
        this.idDisease = disease.getIdDisease();
        this.diseaseName = disease.getDiseaseName();
    }

    public static List<DiseaseDTO> converter(List<Disease> diseases) {
        return diseases.stream().map(DiseaseDTO::new).collect(Collectors.toList());
    }
}
