package com.app.ClinicaMedica.Disease.DTO;

import com.app.ClinicaMedica.Disease.Disease;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiseaseUpdateDTO {
    @NotEmpty(message = "Disease Name is mandatory")
    private String diseaseName;

    public void update(Disease disease) {
        disease.setDiseaseName(this.getDiseaseName());
    }
}
