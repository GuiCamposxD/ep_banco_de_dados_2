package com.app.ClinicaMedica.Diagnostic;

import com.app.ClinicaMedica.Diagnostic.DTO.DiagnosticCreateDTO;
import com.app.ClinicaMedica.Diagnostic.DTO.DiagnosticDTO;
import com.app.ClinicaMedica.Diagnostic.DTO.DiagnosticUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/diagnostics")
public class DiagnosticController {
    private final DiagnosticService diagnosticService;

    @Autowired
    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    @GetMapping
    public ResponseEntity<List<DiagnosticDTO>> getDiagnostics() {
        return ResponseEntity.ok(diagnosticService.getDiagnostics());
    }

    @PostMapping
    public ResponseEntity<DiagnosticDTO> registerDiagnostic(@Valid @RequestBody DiagnosticCreateDTO form) {
        return ResponseEntity.ok(diagnosticService.addNewDiagnostic(form));
    }

    @PatchMapping(path = "{idDiagnostic}")
    public ResponseEntity<?> updateDiagnostic(
            @PathVariable("idDiagnostic") Long idDiagnostic,
            @Valid @RequestBody DiagnosticUpdateDTO updateForm
    ) {
        try {
            return ResponseEntity.ok(diagnosticService.updateDiagnostic(idDiagnostic, updateForm));
        } catch (IllegalStateException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{idDiagnostic}")
    public ResponseEntity<?> deleteDiagnostic(
            @PathVariable("idDiagnostic") Long idPatient
    ) {
        try {
            return ResponseEntity.ok(diagnosticService.deleteDiagnostic(idPatient));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
