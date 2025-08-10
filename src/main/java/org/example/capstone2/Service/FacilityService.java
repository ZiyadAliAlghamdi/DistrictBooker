package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiException;
import org.example.capstone2.Model.*;
import org.example.capstone2.Repository.*;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final CategoryRepository categoryRepository;
    private final ScheduleRuleRepository scheduleRuleRepository;
    private final RatingRepository ratingRepository;
    private final AdminRepository adminRepository;

    public List<Facility> getAllFacilities(){
        return facilityRepository.findAll();
    }

    public Facility getSingleFacility(Integer id){
        Facility facility = facilityRepository.getFacilityById(id);

        if (facility == null){
            throw new ApiException("<facility-service> facility not found");
        }

        return facility;
    }

    public void addFacility(Facility facility){
        Category category = categoryRepository.getCategoryById(facility.getCategoryId());

        if (category == null){
            throw new ApiException("<facility-service> category not found");
        }

        Admin admin = adminRepository.getAdminById(facility.getAdminId());

        if (admin == null){
            throw new ApiException("<facility-service> unauthorized access");
        }



        facilityRepository.save(facility);
    }

    public void updateFacility(Integer id, Facility facility){
        Facility oldFacility = facilityRepository.getFacilityById(id);

        if (oldFacility == null){
            throw new ApiException("<facility-service> facility not found");
        }

        Category category = categoryRepository.getCategoryById(facility.getCategoryId());

        if (category == null){
            throw new ApiException("<facility-service> category not found");
        }

        Admin admin = adminRepository.getAdminById(facility.getAdminId());

        if (admin == null){
            throw new ApiException("<facility-service> unauthorized access");
        }



        oldFacility.setName(facility.getName());
        oldFacility.setDescription(facility.getDescription());
        oldFacility.setCategoryId(facility.getCategoryId());
        oldFacility.setNeighborhoodId(facility.getNeighborhoodId());
        oldFacility.setCapacity(facility.getCapacity());
        facilityRepository.save(oldFacility);
    }

    public void deleteFacility(Integer id){
        Facility facility = facilityRepository.getFacilityById(id);

        if (facility == null){
            throw new ApiException("<facility-service> facility not found");
        }

        facilityRepository.delete(facility);
    }


    ///extras


    public List<Facility> getFacilitiesByNeighborhood(Integer neighborhoodId){
        return facilityRepository.findFacilitiesByNeighborhood(neighborhoodId);
    }


    public List<Facility> getCurrentlyOpen(Integer neighborhoodId){

        String day = LocalDate.now().getDayOfWeek().name();
        LocalTime time = LocalTime.now();

        List<Integer> ids = scheduleRuleRepository.findFacilityIdsOpenNow(day,time);
        return facilityRepository.findByIdsWithFilter(ids,neighborhoodId);
    }


    public List<Facility> getByDayAndTime(String day, String time,Integer neighborhoodId){

        LocalTime t = LocalTime.parse(time);

        List<Integer> ids = scheduleRuleRepository.findFacilityIdsByDayAndTime(day.toUpperCase(),t);

        return facilityRepository.findByIdsWithFilter(ids,neighborhoodId);
    }

    public List<Rating> getFacilityRatings(Integer facilityId){
        Facility facility = facilityRepository.getFacilityById(facilityId);

        if (facility == null){
            throw new ApiException("<facility-service> facility not found");
        }

        return ratingRepository.findByFacilityId(facility.getId());
    }
}
