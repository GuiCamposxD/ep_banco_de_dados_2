package com.app.ClinicaMedica.Doctor;

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

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public void addNewDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();

        doctor.setCrm(doctorDto.getCrm());
        doctor.setDoctorName(doctorDto.getDoctorName());
        doctor.setDoctorPhone(doctorDto.getDoctorPhone());
        doctor.setPercentage(doctorDto.getPercentage());

        doctorRepository.save(doctor);
    }

    public void updateDoctor(String crm, DoctorDto doctorDto) {
        Doctor existingDoctor = doctorRepository.findByCrm(crm).orElseThrow(
                () -> new IllegalStateException("Doctor with this " + crm + " CRM does not exists")
        );

        existingDoctor.setDoctorName(doctorDto.getDoctorName());
        existingDoctor.setDoctorPhone(doctorDto.getDoctorPhone());
        existingDoctor.setPercentage(doctorDto.getPercentage());

        doctorRepository.save(existingDoctor);
    }
}
