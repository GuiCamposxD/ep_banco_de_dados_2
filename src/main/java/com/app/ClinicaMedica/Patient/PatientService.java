package com.app.ClinicaMedica.Patient;

import Util.FetchEntity;
import com.app.ClinicaMedica.Patient.DTO.PatientCreateDTO;
import com.app.ClinicaMedica.Patient.DTO.PatientDTO;
import com.app.ClinicaMedica.Patient.DTO.PatientUpdateDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientDTO> getPatients() {
        return PatientDTO.converter(patientRepository.findAll());
    }

    @Transactional
    public PatientDTO addNewPatient(PatientCreateDTO form) {
        Patient patient = form.converter();

        patientRepository.save(patient);

        return new PatientDTO(patient);
    }

    @Transactional
    public PatientDTO updatePatient(Long idPatient, PatientUpdateDTO form) {
        Patient patient = FetchEntity.fetchEntity(idPatient, this.patientRepository, "Patient");

        form.update(patient);
        patientRepository.save(patient);

        return new PatientDTO(patient);
    }

    @Transactional
    public PatientDTO deletePatient(Long idPatient) {
        Patient patient = FetchEntity.fetchEntity(idPatient, this.patientRepository, "Patient");

        patientRepository.deleteById(idPatient);

        return new PatientDTO(patient);
    }
}
