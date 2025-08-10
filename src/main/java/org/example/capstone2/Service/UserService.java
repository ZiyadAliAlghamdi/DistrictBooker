package org.example.capstone2.Service;


import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiException;
import org.example.capstone2.Model.Booking;
import org.example.capstone2.Model.Feedback;
import org.example.capstone2.Model.Rating;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.BookingRepository;
import org.example.capstone2.Repository.FeedbackRepository;
import org.example.capstone2.Repository.RatingRepository;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final RatingRepository ratingRepository;
    private final FeedbackRepository feedbackRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public User getSingleUser(Integer id){
        User user = userRepository.getUserById(id);

        if (user == null){
            throw new ApiException("<user-service/getSingleUser> user not found");
        }

        return user;
    }


    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){
        User oldUser = userRepository.getUserById(id);

        if (oldUser == null){
            throw new ApiException("<user-service/updateUser> user not found");
        }

        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhone(user.getPhone());
        oldUser.setNeighborhoodId(user.getNeighborhoodId());
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user = userRepository.getUserById(id);

        if (user == null){
            throw new ApiException("<user-service/userDelete> user not found");
        }

        userRepository.delete(user);
    }

    public List<Booking> getBookingHistory(Integer id){
        return bookingRepository.findBookingsByUserId(id);
    }

    public List<Rating> getRatingHistory(Integer id){
        return ratingRepository.findRatingByUserId(id);
    }

    public List<Feedback> getFeedbackHistory(Integer id){
        return feedbackRepository.findFeedbackByUserId(id);
    }
}
