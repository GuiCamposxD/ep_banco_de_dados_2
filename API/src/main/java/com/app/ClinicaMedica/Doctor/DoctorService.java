package com.app.ClinicaMedica.Doctor;

import Util.FetchEntity;
import com.app.ClinicaMedica.Doctor.DTO.DoctorCreateDTO;
import com.app.ClinicaMedica.Doctor.DTO.DoctorDTO;
import com.app.ClinicaMedica.Doctor.DTO.DoctorUpdateDTO;
import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityCreateDTO;
import com.app.ClinicaMedica.ExertSpeciality.ExertSpecialityService;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final ExertSpecialityService exertSpecialityService;

    @Autowired
    public DoctorService(
        DoctorRepository doctorRepository,
        ExertSpecialityService exertSpecialityService
    ) {
        this.doctorRepository = doctorRepository;
        this.exertSpecialityService = exertSpecialityService;
    }

    public List<DoctorDTO> getDoctors() {
        return DoctorDTO.converter(doctorRepository.findAll());
    }

    @Transactional
    public DoctorDTO addNewDoctor(DoctorCreateDTO form) {
        if(doctorRepository.existsById(form.getCrm())) throw new EntityExistsException("This Doctor already exists");

        Doctor doctor = form.converter();
        doctorRepository.save(doctor);

        if(form.getIdSpeciality() != null) {
            exertSpecialityService.addNewExertSpeciality(
                    new ExertSpecialityCreateDTO(form.getCrm(), form.getIdSpeciality())
            );
        }

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
