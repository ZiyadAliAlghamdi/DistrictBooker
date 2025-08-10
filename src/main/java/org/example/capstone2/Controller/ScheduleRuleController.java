package org.example.capstone2.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiResponse;
import org.example.capstone2.Model.ScheduleRule;
import org.example.capstone2.Service.ScheduleRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/schedule_rule")
@RequiredArgsConstructor
public class ScheduleRuleController {

    private final ScheduleRuleService scheduleRuleService;


    @GetMapping("/get")
    public ResponseEntity<?> getAllScheduleRule(){
        return ResponseEntity.ok(scheduleRuleService.getAllScheduleRule());
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSingleScheduleRule(@PathVariable Integer id){
        return ResponseEntity.ok(scheduleRuleService.getSingleScheduleRule(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addScheduleRule(@RequestBody @Valid ScheduleRule scheduleRule, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        scheduleRuleService.addScheduleRule(scheduleRule);
        return ResponseEntity.ok(new ApiResponse("<scheduleRole-controller/addScheduleRule> scheduleRole added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateScheduleRule(@PathVariable Integer id, @RequestBody @Valid ScheduleRule scheduleRule, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        scheduleRuleService.updateScheduleRule(id,scheduleRule);
        return ResponseEntity.ok(new ApiResponse("<scheduleRole-controller/updateScheduleRule> scheduleRole updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteScheduleRule(@PathVariable Integer id){
        scheduleRuleService.deleteScheduleRole(id);
        return ResponseEntity.ok(new ApiResponse("<scheduleRole-controller/deleteScheduleRule> scheduleRole deleted"));
    }
}
