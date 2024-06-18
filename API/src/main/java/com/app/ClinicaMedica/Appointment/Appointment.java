package com.app.ClinicaMedica.Appointment;

import Enum.PaymentMethods;
import com.app.ClinicaMedica.Constraints.AppointmentDontExist;
import com.app.ClinicaMedica.Constraints.AppointmentTimeInSchedule;
import com.app.ClinicaMedica.Constraints.StartHourBeforeEndHour;
import com.app.ClinicaMedica.Diagnostic.Diagnostic;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Patient.Patient;
import com.app.ClinicaMedica.Speciality.Speciality;
import com.app.ClinicaMedica.Validators.TimeInterval;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@StartHourBeforeEndHour
@AppointmentDontExist
@AppointmentTimeInSchedule
public class Appointment implements TimeInterval {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "appointment_sequence")
    @SequenceGenerator(name = "appointment_sequence", sequenceName = "app_seq")
    private Long idAppointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crm", referencedColumnName = "crm", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSpeciality", referencedColumnName = "idSpeciality", nullable = false)
    private Speciality speciality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient", nullable = false)
    private Patient patient;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startHour;

    @NotNull
    private LocalTime endHour;

    @NotNull
    private Boolean isPaid;

    private Float paidAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethod;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Diagnostic> diagnostics;
}
