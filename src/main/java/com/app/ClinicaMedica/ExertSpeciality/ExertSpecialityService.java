package com.app.ClinicaMedica.ExertSpeciality;

import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Doctor.DoctorRepository;
import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityCreateDTO;
import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityDTO;
import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityUpdateDTO;
import com.app.ClinicaMedica.Speciality.Speciality;
import com.app.ClinicaMedica.Speciality.SpecialityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExertSpecialityService {
    private final ExertSpecialityRepository exertSpecialityRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;

    @Autowired
    public ExertSpecialityService(
            ExertSpecialityRepository exertSpecialityRepository,
            DoctorRepository doctorRepository,
            SpecialityRepository specialityRepository
    ) {
        this.exertSpecialityRepository = exertSpecialityRepository;
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
    }

    public List<ExertSpecialityDTO> getExertSpeciality() {
        return ExertSpecialityDTO.converter(this.exertSpecialityRepository.findAll());
    }

    @Transactional
    public ExertSpecialityDTO addNewExertSpeciality(
        ExertSpecialityCreateDTO form
    ) {
        Doctor doctor = doctorRepository.findById(form.getCrm())
                .orElseThrow(() -> new IllegalStateException("Doctor with CRM " + form.getCrm() + " does not exist"));

        Speciality speciality = specialityRepository.findById(form.getIdSpeciality())
                .orElseThrow(() -> new IllegalStateException("Doctor with CRM " + form.getCrm() + " does not exist"));

        ExertSpeciality exertSpeciality = form.converter(doctor, speciality);
        this.exertSpecialityRepository.save(exertSpeciality);
        return new ExertSpecialityDTO(exertSpeciality);
    }

    @Transactional
    public ExertSpecialityDTO updateExertSpeciality(String crm, Long idSpeciality, ExertSpecialityUpdateDTO form) {
        ExertSpeciality exertSpeciality = exertSpecialityRepository.findById(new ExertSpecialityId(crm, idSpeciality))
                .orElseThrow(() -> new IllegalStateException("ExertSpeciality with CRM " + crm + " and Speciality ID " + idSpeciality + " does not exist"));

        Doctor doctor = doctorRepository.findById(form.getCrm())
                .orElseThrow(() -> new IllegalStateException("Doctor with CRM " + form.getCrm() + " does not exist"));

        Speciality speciality = specialityRepository.findById(form.getIdSpeciality())
                .orElseThrow(() -> new IllegalStateException("Doctor with CRM " + form.getCrm() + " does not exist"));

        form.update(exertSpeciality, doctor, speciality);
        exertSpecialityRepository.save(exertSpeciality);
        return new ExertSpecialityDTO(exertSpeciality);
    }

    @Transactional
    public ExertSpecialityDTO deleteExertSpeciality(String crm, Long idSpeciality) {
        ExertSpeciality exertSpeciality = exertSpecialityRepository.findById(new ExertSpecialityId(crm, idSpeciality))
                .orElseThrow(() -> new IllegalStateException("ExertSpeciality with CRM " + crm + " and Speciality ID " + idSpeciality + " does not exist"));

        exertSpecialityRepository.delete(exertSpeciality);

        return new ExertSpecialityDTO(exertSpeciality);
    }
}
