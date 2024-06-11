package com.app.ClinicaMedica.Validators;


import com.app.ClinicaMedica.Constraints.ExistsTimePeriod;
import com.app.ClinicaMedica.Doctor.Doctor;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidator;

public class ExistsTimePeriodValidator implements ConstraintValidator<ExistsTimePeriod, Doctor> {

    @Override
    public void initialize(ExistsTimePeriod constraintAnnotation){
    }

    @Override
    public boolean isValid(Doctor doctor, ConstraintValidatorContext context){
        for (int i =0; i <doctor.getSchedules().size(); i++){
            for (int j = 0; j < doctor.getSchedules().size(); j++){
                if (doctor.getSchedules().get(i).getWeekDay().equals(doctor.getSchedules().get(j).getWeekDay())){
                    if (doctor.getSchedules().get(i).getStartHour().isBefore(doctor.getSchedules().get(j).getEndHour()) && doctor.getSchedules().get(i).getEndHour().isAfter(doctor.getSchedules().get(j).getStartHour())){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
