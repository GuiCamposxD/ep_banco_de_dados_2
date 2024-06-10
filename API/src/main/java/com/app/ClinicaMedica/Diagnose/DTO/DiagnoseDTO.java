package com.app.ClinicaMedica.Diagnose.DTO;

import com.app.ClinicaMedica.Diagnose.Diagnose;
import com.app.ClinicaMedica.Diagnostic.DTO.DiagnosticDTO;
import com.app.ClinicaMedica.Disease.DTO.DiseaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DiagnoseDTO {
    @NotNull(message = "Diagnose is mandatory")
    private final DiagnosticDTO diagnostic;

    @NotNull(message = "Disease is mandatory")
    private final DiseaseDTO disease;

    public DiagnoseDTO(Diagnose diagnose) {
        this.diagnostic = new DiagnosticDTO(diagnose.getDiagnostic());
        this.disease = new DiseaseDTO(diagnose.getDisease());
    }
    public static List<DiagnoseDTO> converter(List<Diagnose> diagnoses) {
        return diagnoses.stream().map(DiagnoseDTO::new).collect(Collectors.toList());
    }
}
