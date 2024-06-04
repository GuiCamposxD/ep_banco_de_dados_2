package com.app.ClinicaMedica.Schedule;

import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Doctor.DoctorRepository;
import com.app.ClinicaMedica.Schedule.DTO.ScheduleCreateDTO;
import com.app.ClinicaMedica.Schedule.DTO.ScheduleDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, DoctorRepository doctorRepository) {
        this.scheduleRepository = scheduleRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<ScheduleDTO> getSchedulesByCrm(String crm) {
        List<Schedule> schedules = scheduleRepository.findByDoctorCrm(crm);
        return ScheduleDTO.converter(schedules);
    }

    @Transactional
    public ScheduleDTO addNewSchedule(ScheduleCreateDTO form) {
        Doctor doctor = doctorRepository.findById(form.getCrm())
                .orElseThrow(() -> new IllegalStateException("Doctor with CRM " + form.getCrm() + " does not exist"));

        Schedule schedule = form.converter(doctor);
        scheduleRepository.save(schedule);
        return new ScheduleDTO(schedule);
    }
}
