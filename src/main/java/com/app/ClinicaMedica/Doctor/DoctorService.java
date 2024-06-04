package com.app.ClinicaMedica.Doctor;

import com.app.ClinicaMedica.Doctor.DTO.DoctorCreateDTO;
import com.app.ClinicaMedica.Doctor.DTO.DoctorDTO;
import com.app.ClinicaMedica.Doctor.DTO.DoctorUpdateDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorDTO> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return DoctorDTO.converter(doctors);
    }

    @Transactional
    public DoctorDTO addNewDoctor(DoctorCreateDTO form) {
        Doctor doctor = form.converter();
        doctorRepository.save(doctor);
        return new DoctorDTO(doctor);
    }

    @Transactional
    public DoctorDTO updateDoctor(String crm, DoctorUpdateDTO form) {
        Doctor doctor = doctorRepository.findByCrm(crm).orElseThrow(
            () -> new IllegalStateException("Doctor with this " + crm + " CRM does not exists")
        );

        form.update(doctor);
        doctorRepository.save(doctor);

        return new DoctorDTO(doctor);
    }

    @Transactional
    public DoctorDTO deleteDoctor(String crm) {
        Doctor doctor = doctorRepository.findByCrm(crm).orElseThrow(
                () -> new IllegalStateException("Doctor with this " + crm + " CRM does not exists")
        );

        doctorRepository.deleteByCrm(crm);

        return new DoctorDTO(doctor);
    }
}
