package com.app.ClinicaMedica.Disease;

import com.app.ClinicaMedica.Diagnose.Diagnose;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Disease {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "disease_sequence")
    @SequenceGenerator(name = "disease_sequence", sequenceName = "dis_seq")
    private Long idDisease;

    @NotEmpty
    private String diseaseName;

    @OneToMany(mappedBy = "disease", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diagnose> diagnoses;
}
