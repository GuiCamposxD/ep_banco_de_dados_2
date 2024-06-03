package com.app.ClinicaMedica.Doctor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    private String crm;

    @NotEmpty
    private String doctorName;

    @NotEmpty
    private String doctorPhone;

    @Positive
    @NotNull
    private Float percentage;
}
