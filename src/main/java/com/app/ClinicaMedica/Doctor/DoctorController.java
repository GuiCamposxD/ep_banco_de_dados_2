package com.app.ClinicaMedica.Doctor;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getDoctors() {
        return doctorService.getDoctors();
    }

    @PostMapping
    public void registerDoctor(@Valid @RequestBody DoctorDto doctorDTO) {
        doctorService.addNewDoctor(doctorDTO);
    }

    @PatchMapping(path = "{crm}")
    public ResponseEntity<String> updateDoctor(
            @PathVariable("crm") String crm,
            @Valid @RequestBody DoctorDto doctorDto
    ) {
         try {
            doctorService.updateDoctor(crm, doctorDto);
            return ResponseEntity.ok("Doctor updated successfully");
        } catch (IllegalStateException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
          }
    }
}
