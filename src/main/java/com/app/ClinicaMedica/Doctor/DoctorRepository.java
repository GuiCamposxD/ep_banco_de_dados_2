package com.app.ClinicaMedica.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE d.crm = ?1")
    Optional<Doctor> findByCrm(String crm);

    @Modifying
    @Query("DELETE FROM Doctor d WHERE d.crm = ?1")
    void deleteByCrm(String crm);
}
