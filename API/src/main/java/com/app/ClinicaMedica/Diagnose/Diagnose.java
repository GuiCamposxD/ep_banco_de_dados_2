package com.app.ClinicaMedica.Diagnose;

import com.app.ClinicaMedica.Diagnostic.Diagnostic;
import com.app.ClinicaMedica.Disease.Disease;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Diagnose {
    @EmbeddedId
    private DiagnoseId id;

    @ManyToOne
    @MapsId("idDiagnostic")
    @JoinColumn(name = "idDiagnostic", referencedColumnName = "idDiagnostic", nullable = false)
    private Diagnostic diagnostic;

    @ManyToOne
    @MapsId("idDisease")
    @JoinColumn(name = "idDisease", referencedColumnName = "idDisease", nullable = false)
    private Disease disease;
}
