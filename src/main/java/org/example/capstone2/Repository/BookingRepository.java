package org.example.capstone2.Repository;

import org.example.capstone2.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Booking getBookingById(Integer id);

    List<Booking> findBookingByStatus(String status);

    @Query("""
           select b from Booking b
           where b.startTime >= :fromTs and b.endTime <= :toTs
           order by b.startTime asc
           """)
    List<Booking> findBetween(LocalDateTime from, LocalDateTime to);

    @Query("select b from Booking b where b.userId=?1")
    List<Booking> findBookingsByUserId(Integer userId);


    List<Booking> findBookingByStartTimeAndEndTime(LocalDateTime startTime, LocalDateTime endTime);

    @Query("select b from Booking b where b.facilityId = ?1 and b.startTime < ?3 and b.endTime > ?2")
    List<Booking> findConflictingBookings(Integer facilityId, LocalDateTime startTime, LocalDateTime endTime);

}
