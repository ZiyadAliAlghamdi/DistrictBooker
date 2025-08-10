package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiResponse;
import org.example.capstone2.Model.City;
import org.example.capstone2.Service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCities(){
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCityById(@PathVariable Integer id){
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCity(@RequestBody @Valid City city, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        cityService.addCity(city);
        return ResponseEntity.ok(new ApiResponse("<city-controller/addCity> City added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCity(@PathVariable Integer id, @RequestBody @Valid City city, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        cityService.updateCity(id, city);
        return ResponseEntity.ok(new ApiResponse("<city-controller/updateCity> City updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer id){
        cityService.deleteCity(id);
        return ResponseEntity.ok(new ApiResponse("<city-controller/deleteCity> City deleted successfully"));
    }
}
