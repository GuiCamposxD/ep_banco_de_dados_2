package com.app.ClinicaMedica.Speciality;

import com.app.ClinicaMedica.Speciality.DTO.SpecialityCreateDTO;
import com.app.ClinicaMedica.Speciality.DTO.SpecialityDTO;
import com.app.ClinicaMedica.Speciality.DTO.SpecialityUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/specialities")
public class SpecialityController {
    private final SpecialityService specialityService;

    @Autowired
    public SpecialityController (SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping
    public ResponseEntity<List<SpecialityDTO>> getSpecialities() {
        return ResponseEntity.ok(specialityService.getSpecialities());
    }

    @PostMapping
    public ResponseEntity<SpecialityDTO> registerSpeciality(
        @Valid @RequestBody SpecialityCreateDTO form
    ) {
        return ResponseEntity.ok(specialityService.addNewSpeciality(form));
    }

    @PatchMapping("{idSpeciality}")
    public ResponseEntity<?> updateSpeciality(
        @PathVariable("idSpeciality") Long idSpeciality,
        @Valid @RequestBody SpecialityUpdateDTO updateForm
    ) {
        try {
            return ResponseEntity.ok(specialityService.updateSpeciality(idSpeciality, updateForm));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{idSpeciality}")
    public ResponseEntity<?> deleteSpeciality(
        @PathVariable("idSpeciality") Long idSpeciality
    ) {
        try {
            return ResponseEntity.ok(specialityService.deleteSpeciality(idSpeciality));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
