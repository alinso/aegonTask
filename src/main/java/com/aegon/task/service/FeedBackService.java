package com.aegon.task.service;

import com.aegon.task.entity.Feedback;
import com.aegon.task.entity.Topic;
import com.aegon.task.entity.dto.FeedbackDto;
import com.aegon.task.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedBackService extends BaseService {

    private final FeedbackRepository feedbackRepository;
    private final TopicService topicService;

    @Autowired
    public FeedBackService(FeedbackRepository feedbackRepository, TopicService topicService) {
        this.feedbackRepository = feedbackRepository;
        this.topicService = topicService;
    }

    public Feedback save(FeedbackDto feedbackDto){
        Feedback feedback = modelMapper.map(feedbackDto,Feedback.class);

        Topic topic= topicService.findEntityById(feedbackDto.getSelectedTopicId());
        feedback.setTopic(topic);
        feedbackRepository.save(feedback);

        List<Feedback> feedbackList =  topicService.getFeedbacksByTopic(topic);
        topicService.updateNPMScore(topic,feedbackList);
        return feedback;
    }


    public List<FeedbackDto> toDtoList(List<Feedback> feedbackList){
        List<FeedbackDto> feedbackDtoList = new ArrayList<>();

        feedbackList.forEach(feedback -> {
            FeedbackDto feedbackDto  = modelMapper.map(feedback,FeedbackDto.class);
            feedbackDtoList.add(feedbackDto);
        });

        return feedbackDtoList;
    }

    public Object findByTopicId(Long topicId) {
        Topic topic =  topicService.findEntityById(topicId);
        List<Feedback> feedbackList =  feedbackRepository.feedbackListByTopic(topic);

        return toDtoList(feedbackList);
    }
}
