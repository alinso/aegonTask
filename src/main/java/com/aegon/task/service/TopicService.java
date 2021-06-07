package com.aegon.task.service;

import com.aegon.task.entity.Feedback;
import com.aegon.task.entity.Topic;
import com.aegon.task.entity.dto.TopicDto;
import com.aegon.task.exception.VoteOutOfBoundException;
import com.aegon.task.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TopicService extends BaseService {

    final TopicRepository repository;

    @Autowired
    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public Topic save(TopicDto topicDto) {
        Topic topic = modelMapper.map(topicDto, Topic.class);
        return repository.save(topic);
    }

    public List<TopicDto> findAll() {
        List<Topic> topicList = repository.findAll();
        return toDtoList(topicList);
    }


    public List<Feedback> getFeedBacks(Topic topic) {
        return topic.getFeedbackList();
    }

    public double calculateNPMScore(Topic topic) {
        AtomicInteger detractorCount = new AtomicInteger();
        AtomicInteger promoterCount = new AtomicInteger();

        List<Feedback> feedbackList = getFeedBacks(topic);
        feedbackList.forEach(feedback -> {
            if (feedback.getVote() < 7 && feedback.getVote() > -1) {
                detractorCount.getAndIncrement();
            } else if (feedback.getVote() > 8 && feedback.getVote() < 11) {
                promoterCount.getAndIncrement();
            } else if (feedback.getVote() < 0 || feedback.getVote() > 10) {
                throw new VoteOutOfBoundException();
            }
        });

        double promoterPercent = (Double.valueOf(promoterCount.get()) / Double.valueOf(feedbackList.size())) * 100;
        double detractorPercent = (Double.valueOf(detractorCount.get()) / Double.valueOf(feedbackList.size())) * 100;
        double NPMScore = promoterPercent - detractorPercent;

        return NPMScore;
    }

    public void updateNPMScore(Topic topic) {
        topic.setNPMScore(calculateNPMScore(topic));
    }


}









