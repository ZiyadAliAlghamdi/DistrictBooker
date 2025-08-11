package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiException;
import org.example.capstone2.Model.Facility;
import org.example.capstone2.Model.Feedback;
import org.example.capstone2.Model.User;
import org.example.capstone2.Repository.FacilityRepository;
import org.example.capstone2.Repository.FeedbackRepository;
import org.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final FacilityRepository facilityRepository;
    private final MailService mailService;

    public List<Feedback> getAllFeedback(){
        return feedbackRepository.findAll();
    }

    public Feedback getSingleFeedback(Integer id){
        Feedback feedBack = feedbackRepository.getFeedBackById(id);

        if (feedBack == null){
            throw new ApiException("<feedback-service> feedback not found");
        }

        return feedBack;
    }

    public void addFeedback(Feedback feedback){
        User user = userRepository.getUserById(feedback.getUserId());
        
        if (user == null){
            throw new ApiException("<feedback-service> user not found");
        }

        Facility facility = facilityRepository.getFacilityById(feedback.getFacilityId());
        
        if (facility == null){
            throw new ApiException("<feedback-service> facility not found");
        }
        
        feedbackRepository.save(feedback);
    }
    
    public void updateFeedback(Integer id, Feedback feedback){
        Feedback oldFeedback = feedbackRepository.getFeedBackById(id);
        
        if (oldFeedback == null){
            throw new ApiException("<feedback-service> feedback not found");
        }
        
        User user = userRepository.getUserById(feedback.getUserId());

        if (user == null){
            throw new ApiException("<feedback-service> user not found");
        }

        Facility facility = facilityRepository.getFacilityById(feedback.getFacilityId());

        if (facility == null){
            throw new ApiException("<feedback-service> facility not found");
        }
        
        oldFeedback.setUserId(feedback.getUserId());
        oldFeedback.setFacilityId(feedback.getFacilityId());
        oldFeedback.setMessage(feedback.getMessage());
        oldFeedback.setStatus(feedback.getStatus());
        feedbackRepository.save(oldFeedback);
    }
    
    public void deleteFeedback(Integer id){
        Feedback feedback = feedbackRepository.getFeedBackById(id);
        
        if (feedback == null){
            throw new ApiException("<feedback-service> feedback not found");
        }

        feedbackRepository.delete(feedback);
    }

    public List<Feedback> getNewFeedbacks(){
        return feedbackRepository.findNewFeedBack();
    }


    public void setFeedbackToInProgress(Integer id){
        Feedback feedback = feedbackRepository.getFeedBackById(id);

        if (feedback == null){
            throw new ApiException("<feedback-service> feedback not found");
        }

        feedback.setStatus("IN_PROGRESS");
        feedbackRepository.save(feedback);
    }

    //todo: using something to handle in the future
    public void closeFeedback(Integer feedbackId, String message){
        Feedback feedback = feedbackRepository.getFeedBackById(feedbackId);

        if (feedback == null){
            throw new ApiException("<feedback-service> feedback not found");
        }

        User user = userRepository.getUserById(feedback.getUserId());

        if (user == null){
            throw new ApiException("<feedback-service> user not found");
        }

        Facility facility = facilityRepository.getFacilityById(feedback.getFacilityId());

        if (facility == null){
            throw new ApiException("<feedback-service> facility not found");
        }

        feedback.setStatus("CLOSED");
        feedbackRepository.save(feedback);

        mailService.sendFeedbackMail(user,facility,message);
    }



}
