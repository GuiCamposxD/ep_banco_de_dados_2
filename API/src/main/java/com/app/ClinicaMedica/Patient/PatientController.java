package com.app.ClinicaMedica.Patient;

import com.app.ClinicaMedica.Patient.DTO.PatientCreateDTO;
import com.app.ClinicaMedica.Patient.DTO.PatientDTO;
import com.app.ClinicaMedica.Patient.DTO.PatientUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @PostMapping
    public ResponseEntity<PatientDTO> registerPatient(@Valid @RequestBody PatientCreateDTO form) {
        return ResponseEntity.ok(patientService.addNewPatient(form));
    }

    @PatchMapping(path = "{idPatient}")
    public ResponseEntity<?> updatePatient(
            @PathVariable("idPatient") Long idPatient,
            @Valid @RequestBody PatientUpdateDTO updateForm
    ) {
        try {
            return ResponseEntity.ok(patientService.updatePatient(idPatient, updateForm));
        } catch (IllegalStateException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{idPatient}")
    public ResponseEntity<?> deletePatient(
            @PathVariable("idPatient") Long idPatient
    ) {
        try {
            return ResponseEntity.ok(patientService.deletePatient(idPatient));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
