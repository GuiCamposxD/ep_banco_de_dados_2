package com.app.ClinicaMedica.Diagnose;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class DiagnoseId implements Serializable {
    private Long idDiagnostic;

    private Long idDisease;
}
