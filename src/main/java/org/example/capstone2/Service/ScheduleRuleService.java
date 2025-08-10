package org.example.capstone2.Service;


import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiException;
import org.example.capstone2.Model.Admin;
import org.example.capstone2.Model.Facility;
import org.example.capstone2.Model.ScheduleRule;
import org.example.capstone2.Repository.AdminRepository;
import org.example.capstone2.Repository.FacilityRepository;
import org.example.capstone2.Repository.ScheduleRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleRuleService {

    private final ScheduleRuleRepository scheduleRuleRepository;
    private final FacilityRepository facilityRepository;
    private final AdminRepository adminRepository;

    public List<ScheduleRule> getAllScheduleRule(){
        return scheduleRuleRepository.findAll();
    }


    public ScheduleRule getSingleScheduleRule(Integer id){
        ScheduleRule scheduleRule = scheduleRuleRepository.getScheduleRuleById(id);

        if (scheduleRule == null){
            throw new ApiException("<scheduleRule-service> scheduleRule not found");
        }

        return scheduleRule;
    }


    public void addScheduleRule(ScheduleRule scheduleRule){

        Facility facility = facilityRepository.getFacilityById(scheduleRule.getFacilityId());

        if (facility == null){
            throw new ApiException("<scheduleRule-service> facility not found");
        }


        scheduleRuleRepository.save(scheduleRule);
    }

    public void updateScheduleRule(Integer id, ScheduleRule scheduleRule){
        ScheduleRule oldScheduleRule = scheduleRuleRepository.getScheduleRuleById(id);
        Facility facility = facilityRepository.getFacilityById(scheduleRule.getFacilityId());

        if (facility == null){
            throw new ApiException("<scheduleRule-service> facility not found");
        }

        if (oldScheduleRule == null){
            throw new ApiException("<scheduleRule-service> scheduleRule not found");
        }

        oldScheduleRule.setFacilityId(scheduleRule.getFacilityId());
        oldScheduleRule.setDaysOfWeek(scheduleRule.getDaysOfWeek());
        oldScheduleRule.setOpenTime(scheduleRule.getOpenTime());
        oldScheduleRule.setCloseTime(scheduleRule.getCloseTime());
        scheduleRuleRepository.save(oldScheduleRule);
    }

    public void deleteScheduleRole(Integer id){
        ScheduleRule scheduleRule = scheduleRuleRepository.getScheduleRuleById(id);

        if (scheduleRule == null){
            throw new ApiException("<scheduleRole-service> scheduleRule not found");
        }

        scheduleRuleRepository.delete(scheduleRule);
    }
}
