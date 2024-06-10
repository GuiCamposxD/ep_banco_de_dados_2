package com.app.ClinicaMedica.ExertSpeciality;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class ExertSpecialityId implements Serializable {
    private String crm;

    private Long idSpeciality;
}
