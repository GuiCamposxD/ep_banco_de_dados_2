package com.app.ClinicaMedica.Disease;

import Util.FetchEntity;
import com.app.ClinicaMedica.Disease.DTO.DiseaseCreateDTO;
import com.app.ClinicaMedica.Disease.DTO.DiseaseDTO;
import com.app.ClinicaMedica.Disease.DTO.DiseaseUpdateDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {
    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public List<DiseaseDTO> getDiseases() {
        return DiseaseDTO.converter(diseaseRepository.findAll());
    }

    @Transactional
    public DiseaseDTO addNewDisease(DiseaseCreateDTO form) {
        Disease disease = form.converter();

        diseaseRepository.save(disease);

        return new DiseaseDTO(disease);
    }

    @Transactional
    public DiseaseDTO updateDisease(Long idDisease, DiseaseUpdateDTO form) {
        Disease disease = FetchEntity.fetchEntity(idDisease, this.diseaseRepository, "Disease");

        form.update(disease);
        diseaseRepository.save(disease);

        return new DiseaseDTO(disease);
    }

    @Transactional
    public DiseaseDTO deleteDisease(Long idDisease) {
        Disease disease = FetchEntity.fetchEntity(idDisease, this.diseaseRepository, "Disease");

        diseaseRepository.deleteById(idDisease);

        return new DiseaseDTO(disease);
    }
}
