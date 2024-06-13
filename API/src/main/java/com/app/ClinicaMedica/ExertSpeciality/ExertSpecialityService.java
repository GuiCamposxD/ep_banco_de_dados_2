package com.app.ClinicaMedica.ExertSpeciality;

import Util.FetchEntity;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Doctor.DoctorRepository;
import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityCreateDTO;
import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityDTO;
import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityUpdateDTO;
import com.app.ClinicaMedica.Speciality.DTO.SpecialityDTO;
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

    public List<SpecialityDTO> findSpecialitiesByDoctorCrm(String crm) {
        return SpecialityDTO.converter(this.exertSpecialityRepository.findSpecialitiesByDoctorCrm(crm));
    }

    @Transactional
    public ExertSpecialityDTO addNewExertSpeciality(
        ExertSpecialityCreateDTO form
    ) {
        Doctor doctor = FetchEntity.fetchEntity(form.getCrm(), this.doctorRepository);
        Speciality speciality = FetchEntity.fetchEntity(form.getIdSpeciality(), this.specialityRepository, "Speciality");

        ExertSpeciality exertSpeciality = form.converter(doctor, speciality);
        this.exertSpecialityRepository.save(exertSpeciality);

        return new ExertSpecialityDTO(exertSpeciality);
    }

    @Transactional
    public ExertSpecialityDTO updateExertSpeciality(String crm, Long idSpeciality, ExertSpecialityUpdateDTO form) {
        ExertSpeciality exertSpeciality = FetchEntity.fetchEntity(crm, idSpeciality, this.exertSpecialityRepository);
        Doctor doctor = FetchEntity.fetchEntity(form.getCrm(), this.doctorRepository);
        Speciality speciality = FetchEntity.fetchEntity(form.getIdSpeciality(), this.specialityRepository, "Speciality");

        form.update(exertSpeciality, doctor, speciality);
        this.exertSpecialityRepository.save(exertSpeciality);

        return new ExertSpecialityDTO(exertSpeciality);
    }

    @Transactional
    public ExertSpecialityDTO deleteExertSpeciality(String crm, Long idSpeciality) {
        ExertSpeciality exertSpeciality = FetchEntity.fetchEntity(crm, idSpeciality, this.exertSpecialityRepository);

        exertSpecialityRepository.delete(exertSpeciality);

        return new ExertSpecialityDTO(exertSpeciality);
    }
}
