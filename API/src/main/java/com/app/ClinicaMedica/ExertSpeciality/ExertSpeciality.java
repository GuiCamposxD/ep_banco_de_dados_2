package com.app.ClinicaMedica.ExertSpeciality;

import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Speciality.Speciality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExertSpeciality {
    @EmbeddedId
    ExertSpecialityId id;

    @ManyToOne
    @MapsId("crm")
    @JoinColumn(name = "crm", referencedColumnName = "crm", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @MapsId("idSpeciality")
    @JoinColumn(name = "idSpeciality", referencedColumnName = "idSpeciality", nullable = false)
    private Speciality speciality;
}
