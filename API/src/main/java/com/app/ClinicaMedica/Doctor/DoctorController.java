package com.app.ClinicaMedica.Doctor;

import com.app.ClinicaMedica.Doctor.DTO.DoctorCreateDTO;
import com.app.ClinicaMedica.Doctor.DTO.DoctorDTO;
import com.app.ClinicaMedica.Doctor.DTO.DoctorUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getDoctors() {
        return ResponseEntity.ok(doctorService.getDoctors());
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> registerDoctor(@Valid @RequestBody DoctorCreateDTO form) {
        return ResponseEntity.ok(doctorService.addNewDoctor(form));
    }

    @PatchMapping(path = "{crm}")
    public ResponseEntity<?> updateDoctor(
            @PathVariable("crm") String crm,
            @Valid @RequestBody DoctorUpdateDTO updateForm
    ) {
        try {
            return ResponseEntity.ok(doctorService.updateDoctor(crm, updateForm));
        } catch (IllegalStateException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{crm}")
    public ResponseEntity<?> deleteDoctor(
            @PathVariable("crm") String crm
    ) {
        try {
            return ResponseEntity.ok(doctorService.deleteDoctor(crm));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
