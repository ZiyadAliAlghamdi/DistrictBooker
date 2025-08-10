package org.example.capstone2.Repository;


import org.example.capstone2.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
    Rating getRatingById(Integer id);

    @Query("select r from Rating r where r.facilityId =?1")
    List<Rating> findByFacilityId(Integer facilityId);

    @Query("select avg(r.ratingValue) from Rating r where r.facilityId = :facilityId")
    Double avgForFacility(Integer facilityId);

    @Query("select r from Rating r where r.userId =?1")
    List<Rating> findRatingByUserId(Integer userId);
}
