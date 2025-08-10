package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiResponse;
import org.example.capstone2.Model.Neighborhood;
import org.example.capstone2.Service.NeighborhoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/neighborhood")
@RequiredArgsConstructor
public class NeighborhoodController {
    private final NeighborhoodService neighborhoodService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllNeighborhoods(){
        return ResponseEntity.ok(neighborhoodService.getAllNeighborhoods());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getNeighborhoodById(@PathVariable Integer id){
        return ResponseEntity.ok(neighborhoodService.getNeighborhoodById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNeighborhood(@RequestBody @Valid Neighborhood neighborhood, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        neighborhoodService.addNeighborhood(neighborhood);
        return ResponseEntity.ok(new ApiResponse("<neighborhood-controller/addNeighborhood> Neighborhood added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNeighborhood(@PathVariable Integer id, @RequestBody @Valid Neighborhood neighborhood, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        neighborhoodService.updateNeighborhood(id, neighborhood);
        return ResponseEntity.ok(new ApiResponse("<neighborhood-controller/updateNeighborhood> Neighborhood updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNeighborhood(@PathVariable Integer id){
        neighborhoodService.deleteNeighborhood(id);
        return ResponseEntity.ok(new ApiResponse("<neighborhood-controller/deleteNeighborhood> Neighborhood deleted successfully"));
    }
}
