package com.app.ClinicaMedica.Appointment;

import com.app.ClinicaMedica.Appointment.DTO.AppointmentCreateDTO;
import com.app.ClinicaMedica.Appointment.DTO.AppointmentDTO;
import com.app.ClinicaMedica.Appointment.DTO.AppointmentUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAppointments() {
        return ResponseEntity.ok(appointmentService.getAppointments());
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> registerAppointment(@Valid @RequestBody AppointmentCreateDTO form) {
        return ResponseEntity.ok(appointmentService.addNewAppointment(form));
    }

    @PatchMapping(path = "{idAppointment}")
    public ResponseEntity<?> updateAppointment(
            @PathVariable("idAppointment") Long idAppointment,
            @Valid @RequestBody AppointmentUpdateDTO updateForm
    ) {
        try {
            return ResponseEntity.ok(appointmentService.updateAppointment(idAppointment, updateForm));
        } catch (IllegalStateException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{idAppointment}")
    public ResponseEntity<?> deleteAppointment(
            @PathVariable("idAppointment") Long idAppointment
    ) {
        try {
            return ResponseEntity.ok(appointmentService.deleteAppointment(idAppointment));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
