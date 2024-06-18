package com.app.ClinicaMedica.Validators;

import com.app.ClinicaMedica.Appointment.Appointment;
import com.app.ClinicaMedica.Constraints.AppointmentDontExist;
import com.app.ClinicaMedica.Doctor.Doctor;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class AppointmentDontExistValidator implements ConstraintValidator<AppointmentDontExist, Appointment> {
    @Override
    public void initialize(AppointmentDontExist constraintAnnotation) {
    }

    @Override
    public boolean isValid(Appointment appointment, ConstraintValidatorContext context) {
        LocalDate dateAppointment = appointment.getDate();
        LocalTime startHourAppointment = appointment.getStartHour();
        LocalTime endHourAppointment = appointment.getEndHour();

        Doctor doctor = appointment.getDoctor();
        Set<Appointment> appointmentList = doctor.getAppointments();

        for (Appointment a : appointmentList) {
            LocalDate existingDate = a.getDate();
            LocalTime existingStartHour = a.getStartHour();
            LocalTime existingEndHour = a.getEndHour();

            if (existingDate.equals(dateAppointment)) {
                boolean isOverlapping = startHourAppointment.isBefore(existingEndHour) && endHourAppointment.isAfter(existingStartHour);
                if (isOverlapping) {
                    return false;
                }
            }
        }

        return true;
    }
}
