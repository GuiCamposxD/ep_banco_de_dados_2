package com.app.ClinicaMedica.Diagnose.DTO;

import com.app.ClinicaMedica.Diagnose.Diagnose;
import com.app.ClinicaMedica.Diagnose.DiagnoseId;
import com.app.ClinicaMedica.Diagnostic.Diagnostic;
import com.app.ClinicaMedica.Disease.Disease;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiagnoseCreateDTO {
    @NotNull(message = "Id Diagnostic is mandatory")
    private Long idDiagnostic;

    @NotNull(message = "Id Disease is mandatory")
    private Long idDisease;

    public Diagnose converter(Diagnostic diagnostic, Disease disease) {
        DiagnoseId id = new DiagnoseId(this.idDiagnostic, this.idDisease);

        return new Diagnose(id, diagnostic, disease);
    }
}
