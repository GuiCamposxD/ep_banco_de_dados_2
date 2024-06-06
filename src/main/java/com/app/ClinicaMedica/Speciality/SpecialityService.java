package com.app.ClinicaMedica.Speciality;

import com.app.ClinicaMedica.Doctor.DTO.DoctorDTO;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.ExertSpeciality.ExertSpecialityService;
import com.app.ClinicaMedica.Speciality.DTO.SpecialityCreateDTO;
import com.app.ClinicaMedica.Speciality.DTO.SpecialityDTO;
import com.app.ClinicaMedica.Speciality.DTO.SpecialityUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {
    private final SpecialityRepository specialityRepository;
    private final ExertSpecialityService exertSpecialityService;

    @Autowired
    public SpecialityService(SpecialityRepository specialityRepository, ExertSpecialityService exertSpecialityService) {
        this.specialityRepository = specialityRepository;
        this.exertSpecialityService = exertSpecialityService;
    }

    public List<SpecialityDTO> getSpecialities() {
        this.exertSpecialityService.getExertSpeciality();
        return SpecialityDTO.converter(this.specialityRepository.findAll());
    }

    public SpecialityDTO addNewSpeciality(SpecialityCreateDTO form) {
        Speciality speciality = form.converter();
        this.specialityRepository.save(speciality);

        return new SpecialityDTO(speciality);
    }

    public SpecialityDTO updateSpeciality(Long idSpeciality, SpecialityUpdateDTO updateForm) {
        Speciality speciality = specialityRepository.findById(idSpeciality).orElseThrow(
                () -> new IllegalStateException("Speciality with this " + idSpeciality + " id does not exists")
        );

        updateForm.update(speciality);
        specialityRepository.save(speciality);

        return new SpecialityDTO(speciality);
    }

    public Object deleteSpeciality(Long idSpeciality) {
        Speciality speciality = specialityRepository.findById(idSpeciality).orElseThrow(
                () -> new IllegalStateException("Speciality with this " + idSpeciality + " id does not exists")
        );

        specialityRepository.deleteById(idSpeciality);

        return new SpecialityDTO(speciality);
    }
}
