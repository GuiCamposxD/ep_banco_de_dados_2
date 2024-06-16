package com.app.ClinicaMedica.Schedule;

import Enum.WeekDays;
import com.app.ClinicaMedica.Schedule.DTO.ScheduleCreateDTO;
import com.app.ClinicaMedica.Schedule.DTO.ScheduleDTO;
import com.app.ClinicaMedica.Schedule.DTO.ScheduleUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping(path = "{crm}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByCrm(@PathVariable("crm") String crm) {
        return ResponseEntity.ok(scheduleService.getSchedulesByCrm(crm));
    }

    @GetMapping(path = "{crm}/{weekDay}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByCrmAndWeekDay(
        @PathVariable("crm") String crm,
        @PathVariable("weekDay") WeekDays weekDay
    ) {
        return ResponseEntity.ok(scheduleService.getSchedulesByCrmAndWeekDay(crm, weekDay));
    }

    @PostMapping
    public ResponseEntity<ScheduleDTO> addNewSchedule(@RequestBody ScheduleCreateDTO form) {
        return ResponseEntity.ok(scheduleService.addNewSchedule(form));
    }

    @PatchMapping(path = "{idSchedule}")
    public ResponseEntity<?> updateSchedule(
            @PathVariable("idSchedule") Long scheduleId,
            @RequestBody ScheduleUpdateDTO updateForm
    ) {
        try {
            return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, updateForm));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{idSchedule}")
    public ResponseEntity<?> deleteSchedule(
            @PathVariable("idSchedule") Long scheduleId
    ) {
        try {
            return ResponseEntity.ok(scheduleService.deleteSchedule(scheduleId));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
