package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiResponse;
import org.example.capstone2.Model.Facility;
import org.example.capstone2.Service.FacilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/facility")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllFacilities(){
        return ResponseEntity.ok(facilityService.getAllFacilities());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSingleFacility(@PathVariable Integer id){
        return ResponseEntity.ok(facilityService.getSingleFacility(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFacility(@RequestBody @Valid Facility facility){
        facilityService.addFacility(facility);
        return ResponseEntity.ok(new ApiResponse("<facility-controller/addFacility>Facility added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFacility(@PathVariable Integer id, @RequestBody @Valid Facility facility){
        facilityService.updateFacility(id, facility);
        return ResponseEntity.ok(new ApiResponse("<facility-controller/updateFacility>Facility updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFacility(@PathVariable Integer id){
        facilityService.deleteFacility(id);
        return ResponseEntity.ok(new ApiResponse("<facility-controller/deleteFacility>Facility deleted successfully"));
    }

    @GetMapping("/neighborhood")
    public ResponseEntity<?> getFacilitiesByNeighborhood(@RequestParam Integer id){
        return ResponseEntity.ok(facilityService.getFacilitiesByNeighborhood(id));
    }

    @GetMapping("/currently_open")
    public ResponseEntity<?> getCurrentlyOpen(@RequestParam Integer id){
        return ResponseEntity.ok(facilityService.getCurrentlyOpen(id));
    }

    @GetMapping("/day_and_time")
    public ResponseEntity<?> getByDayAndTime(@RequestParam String day, @RequestParam String time, @RequestParam Integer neighborhoodId){
        return ResponseEntity.ok(facilityService.getByDayAndTime(day, time, neighborhoodId));
    }

    @GetMapping("/ratings")
    public ResponseEntity<?> getFacilityRatings(@RequestParam Integer id){
        return ResponseEntity.ok(facilityService.getFacilityRatings(id));
    }
}
