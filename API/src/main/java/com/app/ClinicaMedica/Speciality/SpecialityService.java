package com.app.ClinicaMedica.Speciality;

import Util.FetchEntity;
import com.app.ClinicaMedica.Speciality.DTO.SpecialityCreateDTO;
import com.app.ClinicaMedica.Speciality.DTO.SpecialityDTO;
import com.app.ClinicaMedica.Speciality.DTO.SpecialityUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {
    private final SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public List<SpecialityDTO> getSpecialities() {
        return SpecialityDTO.converter(this.specialityRepository.findAllByOrderByIndexAsc());
    }

    public SpecialityDTO addNewSpeciality(SpecialityCreateDTO form) {
        Speciality speciality = form.converter();
        this.specialityRepository.save(speciality);

        return new SpecialityDTO(speciality);
    }

    public SpecialityDTO updateSpeciality(Long idSpeciality, SpecialityUpdateDTO updateForm) {
        Speciality speciality = FetchEntity.fetchEntity(idSpeciality, this.specialityRepository, "Speciality");

        updateForm.update(speciality);
        specialityRepository.save(speciality);

        return new SpecialityDTO(speciality);
    }

    public Object deleteSpeciality(Long idSpeciality) {
        Speciality speciality = FetchEntity.fetchEntity(idSpeciality, this.specialityRepository, "Speciality");

        specialityRepository.deleteById(idSpeciality);

        return new SpecialityDTO(speciality);
    }
}
