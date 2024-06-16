package com.app.ClinicaMedica.Schedule;

import Enum.WeekDays;
import Util.FetchEntity;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Doctor.DoctorRepository;
import com.app.ClinicaMedica.Schedule.DTO.ScheduleCreateDTO;
import com.app.ClinicaMedica.Schedule.DTO.ScheduleDTO;
import com.app.ClinicaMedica.Schedule.DTO.ScheduleUpdateDTO;
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
        return ScheduleDTO.converter(scheduleRepository.findByDoctorCrm(crm));
    }

    public List<ScheduleDTO> getSchedulesByCrmAndWeekDay(String crm, WeekDays weekDay) {
        return ScheduleDTO.converter(scheduleRepository.findByDoctorCrmAndWeekDay(crm, weekDay));
    }

    @Transactional
    public ScheduleDTO addNewSchedule(ScheduleCreateDTO form) {
        Doctor doctor = FetchEntity.fetchEntity(form.getCrm(), this.doctorRepository);

        Schedule schedule = form.converter(doctor);
        scheduleRepository.save(schedule);
        return new ScheduleDTO(schedule);
    }

    @Transactional
    public ScheduleDTO updateSchedule(Long scheduleId, ScheduleUpdateDTO updateForm) {
        Schedule schedule = FetchEntity.fetchEntity(scheduleId, this.scheduleRepository, "Schedule");
        Doctor doctor = FetchEntity.fetchEntity(updateForm.getCrm(), this.doctorRepository);

        updateForm.update(schedule, doctor);

        return new ScheduleDTO(schedule);
    }

    @Transactional
    public ScheduleDTO deleteSchedule(Long scheduleId) {
        Schedule schedule = FetchEntity.fetchEntity(scheduleId, this.scheduleRepository, "Schedule");

        scheduleRepository.deleteById(scheduleId);

        return new ScheduleDTO(schedule);
    }
}
