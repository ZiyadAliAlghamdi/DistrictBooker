package org.example.capstone2.Controller;

import org.example.capstone2.ApiResponse.ApiResponse;
import org.example.capstone2.Model.Feedback;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.Service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllFeedback(){
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSingleFeedback(@PathVariable Integer id){
        return ResponseEntity.ok(feedbackService.getSingleFeedback(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFeedback(@RequestBody @Valid Feedback feedback){
        feedbackService.addFeedback(feedback);
        return ResponseEntity.ok(new ApiResponse("<feedback-controller/addFeedback>Feedback added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFeedback(@PathVariable Integer id, @RequestBody @Valid Feedback feedback){
        feedbackService.updateFeedback(id, feedback);
        return ResponseEntity.ok(new ApiResponse("<feedback-controller/updateFeedback>Feedback updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Integer id){
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok(new ApiResponse("<feedback-controller/deleteFeedback>Feedback deleted successfully"));
    }

    @GetMapping("/get_new")
    public ResponseEntity<?> getNewFeedback(){
        return ResponseEntity.ok(feedbackService.getNewFeedbacks());
    }

    @PutMapping("/set_in_progress")
    public ResponseEntity<?> setFeedbackToInProgress(@RequestParam Integer id){
        feedbackService.setFeedbackToInProgress(id);
        return ResponseEntity.ok(new ApiResponse("feedback set to IN_PROGRESS"));
    }

    @PutMapping("/close_feedback")
    public ResponseEntity<?> closeFeedback(@RequestParam Integer feedbackId, @RequestParam String message){
        feedbackService.closeFeedback(feedbackId,message);
        return ResponseEntity.ok(new ApiResponse("feedback set to closed"));
    }


}
