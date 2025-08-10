package org.example.capstone2.Repository;

import org.example.capstone2.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
    Feedback getFeedBackById(Integer id);

    @Query("select f from Feedback f where f.userId = ?1")
    List<Feedback> findFeedbackByUserId(Integer userId);

    @Query("select f from Feedback f where f.status = 'NEW'")
    List<Feedback> findNewFeedBack();
}
