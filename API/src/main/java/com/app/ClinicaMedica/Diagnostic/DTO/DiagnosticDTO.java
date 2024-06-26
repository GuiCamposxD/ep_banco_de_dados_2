package com.app.ClinicaMedica.Diagnostic.DTO;

import com.app.ClinicaMedica.Appointment.DTO.AppointmentDTO;
import com.app.ClinicaMedica.Diagnostic.Diagnostic;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DiagnosticDTO {
    @NotNull(message = "Id Diagnostic is mandatory")
    private final Long idDiagnostic;

    @NotEmpty(message = "Recommended Treatment is mandatory")
    private final String recommendedTreatment;

    @NotEmpty(message = "Prescription Medicines is mandatory")
    private final String prescriptionMedicines;

    @NotEmpty(message = "Observations Treatment is mandatory")
    private final String observations;

    @NotNull(message = "Appointment is mandatory")
    private final AppointmentDTO appointment;

    public DiagnosticDTO(Diagnostic diagnostic) {
        this.idDiagnostic = diagnostic.getIdDiagnostic();
        this.recommendedTreatment = diagnostic.getRecommendedTreatment();
        this.observations = diagnostic.getObservations();
        this.prescriptionMedicines = diagnostic.getPrescriptionMedicines();
        this.appointment = new AppointmentDTO(diagnostic.getAppointment());
    }

    public static List<DiagnosticDTO> converter(List<Diagnostic> diagnostics) {
        return diagnostics.stream().map(DiagnosticDTO::new).collect(Collectors.toList());
    }
}
