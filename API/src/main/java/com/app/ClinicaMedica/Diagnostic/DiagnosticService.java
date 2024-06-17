package com.app.ClinicaMedica.Diagnostic;

import Util.FetchEntity;
import com.app.ClinicaMedica.Appointment.Appointment;
import com.app.ClinicaMedica.Appointment.AppointmentRepository;
import com.app.ClinicaMedica.Diagnose.DTO.DiagnoseCreateDTO;
import com.app.ClinicaMedica.Diagnose.Diagnose;
import com.app.ClinicaMedica.Diagnose.DiagnoseRepository;
import com.app.ClinicaMedica.Diagnose.DiagnoseService;
import com.app.ClinicaMedica.Diagnostic.DTO.DiagnosticCreateDTO;
import com.app.ClinicaMedica.Diagnostic.DTO.DiagnosticDTO;
import com.app.ClinicaMedica.Diagnostic.DTO.DiagnosticUpdateDTO;
import com.app.ClinicaMedica.Diagnostic.DTO.DiagnosticWithDiseasesDTO;
import com.app.ClinicaMedica.Disease.DTO.DiseaseDTO;
import com.app.ClinicaMedica.Disease.Disease;
import com.app.ClinicaMedica.Doctor.DTO.DoctorDTO;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityCreateDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiagnosticService {
    private final DiagnosticRepository diagnosticRepository;
    private final AppointmentRepository appointmentRepository;
    private final DiagnoseRepository diagnoseRepository;
    private final DiagnoseService diagnoseService;

    @Autowired
    public DiagnosticService(
            DiagnosticRepository diagnosticRepository,
            AppointmentRepository appointmentRepository,
            DiagnoseRepository diagnoseRepository,
            DiagnoseService diagnoseService
    ) {
        this.diagnosticRepository = diagnosticRepository;
        this.appointmentRepository = appointmentRepository;
        this.diagnoseRepository = diagnoseRepository;
        this.diagnoseService = diagnoseService;
    }

    public List<DiagnosticDTO> getDiagnostics() {
        return DiagnosticDTO.converter(diagnosticRepository.findAll());
    }

    public List<DiagnosticWithDiseasesDTO> getDiagnosticsByPatientId(Long idPatient) {
        List<Diagnostic> diagnostics = diagnosticRepository.findByAppointment_Patient_IdPatient(idPatient);

        return diagnostics.stream()
                .map(diagnostic -> {
                    List<DiseaseDTO> diseases = diagnoseRepository.findByDiagnostic_IdDiagnostic(diagnostic.getIdDiagnostic())
                            .stream()
                            .map(diagnose -> new DiseaseDTO(diagnose.getDisease()))
                            .collect(Collectors.toList());
                    return new DiagnosticWithDiseasesDTO(diagnostic, diseases);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public DiagnosticDTO addNewDiagnostic(DiagnosticCreateDTO form) {
        Appointment appointment = FetchEntity.fetchEntity(form.getIdAppointment(), this.appointmentRepository, "Appointment");

        if(!form.getIdDiseases().isEmpty()) {
            Diagnostic diagnostic = form.converter(appointment);
            this.diagnosticRepository.save(diagnostic);

            for(Long idDisease : form.getIdDiseases()) {
                this.diagnoseService.addNewDiagnose(
                        new DiagnoseCreateDTO(diagnostic.getIdDiagnostic(), idDisease)
                );
            }

            return new DiagnosticDTO(diagnostic);
        }

        throw new EntityNotFoundException("This Diagnostic doesn't have disease");
    }

    @Transactional
    public DiagnosticDTO updateDiagnostic(Long idDiagnostic, DiagnosticUpdateDTO form) {
        Diagnostic diagnostic = FetchEntity.fetchEntity(idDiagnostic, this.diagnosticRepository, "Diagnostic");
        Appointment appointment = FetchEntity.fetchEntity(form.getIdAppointment(), this.appointmentRepository, "Appointment");

        form.update(diagnostic, appointment);
        this.diagnosticRepository.save(diagnostic);

        return new DiagnosticDTO(diagnostic);
    }

    @Transactional
    public DiagnosticDTO deleteDiagnostic(Long idDiagnostic) {
        Diagnostic diagnostic = FetchEntity.fetchEntity(idDiagnostic, this.diagnosticRepository, "Diagnostic");

        this.diagnosticRepository.deleteById(idDiagnostic);

        return new DiagnosticDTO(diagnostic);
    }
}
