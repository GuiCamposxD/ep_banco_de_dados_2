package com.app.ClinicaMedica.Diagnostic;

import Util.FetchEntity;
import com.app.ClinicaMedica.Appointment.Appointment;
import com.app.ClinicaMedica.Appointment.AppointmentRepository;
import com.app.ClinicaMedica.Diagnostic.DTO.DiagnosticCreateDTO;
import com.app.ClinicaMedica.Diagnostic.DTO.DiagnosticDTO;
import com.app.ClinicaMedica.Diagnostic.DTO.DiagnosticUpdateDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosticService {
    private final DiagnosticRepository diagnosticRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public DiagnosticService(
            DiagnosticRepository diagnosticRepository,
            AppointmentRepository appointmentRepository
    ) {
        this.diagnosticRepository = diagnosticRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<DiagnosticDTO> getDiagnostics() {
        return DiagnosticDTO.converter(diagnosticRepository.findAll());
    }

    @Transactional
    public DiagnosticDTO addNewDiagnostic(DiagnosticCreateDTO form) {
        Appointment appointment = FetchEntity.fetchEntity(form.getIdAppointment(), this.appointmentRepository, "Appointment");

        Diagnostic diagnostic = form.converter(appointment);
        this.diagnosticRepository.save(diagnostic);

        return new DiagnosticDTO(diagnostic);
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
