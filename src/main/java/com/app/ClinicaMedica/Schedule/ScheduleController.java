package com.app.ClinicaMedica.Schedule;

import com.app.ClinicaMedica.Schedule.DTO.ScheduleCreateDTO;
import com.app.ClinicaMedica.Schedule.DTO.ScheduleDTO;
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
        System.out.println(schedule);
        return ResponseEntity.ok(schedule);
    }
}
