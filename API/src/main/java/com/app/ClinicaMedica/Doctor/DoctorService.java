package com.app.ClinicaMedica.Doctor;

import Util.FetchEntity;
import com.app.ClinicaMedica.Doctor.DTO.DoctorCreateDTO;
import com.app.ClinicaMedica.Doctor.DTO.DoctorDTO;
import com.app.ClinicaMedica.Doctor.DTO.DoctorUpdateDTO;
import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityCreateDTO;
import com.app.ClinicaMedica.ExertSpeciality.DTO.ExertSpecialityUpdateDTO;
import com.app.ClinicaMedica.ExertSpeciality.ExertSpecialityService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return DoctorDTO.converter(doctorRepository.findAllByOrderByCrmAsc());
    }

    public DoctorDTO getDoctorByCrm(String crm) {
        Optional<Doctor> doctor = this.doctorRepository.findByCrm(crm);

        if(doctor.isPresent()) return new DoctorDTO(doctor.get());

        throw new EntityNotFoundException("This Doctor doesn't exists");
    }

    @Transactional
    public DoctorDTO addNewDoctor(DoctorCreateDTO form) {
        if(doctorRepository.existsById(form.getCrm())) throw new EntityExistsException("This Doctor already exists");

        if(!form.getIdSpecialities().isEmpty()) {
            Doctor doctor = form.converter();
            doctorRepository.save(doctor);

            for(Long idSpeciality : form.getIdSpecialities()) {
                exertSpecialityService.addNewExertSpeciality(
                    new ExertSpecialityCreateDTO(form.getCrm(), idSpeciality)
                );
            }

            return new DoctorDTO(doctor);
        }

        throw new EntityNotFoundException("This Doctor doesn't have speciality");
    }

    @Transactional
    public DoctorDTO updateDoctor(String crm, DoctorUpdateDTO form) {
        Doctor doctor = FetchEntity.fetchEntity(crm, this.doctorRepository);

        if(!form.getIdSpecialities().isEmpty()) {
            form.update(doctor);
            doctorRepository.save(doctor);

            for(Long idSpeciality : form.getIdSpecialities()) {
                exertSpecialityService.updateExertSpeciality(
                    crm,
                    idSpeciality,
                    new ExertSpecialityUpdateDTO(crm, idSpeciality)
                );
            }

            return new DoctorDTO(doctor);
        }

        throw new EntityNotFoundException("This Doctor doesn't have speciality");
    }

    @Transactional
    public DoctorDTO deleteDoctor(String crm) {
        Doctor doctor = FetchEntity.fetchEntity(crm, this.doctorRepository);

        doctorRepository.deleteByCrm(crm);

        return new DoctorDTO(doctor);
    }
}
