package com.app.ClinicaMedica.Diagnostic.DTO;

import com.app.ClinicaMedica.Appointment.DTO.AppointmentDTO;
import com.app.ClinicaMedica.Diagnostic.Diagnostic;
import com.app.ClinicaMedica.Disease.DTO.DiseaseDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class DiagnosticWithDiseasesDTO {
    private final Long idDiagnostic;
    private final String recommendedTreatment;
    private final String prescriptionMedicines;
    private final String observations;
    private final AppointmentDTO appointment;
    private final List<DiseaseDTO> diseases;

    public DiagnosticWithDiseasesDTO(Diagnostic diagnostic, List<DiseaseDTO> diseases) {
        this.idDiagnostic = diagnostic.getIdDiagnostic();
        this.recommendedTreatment = diagnostic.getRecommendedTreatment();
        this.observations = diagnostic.getObservations();
        this.prescriptionMedicines = diagnostic.getPrescriptionMedicines();
        this.appointment = new AppointmentDTO(diagnostic.getAppointment());
        this.diseases = diseases;
    }
}