package org.example.capstone2.Repository;

import org.example.capstone2.Model.ScheduleRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRuleRepository extends JpaRepository<ScheduleRule,Integer> {
    ScheduleRule getScheduleRuleById(Integer id);

    ScheduleRule findScheduleRuleByFacilityId(Integer facilityId);

    @Query(value = "SELECT DISTINCT sr.facility_id FROM schedule_rule sr WHERE FIND_IN_SET(?1, sr.days_of_week) > 0 AND ?2 BETWEEN sr.open_time AND sr.close_time", nativeQuery = true)
    List<Integer> findFacilityIdsOpenNow(String day, LocalTime time);



    @Query(value = "SELECT DISTINCT sr.facility_id FROM schedule_rule sr WHERE FIND_IN_SET(:day, sr.days_of_week) > 0 AND :time BETWEEN sr.open_time AND sr.close_time", nativeQuery = true)
    List<Integer> findFacilityIdsByDayAndTime(@Param("day") String day, @Param("time") LocalTime time);




}