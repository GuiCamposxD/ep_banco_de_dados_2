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
        DoctorDTO newDoctor = doctorService.addNewDoctor(form);
        return ResponseEntity.ok(newDoctor);
    }

    @PatchMapping(path = "{crm}")
    public ResponseEntity<?> updateDoctor(
            @PathVariable("crm") String crm,
            @Valid @RequestBody DoctorUpdateDTO doctorUpdateDTO
    ) {
         try {
            DoctorDTO updatedDoctor = doctorService.updateDoctor(crm, doctorUpdateDTO);
            return ResponseEntity.ok(updatedDoctor);
        } catch (IllegalStateException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
          }
    }

    @DeleteMapping(path = "{crm}")
    public ResponseEntity<?> deleteDoctor(
            @PathVariable("crm") String crm
    ) {
        try {
            DoctorDTO deletedDoctor = doctorService.deleteDoctor(crm);
            return ResponseEntity.ok(deletedDoctor);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
