package com.app.ClinicaMedica.Disease.DTO;

import com.app.ClinicaMedica.Disease.Disease;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseUpdateDTO {
    @NotEmpty(message = "Disease Name is mandatory")
    private String diseaseName;

    public void update(Disease disease) {
        disease.setDiseaseName(this.getDiseaseName());
    }
}
