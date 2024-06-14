package com.app.ClinicaMedica.Diagnostic;

import com.app.ClinicaMedica.Appointment.Appointment;
import com.app.ClinicaMedica.Constraints.StartHourBeforeEndHour;
import com.app.ClinicaMedica.Diagnose.Diagnose;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Diagnostic {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "diagnostic_sequence")
    @SequenceGenerator(name = "diagnostic_sequence", sequenceName = "dia_seq")
    private Long idDiagnostic;

    private String recommendedTreatment;

    private String prescriptionMedicines;

    private String observations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAppointment", referencedColumnName = "idAppointment", nullable = false)
    private Appointment appointment;

    @OneToMany(mappedBy = "diagnostic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diagnose> diagnoses;
}
