package com.app.ClinicaMedica.Doctor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/doctors")
public class DoctorController {
    @GetMapping
    public List<Doctor> hello() {
        return List.of(
                new Doctor(
                        "101021",
                        "Guilherme Campos",
                        "11953220101",
                        0.25F
                )
        );
    }
}
