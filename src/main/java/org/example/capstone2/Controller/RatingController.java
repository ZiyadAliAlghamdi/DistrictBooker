package org.example.capstone2.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiResponse;
import org.example.capstone2.Model.Rating;
import org.example.capstone2.Service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllRatings(){
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSingleRating(@PathVariable Integer id){
        return ResponseEntity.ok(ratingService.getSingleRating(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRating(@RequestBody @Valid Rating rating, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        ratingService.addRating(rating);
        return ResponseEntity.ok(new ApiResponse("<rating-controller/addRating> rating added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRating(@PathVariable Integer id, @RequestBody @Valid Rating rating, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        ratingService.updateRating(id,rating);
        return ResponseEntity.ok(new ApiResponse("<rating-controller/updateRating> rating updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable Integer id){
        ratingService.deleteRating(id);
        return ResponseEntity.ok(new ApiResponse("<rating-controller/addRating> rating deleted"));
    }

    @GetMapping("/avg")
    public ResponseEntity<?> getAvg(Integer id){
        return ResponseEntity.ok(ratingService.getAvg(id));
    }


}
