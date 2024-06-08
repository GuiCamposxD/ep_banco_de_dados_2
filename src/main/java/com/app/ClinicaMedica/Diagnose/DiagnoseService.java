package com.app.ClinicaMedica.Diagnose;

import Util.FetchEntity;
import com.app.ClinicaMedica.Diagnose.DTO.DiagnoseCreateDTO;
import com.app.ClinicaMedica.Diagnose.DTO.DiagnoseDTO;
import com.app.ClinicaMedica.Diagnose.DTO.DiagnoseUpdateDTO;
import com.app.ClinicaMedica.Diagnostic.Diagnostic;
import com.app.ClinicaMedica.Diagnostic.DiagnosticRepository;
import com.app.ClinicaMedica.Disease.Disease;
import com.app.ClinicaMedica.Disease.DiseaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnoseService {
    private final DiagnoseRepository diagnoseRepository;
    private final DiagnosticRepository diagnosticRepository;
    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiagnoseService(
            DiagnoseRepository diagnoseRepository,
            DiagnosticRepository diagnosticRepository,
            DiseaseRepository diseaseRepository
    ) {
        this.diagnoseRepository = diagnoseRepository;
        this.diagnosticRepository = diagnosticRepository;
        this.diseaseRepository = diseaseRepository;
    }

    public List<DiagnoseDTO> getDiagnoses() {
        return DiagnoseDTO.converter(diagnoseRepository.findAll());
    }

    @Transactional
    public DiagnoseDTO addNewDiagnose(DiagnoseCreateDTO form) {
        Diagnostic diagnostic = FetchEntity.fetchEntity(form.getIdDiagnostic(), this.diagnosticRepository, "Diagnostic");
        Disease disease = FetchEntity.fetchEntity(form.getIdDisease(), this.diseaseRepository, "Disease");

        Diagnose diagnose = form.converter(diagnostic, disease);

        diagnoseRepository.save(diagnose);

        return new DiagnoseDTO(diagnose);
    }

    @Transactional
    public DiagnoseDTO updateDiagnose(Long idDiagnose, DiagnoseUpdateDTO form) {
        Diagnose diagnose = FetchEntity.fetchEntity(idDiagnose, this.diagnoseRepository, "Diagnose");
        Diagnostic diagnostic = FetchEntity.fetchEntity(form.getIdDiagnostic(), this.diagnosticRepository, "Diagnostic");
        Disease disease = FetchEntity.fetchEntity(form.getIdDisease(), this.diseaseRepository, "Disease");

        form.update(diagnose, diagnostic, disease);
        diagnoseRepository.save(diagnose);

        return new DiagnoseDTO(diagnose);
    }

    @Transactional
    public DiagnoseDTO deleteDiagnose(Long idDiagnose) {
        Diagnose diagnose = FetchEntity.fetchEntity(idDiagnose, this.diagnoseRepository, "Diagnose");

        diagnoseRepository.deleteById(idDiagnose);

        return new DiagnoseDTO(diagnose);
    }
}
