
package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiException;
import org.example.capstone2.Model.Booking;
import org.example.capstone2.Model.Facility;
import org.example.capstone2.Model.ScheduleRule;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.BookingRepository;
import org.example.capstone2.Repository.FacilityRepository;
import org.example.capstone2.Repository.ScheduleRuleRepository;
import org.example.capstone2.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FacilityRepository facilityRepository;
    private final ScheduleRuleRepository scheduleRuleRepository;
    private final MailService mailService;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getSingleBooking(Integer id) {
        Booking booking = bookingRepository.getBookingById(id);

        if (booking == null) {
            throw new ApiException("<booking-service> booking not found");
        }

        return booking;
    }

    public void addBooking(Booking booking) {
        User user = userRepository.getUserById(booking.getUserId());

        if (user == null) {
            throw new ApiException("<booking-service> user not found");
        }

        Facility facility = facilityRepository.getFacilityById(booking.getFacilityId());

        if (facility == null) {
            throw new ApiException("<booking-service> facility not found");
        }

        //check other bookings if there is conflict
        List<Booking> conflictingBookings = bookingRepository.findConflictingBookings(booking.getFacilityId(), booking.getStartTime(), booking.getEndTime());
        if (!conflictingBookings.isEmpty()) {
            throw new ApiException("<booking-service> there is some conflicts in start time and end time");
        }


        //add validation for scheduleRule
        ScheduleRule scheduleRule = scheduleRuleRepository.findScheduleRuleByFacilityId(booking.getFacilityId());
        if (scheduleRule == null) {
            throw new ApiException("schedule rule not found for this facility");
        }

        String requestedDay = booking.getStartTime().getDayOfWeek().toString();

        if (!scheduleRule.getDaysOfWeek().contains(requestedDay)) {
            throw new ApiException("the facility is closed on " + requestedDay);
        }

        if (booking.getStartTime().toLocalTime().isBefore(scheduleRule.getOpenTime()) || booking.getEndTime().toLocalTime().isAfter(scheduleRule.getCloseTime())) {
            throw new ApiException("the facility is closed at the requested time");
        }


        booking.setStatus("pending");
        bookingRepository.save(booking);
        mailService.sendBookingConfirmationEmail(booking, user, facility);
    }

    public void updateBooking(Integer id, Booking booking) {
        Booking oldBooking = bookingRepository.getBookingById(id);

        if (oldBooking == null) {
            throw new ApiException("<booking-service> booking not found");
        }

        User user = userRepository.getUserById(booking.getUserId());

        if (user == null) {
            throw new ApiException("<booking-service> user not found");
        }

        Facility facility = facilityRepository.getFacilityById(booking.getFacilityId());

        if (facility == null) {
            throw new ApiException("<booking-service> facility not found");
        }

        oldBooking.setUserId(booking.getUserId());
        oldBooking.setFacilityId(booking.getFacilityId());
        oldBooking.setStartTime(booking.getStartTime());
        oldBooking.setEndTime(booking.getEndTime());
        oldBooking.setStatus(booking.getStatus());
        bookingRepository.save(oldBooking);
    }

    public void deleteBooking(Integer id) {
        Booking booking = bookingRepository.getBookingById(id);

        if (booking == null) {
            throw new ApiException("<booking-service> booking not found");
        }

        bookingRepository.delete(booking);
    }

    public void acceptBooking(Integer id) {
        Booking booking = bookingRepository.getBookingById(id);

        if (booking == null) {
            throw new ApiException("<booking-service> booking not found");
        }

        User user = userRepository.getUserById(booking.getUserId());

        if (user == null) {
            throw new ApiException("<booking-service> user not found");
        }



        booking.setStatus("approved");
        bookingRepository.save(booking);
        Facility facility = facilityRepository.getFacilityById(booking.getFacilityId());
        mailService.sendBookingAcceptedEmail(booking, user, facility);
    }


    public void cancelBooking(Integer id) {
        Booking booking = bookingRepository.getBookingById(id);

        if (booking == null) {
            throw new ApiException("<booking-service> booking not found");
        }

        booking.setStatus("cancelled");
        bookingRepository.save(booking);

        User user = userRepository.getUserById(booking.getUserId());
        Facility facility = facilityRepository.getFacilityById(booking.getFacilityId());
        mailService.sendBookingCancelledEmail(booking, user, facility);
    }


    public List<Booking> getPendingBookings() {
        return bookingRepository.findBookingByStatus("pending");
    }


    public List<Booking> getTodayBookings() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        return bookingRepository.findBetween(start, end);
    }


































    @Scheduled(cron = "0 * * * * *")
    public void updateBookingStatus() {
        List<Booking> approvedBookings = bookingRepository.findBookingByStatus("approved");
        for (Booking booking : approvedBookings) {
            if (booking.getEndTime().isBefore(LocalDateTime.now())) {
                booking.setStatus("done");
                bookingRepository.save(booking);
                logger.info("<updateBookingStatus is working>");
            }
        }
    }
}