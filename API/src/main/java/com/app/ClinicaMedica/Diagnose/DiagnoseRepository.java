package com.app.ClinicaMedica.Diagnose;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {
    List<Diagnose> findByDiagnostic_IdDiagnostic(Long idDiagnostic);
}
