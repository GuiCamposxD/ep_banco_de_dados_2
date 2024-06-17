package com.app.ClinicaMedica.Diagnostic.DTO;

import com.app.ClinicaMedica.Appointment.Appointment;
import com.app.ClinicaMedica.Diagnostic.Diagnostic;
import com.app.ClinicaMedica.Disease.Disease;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class DiagnosticCreateDTO {
    @NotEmpty(message = "Recommended Treatment is mandatory")
    private String recommendedTreatment;

    @NotEmpty(message = "observations Treatment is mandatory")
    private String observations;

    @NotEmpty(message = "Prescription Medicines is mandatory")
    private String prescriptionMedicines;

    @NotNull(message = "Id Appointment is mandatory")
    private Long idAppointment;

    private Set<Long> idDiseases;

    public Diagnostic converter(
        Appointment appointment
    ) {
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setRecommendedTreatment(this.getRecommendedTreatment());
        diagnostic.setObservations(this.getObservations());
        diagnostic.setPrescriptionMedicines(this.getPrescriptionMedicines());
        diagnostic.setAppointment(appointment);

        return diagnostic;
    }
}
