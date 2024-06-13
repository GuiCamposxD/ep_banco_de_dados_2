package com.app.ClinicaMedica.Disease;

import com.app.ClinicaMedica.Disease.DTO.DiseaseCreateDTO;
import com.app.ClinicaMedica.Disease.DTO.DiseaseDTO;
import com.app.ClinicaMedica.Disease.DTO.DiseaseUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/diseases")
public class DiseaseController {
    private final DiseaseService diseaseService;

    @Autowired
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping
    public ResponseEntity<List<DiseaseDTO>> getDiseases() {
        return ResponseEntity.ok(diseaseService.getDiseases());
    }

    @PostMapping
    public ResponseEntity<DiseaseDTO> registerDisease(@Valid @RequestBody DiseaseCreateDTO form) {
        System.out.println(form);
        return ResponseEntity.ok(diseaseService.addNewDisease(form));
    }

    @PatchMapping(path = "{idDisease}")
    public ResponseEntity<?> updateDisease(
            @PathVariable("idDisease") Long idDisease,
            @Valid @RequestBody DiseaseUpdateDTO updateForm
    ) {
        try {
            return ResponseEntity.ok(diseaseService.updateDisease(idDisease, updateForm));
        } catch (IllegalStateException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{idDisease}")
    public ResponseEntity<?> deleteDisease(
            @PathVariable("idDisease") Long idDisease
    ) {
        try {
            return ResponseEntity.ok(diseaseService.deleteDisease(idDisease));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
