package com.app.ClinicaMedica.Validators;

import java.time.LocalTime;

public interface TimeInterval {
    LocalTime getStartHour();
    LocalTime getEndHour();
}
