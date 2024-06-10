package com.app.ClinicaMedica.Diagnose.DTO;

import com.app.ClinicaMedica.Diagnose.Diagnose;
import com.app.ClinicaMedica.Diagnostic.Diagnostic;
import com.app.ClinicaMedica.Disease.Disease;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiagnoseUpdateDTO {
    @NotNull(message = "Id Diagnostic is mandatory")
    private Long idDiagnostic;

    @NotNull(message = "Id Disease is mandatory")
    private Long idDisease;

    public void update(Diagnose diagnose, Diagnostic diagnostic, Disease disease) {
        diagnose.setDiagnostic(diagnostic);
        diagnose.setDisease(disease);
    }
}
