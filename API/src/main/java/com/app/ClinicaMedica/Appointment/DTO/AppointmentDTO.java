package com.app.ClinicaMedica.Appointment.DTO;

import Enum.PaymentMethods;
import com.app.ClinicaMedica.Appointment.Appointment;
import com.app.ClinicaMedica.Doctor.DTO.DoctorDTO;
import com.app.ClinicaMedica.Patient.DTO.PatientDTO;
import com.app.ClinicaMedica.Speciality.DTO.SpecialityDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AppointmentDTO {
    @NotNull(message = "Id Appointment is mandatory")
    private final Long idAppointment;

    @NotNull(message = "Doctor is mandatory")
    private final DoctorDTO doctor;

    @NotNull(message = "Speciality is mandatory")
    private final SpecialityDTO speciality;

    @NotNull(message = "Patient id is mandatory")
    private final PatientDTO patient;

    @NotNull(message = "Date is mandatory")
    private final LocalDate date;

    @NotNull(message = "Start Hour id is mandatory")
    private final LocalTime startHour;

    @NotNull(message = "End Hour id is mandatory")
    private final LocalTime endHour;

    @NotNull(message = "Is Paid id is mandatory")
    private final Boolean isPaid;

    private final Float paidAmount;

    @Enumerated(EnumType.STRING)
    private final PaymentMethods paymentMethod;

    public AppointmentDTO(Appointment appointment) {
        this.idAppointment = appointment.getIdAppointment();
        this.doctor = new DoctorDTO(appointment.getDoctor());
        this.speciality = new SpecialityDTO(appointment.getSpeciality());
        this.patient = new PatientDTO(appointment.getPatient());
        this.date = appointment.getDate();
        this.startHour = appointment.getStartHour();
        this.endHour = appointment.getEndHour();
        this.isPaid = appointment.getIsPaid();
        this.paidAmount = appointment.getPaidAmount();
        this.paymentMethod = appointment.getPaymentMethod();
    }

    public static List<AppointmentDTO> converter(List<Appointment> appointments) {
        return appointments.stream().map(AppointmentDTO::new).collect(Collectors.toList());
    }
}
