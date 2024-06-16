package Util;

import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Doctor.DoctorRepository;
import com.app.ClinicaMedica.ExertSpeciality.ExertSpeciality;
import com.app.ClinicaMedica.ExertSpeciality.ExertSpecialityId;
import com.app.ClinicaMedica.ExertSpeciality.ExertSpecialityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public class FetchEntity {
    public static <T> T fetchEntity(Long id, JpaRepository<T, Long> repository, String entityName) {
        return repository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(entityName + " with ID " + id + " does not exist")
        );
    }

    public static Doctor fetchEntity(String crm, DoctorRepository repository) {
        return repository.findByCrm(crm).orElseThrow(
            () -> new EntityNotFoundException("Doctor" + " with CRM " + crm + " does not exist")
        );
    }

    public static ExertSpeciality fetchEntity(String crm, Long idSpeciality, ExertSpecialityRepository repository) {
        return repository.findById(new ExertSpecialityId(crm, idSpeciality)).orElseThrow(
            () -> new EntityNotFoundException("Exert Speciality with CRM " + crm + " and Speciality ID " + idSpeciality + " does not exist")
        );
    }
}
