package com.app.ClinicaMedica.ExertSpeciality;

import com.app.ClinicaMedica.Speciality.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExertSpecialityRepository extends JpaRepository<ExertSpeciality, ExertSpecialityId> {
    @Query("SELECT es.speciality FROM ExertSpeciality es WHERE es.doctor.crm = :crm")
    List<Speciality> findSpecialitiesByDoctorCrm(String crm);
}
