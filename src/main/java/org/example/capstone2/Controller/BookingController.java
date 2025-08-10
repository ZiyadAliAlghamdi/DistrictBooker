
package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiResponse;
import org.example.capstone2.Model.Booking;
import org.example.capstone2.Service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllBookings(){
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSingleBooking(@PathVariable Integer id){
        return ResponseEntity.ok(bookingService.getSingleBooking(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBooking(@RequestBody @Valid Booking booking){
        bookingService.addBooking(booking);
        return ResponseEntity.ok(new ApiResponse("<booking-controller/addBooking>Booking added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Integer id, @RequestBody @Valid Booking booking){
        bookingService.updateBooking(id, booking);
        return ResponseEntity.ok(new ApiResponse("<booking-controller/updateBooking>Booking updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Integer id){
        bookingService.deleteBooking(id);
        return ResponseEntity.ok(new ApiResponse("<booking-controller/deleteBooking>Booking deleted successfully"));
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<?> acceptBooking(@PathVariable Integer id){
        bookingService.acceptBooking(id);
        return ResponseEntity.ok(new ApiResponse("<booking-controller/acceptBooking>Booking accepted successfully"));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Integer id){
        bookingService.cancelBooking(id);
        return ResponseEntity.ok(new ApiResponse("<booking-controller/cancelBooking>Booking cancelled successfully"));
    }

    @GetMapping("/pending")
    public ResponseEntity<?> getPendingBookings(){
        return ResponseEntity.ok(bookingService.getPendingBookings());
    }

    @GetMapping("/today")
    public ResponseEntity<?> getTodayBookings(){
        return ResponseEntity.ok(bookingService.getTodayBookings());
    }
}
