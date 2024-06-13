package com.app.ClinicaMedica.Disease.DTO;

import com.app.ClinicaMedica.Disease.Disease;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiseaseCreateDTO {
    @NotEmpty(message = "Disease Name is mandatory")
    private String diseaseName;

    public Disease converter() {
        Disease disease = new Disease();
        disease.setDiseaseName(this.diseaseName);

        return disease;
    }
}
