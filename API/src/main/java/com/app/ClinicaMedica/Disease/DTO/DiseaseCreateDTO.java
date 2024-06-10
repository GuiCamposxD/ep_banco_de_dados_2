package com.app.ClinicaMedica.Disease.DTO;

import com.app.ClinicaMedica.Disease.Disease;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiseaseCreateDTO {
    @NotEmpty(message = "Disease Name is mandatory")
    private String diseaseName;

    public Disease converter() {
        Disease disease = new Disease();
        disease.setDiseaseName(this.diseaseName);

        return disease;
    }
}
