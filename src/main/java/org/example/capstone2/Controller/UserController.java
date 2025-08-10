package org.example.capstone2.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiResponse;
import org.example.capstone2.Model.User;
import org.example.capstone2.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSingleUser(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getSingleUser(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.ok(new ApiResponse("<user-controller/addUser> user added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id, user);
        return ResponseEntity.ok(new ApiResponse("<user-service/updateUser> user updated successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("<user-controller/userDelete> user deleted successfully"));
    }

    @GetMapping("/history/booking")
    public ResponseEntity<?> getBookingHistory(@RequestParam Integer userId){
        return ResponseEntity.ok(userService.getBookingHistory(userId));
    }

    @GetMapping("/history/rating")
    public ResponseEntity<?> getRatingHistory(@RequestParam Integer userId){
        return ResponseEntity.ok(userService.getRatingHistory(userId));
    }

    @GetMapping("/history/feedback")
    public ResponseEntity<?> getFeedbackHistory(@RequestParam Integer userId){
        return ResponseEntity.ok(userService.getFeedbackHistory(userId));
    }

}
