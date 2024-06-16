package com.app.ClinicaMedica.Schedule;

import Enum.WeekDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByDoctorCrm(String crm);
    List<Schedule> findByDoctorCrmAndWeekDay(String crm, WeekDays weekDay);
    List<Schedule> findAllByIdSchedule(Long idSchedule);
}
