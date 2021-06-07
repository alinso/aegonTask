package com.aegon.task.repository;

import com.aegon.task.entity.Feedback;
import com.aegon.task.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

    @Query("select f from Feedback f where f.topic=:tpc")
    public List<Feedback> feedbackListByTopic(@Param("tpc")Topic tpc);

}
