package com.app.ClinicaMedica.Diagnose;

import com.app.ClinicaMedica.Diagnose.DTO.DiagnoseCreateDTO;
import com.app.ClinicaMedica.Diagnose.DTO.DiagnoseDTO;
import com.app.ClinicaMedica.Diagnose.DTO.DiagnoseUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/diagnoses")
public class DiagnoseController {
    private final DiagnoseService diagnoseService;

    @Autowired
    public DiagnoseController(DiagnoseService diagnoseService) {
        this.diagnoseService = diagnoseService;
    }

    @GetMapping
    public ResponseEntity<List<DiagnoseDTO>> getDiagnoses() {
        return ResponseEntity.ok(diagnoseService.getDiagnoses());
    }

    @PostMapping
    public ResponseEntity<DiagnoseDTO> registerDiagnose(@Valid @RequestBody DiagnoseCreateDTO form) {
        return ResponseEntity.ok(diagnoseService.addNewDiagnose(form));
    }

    @PatchMapping(path = "{idDiagnose}")
    public ResponseEntity<?> updateDiagnose(
            @PathVariable("idDiagnose") Long idDiagnose,
            @Valid @RequestBody DiagnoseUpdateDTO updateForm
    ) {
        try {
            return ResponseEntity.ok(diagnoseService.updateDiagnose(idDiagnose, updateForm));
        } catch (IllegalStateException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{idDiagnose}")
    public ResponseEntity<?> deleteDiagnose(
            @PathVariable("idDiagnose") Long idDiagnose
    ) {
        try {
            return ResponseEntity.ok(diagnoseService.deleteDiagnose(idDiagnose));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
