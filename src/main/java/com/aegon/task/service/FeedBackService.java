package com.aegon.task.service;

import com.aegon.task.entity.Feedback;
import com.aegon.task.entity.dto.FeedbackDto;
import com.aegon.task.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedBackService extends BaseService {

    private final FeedbackRepository repository;

    @Autowired
    public FeedBackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public Feedback save(FeedbackDto feedbackDto){
        Feedback feedback = modelMapper.map(feedbackDto,Feedback.class);
         return repository.save(feedback);
    }



}
