package com.app.ClinicaMedica.Diagnostic.DTO;

import com.app.ClinicaMedica.Appointment.Appointment;
import com.app.ClinicaMedica.Diagnostic.Diagnostic;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiagnosticUpdateDTO {
    @NotEmpty(message = "Recommended Treatment is mandatory")
    private String recommendedTreatment;

    @NotEmpty(message = "observations Treatment is mandatory")
    private String observations;

    @NotNull(message = "Id Appointment is mandatory")
    private Long idAppointment;

    public void update(
        Diagnostic diagnostic,
        Appointment appointment
    ) {
        diagnostic.setRecommendedTreatment(this.getRecommendedTreatment());
        diagnostic.setObservations(this.getObservations());
        diagnostic.setAppointment(appointment);
    }
}
