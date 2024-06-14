package com.app.ClinicaMedica.Diagnostic.DTO;

import com.app.ClinicaMedica.Appointment.Appointment;
import com.app.ClinicaMedica.Diagnostic.Diagnostic;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiagnosticCreateDTO {
    @NotEmpty(message = "Recommended Treatment is mandatory")
    private String recommendedTreatment;

    @NotEmpty(message = "observations Treatment is mandatory")
    private String observations;

    @NotNull(message = "Id Appointment is mandatory")
    private Long idAppointment;

    @NotNull(message = "Id Disease is mandatory")
    private Long idDisease;

    public Diagnostic converter(
        Appointment appointment
    ) {
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setRecommendedTreatment(this.getRecommendedTreatment());
        diagnostic.setObservations(this.getObservations());
        diagnostic.setAppointment(appointment);

        return diagnostic;
    }
}
