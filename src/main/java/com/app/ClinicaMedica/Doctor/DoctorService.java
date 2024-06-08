package com.app.ClinicaMedica.Doctor;

import Util.FetchEntity;
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
        return DoctorDTO.converter(doctorRepository.findAll());
    }

    @Transactional
    public DoctorDTO addNewDoctor(DoctorCreateDTO form) {
        Doctor doctor = form.converter();
        doctorRepository.save(doctor);
        return new DoctorDTO(doctor);
    }

    @Transactional
    public DoctorDTO updateDoctor(String crm, DoctorUpdateDTO form) {
        Doctor doctor = FetchEntity.fetchEntity(crm, this.doctorRepository);

        form.update(doctor);
        doctorRepository.save(doctor);

        return new DoctorDTO(doctor);
    }

    @Transactional
    public DoctorDTO deleteDoctor(String crm) {
        Doctor doctor = FetchEntity.fetchEntity(crm, this.doctorRepository);

        doctorRepository.deleteByCrm(crm);

        return new DoctorDTO(doctor);
    }
}
