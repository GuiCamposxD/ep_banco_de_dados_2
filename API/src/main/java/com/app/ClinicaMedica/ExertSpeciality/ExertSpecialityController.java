package com.app.ClinicaMedica.ExertSpeciality;

import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityCreateDTO;
import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityDTO;
import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "exert-specialities")
public class ExertSpecialityController {
    private final ExertSpecialityService exertSpecialityService;

    @Autowired
    public ExertSpecialityController(ExertSpecialityService exertSpecialityService) {
        this.exertSpecialityService = exertSpecialityService;
    }

    @GetMapping
    public ResponseEntity<List<ExertSpecialityDTO>> getExertSpecialities() {
        return ResponseEntity.ok(this.exertSpecialityService.getExertSpeciality());
    }

    @PostMapping
    public ResponseEntity<ExertSpecialityDTO> registerExertSpeciality(
            @Valid @RequestBody ExertSpecialityCreateDTO form
    ) {
        return ResponseEntity.ok(this.exertSpecialityService.addNewExertSpeciality(form));
    }

    @PatchMapping(path = "{crm}/{idSpeciality}")
    public ResponseEntity<?> updateExertSpeciality(
            @PathVariable String crm,
            @PathVariable Long idSpeciality,
            @Valid @RequestBody ExertSpecialityUpdateDTO form
    ) {
        try {
            return ResponseEntity.ok(exertSpecialityService.updateExertSpeciality(crm, idSpeciality, form));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{crm}/{idSpeciality}")
    public ResponseEntity<?> deleteExertSpeciality(
            @PathVariable String crm,
            @PathVariable Long idSpeciality
    ) {
        try {
            return ResponseEntity.ok(exertSpecialityService.deleteExertSpeciality(crm, idSpeciality));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
