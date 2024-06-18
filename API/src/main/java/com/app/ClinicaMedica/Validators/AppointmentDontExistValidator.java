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
        LocalDate date_appointment = appointment.getDate();
        LocalTime start_hour_appointment = appointment.getStartHour();
        LocalTime end_hour_appointment = appointment.getEndHour();

        Doctor doctor = appointment.getDoctor();
        Set<Appointment> appointmentList = doctor.getAppointments();

        for (Appointment a:appointmentList){
            if (a.getStartHour().equals(start_hour_appointment)
                || a.getEndHour().equals(end_hour_appointment)
                || a.getDate().equals(date_appointment)){
                    return false;
            }
        }

        return true;


    }
}
