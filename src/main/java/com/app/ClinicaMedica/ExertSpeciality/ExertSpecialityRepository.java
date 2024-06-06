package com.app.ClinicaMedica.ExertSpeciality;

import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Speciality.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExertSpecialityRepository extends JpaRepository<ExertSpeciality, ExertSpecialityId> {
    Optional<ExertSpeciality> findByDoctorAndSpeciality(Doctor doctor, Speciality speciality);
}
