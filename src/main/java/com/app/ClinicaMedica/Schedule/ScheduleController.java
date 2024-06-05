package com.app.ClinicaMedica.Schedule;

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

    @PostMapping
    public ResponseEntity<ScheduleDTO> addNewSchedule(@RequestBody ScheduleCreateDTO form) {
        ScheduleDTO schedule = scheduleService.addNewSchedule(form);
        return ResponseEntity.ok(schedule);
    }

    @PatchMapping(path = "{scheduleId}")
    public ResponseEntity<?> updateSchedule(
            @PathVariable("scheduleId") Long scheduleId,
            @RequestBody ScheduleUpdateDTO updateForm
    ) {
        try {
            return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, updateForm));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "{scheduleId}")
    public ResponseEntity<?> deleteSchedule(
            @PathVariable("scheduleId") Long scheduleId
    ) {
        try {
            return ResponseEntity.ok(scheduleService.deleteSchedule(scheduleId));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
