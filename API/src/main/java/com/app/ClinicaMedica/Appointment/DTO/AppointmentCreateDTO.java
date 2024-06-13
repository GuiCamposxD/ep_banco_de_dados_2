package com.app.ClinicaMedica.Appointment.DTO;

import com.app.ClinicaMedica.Appointment.Appointment;
import com.app.ClinicaMedica.Doctor.Doctor;
import Enum.PaymentMethods;
import com.app.ClinicaMedica.Patient.Patient;
import com.app.ClinicaMedica.Speciality.Speciality;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class AppointmentCreateDTO {
    @NotEmpty
    private String crm;

    @NotNull
    private Long idSpeciality;

    @NotNull
    private Long idPatient;

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

    public Appointment converter(
        Doctor doctor,
        Speciality speciality,
        Patient patient
    ) {
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setSpeciality(speciality);
        appointment.setPatient(patient);
        appointment.setDate(this.getDate());
        appointment.setStartHour(this.getStartHour());
        appointment.setEndHour(this.getEndHour());
        appointment.setIsPaid(this.getIsPaid());
        appointment.setPaidAmount(this.getPaidAmount());
        appointment.setPaymentMethod(this.getPaymentMethod());

        return appointment;
    }
}
