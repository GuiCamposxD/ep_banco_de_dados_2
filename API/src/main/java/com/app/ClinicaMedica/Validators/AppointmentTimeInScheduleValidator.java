package com.app.ClinicaMedica.Validators;

import com.app.ClinicaMedica.Appointment.Appointment;
import com.app.ClinicaMedica.Constraints.AppointmentTimeInSchedule;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Schedule.Schedule;
import Enum.WeekDays;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class AppointmentTimeInScheduleValidator implements ConstraintValidator<AppointmentTimeInSchedule, Appointment> {
    @Override
    public void initialize(AppointmentTimeInSchedule constraintAnnotation) {
    }

    @Override
    public boolean isValid(Appointment appointment, ConstraintValidatorContext context) {
        LocalDate dateAppointment = appointment.getDate();
        LocalTime startHourAppointment = appointment.getStartHour();
        LocalTime endHourAppointment = appointment.getEndHour();

        Doctor doctor = appointment.getDoctor();
        List <Schedule> schedules = doctor.getSchedules();

        WeekDays appointmentWeekDay = switch (dateAppointment.getDayOfWeek()) {
            case DayOfWeek.MONDAY -> WeekDays.MONDAY;
            case DayOfWeek.TUESDAY -> WeekDays.TUESDAY;
            case DayOfWeek.WEDNESDAY -> WeekDays.WEDNESDAY;
            case DayOfWeek.THURSDAY -> WeekDays.THURSDAY;
            case DayOfWeek.FRIDAY -> WeekDays.FRIDAY;
            case DayOfWeek.SATURDAY -> WeekDays.SATURDAY;
            case DayOfWeek.SUNDAY -> WeekDays.SUNDAY;
            default -> null;
        };

        for (Schedule s:schedules) {
            WeekDays weekDay = s.getWeekDay();
            LocalTime scheduleStartHour = s.getStartHour();
            LocalTime scheduleEndHour = s.getEndHour();

            if (appointmentWeekDay.equals(weekDay)){
                if (startHourAppointment.isAfter(scheduleStartHour)
                && endHourAppointment.isBefore(scheduleEndHour)) {
                    return true;
                }
            }
        }
        return false;
    }
}
