package org.example.capstone2.Service;


import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiException;
import org.example.capstone2.Model.Facility;
import org.example.capstone2.Model.Rating;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.FacilityRepository;
import org.example.capstone2.Repository.RatingRepository;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final FacilityRepository facilityRepository;

    public List<Rating> getAllRatings(){
        return ratingRepository.findAll();
    }

    public Rating getSingleRating(Integer id){
        Rating rating = ratingRepository.getRatingById(id);

        if (rating ==null){
            throw new ApiException("<rating-service> rating not found");
        }
        return rating;
    }


    public void addRating(Rating rating){
        User user = userRepository.getUserById(rating.getUserId());

        if (user == null){
            throw new ApiException("<rating-service> user not found");
        }

        Facility facility = facilityRepository.getFacilityById(rating.getFacilityId());

        if (facility == null){
            throw new ApiException("<rating-service> facility not found");
        }

        ratingRepository.save(rating);
    }

    public void updateRating(Integer id, Rating rating){
        Rating oldRating = ratingRepository.getRatingById(id);

        if (oldRating == null){
            throw new ApiException("<rating-service> rating not found");
        }

        User user = userRepository.getUserById(rating.getUserId());

        if (user == null){
            throw new ApiException("<rating-service> user not found");
        }

        Facility facility = facilityRepository.getFacilityById(rating.getFacilityId());

        if (facility == null){
            throw new ApiException("<rating-service> facility not found");
        }

        oldRating.setUserId(rating.getUserId());
        oldRating.setFacilityId(rating.getFacilityId());
        oldRating.setRatingValue(rating.getRatingValue());
        oldRating.setComment(rating.getComment());
        ratingRepository.save(oldRating);
    }

    public void deleteRating(Integer id){
        Rating rating = ratingRepository.getRatingById(id);

        if (rating == null){
            throw new ApiException("<rating-service> rating not found");
        }

        ratingRepository.delete(rating);
    }


    //additional
    public Double getAvg(Integer facilityId){
        return ratingRepository.avgForFacility(facilityId);
    }
}
