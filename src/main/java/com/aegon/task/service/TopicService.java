package com.aegon.task.service;

import com.aegon.task.entity.Feedback;
import com.aegon.task.entity.Topic;
import com.aegon.task.entity.dto.TopicDto;
import com.aegon.task.exception.VoteOutOfBoundException;
import com.aegon.task.repository.FeedbackRepository;
import com.aegon.task.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TopicService extends BaseService {

    final TopicRepository topicRepository;
    final FeedbackRepository feedbackRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository, FeedbackRepository feedbackRepository) {
        this.topicRepository = topicRepository;
        this.feedbackRepository = feedbackRepository;
    }

    public Topic save(TopicDto topicDto) {
        Topic topic = modelMapper.map(topicDto, Topic.class);
        return topicRepository.save(topic);
    }

    public List<Feedback> getFeedbacksByTopic(Topic tpc){
        return feedbackRepository.feedbackListByTopic(tpc);
    }

    public List<TopicDto> findAll() {
        List<Topic> topicList = topicRepository.findAll();
        return toDtoList(topicList);
    }


    public List<TopicDto> toDtoList(List<Topic> topicList){

        List<TopicDto> topicDtoList = new ArrayList<>();
        topicList.forEach(topic -> {
            TopicDto topicDto = modelMapper.map(topic, TopicDto.class);
            topicDtoList.add(topicDto);
        });

        return topicDtoList;
    }

    public double calculateNPMScore( List<Feedback> feedbackList) {

        AtomicInteger detractorCount = new AtomicInteger();
        AtomicInteger promoterCount = new AtomicInteger();

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

    public void updateNPMScore(Topic topic,List<Feedback> feedbackList) {
        topic.setNPMScore(calculateNPMScore(feedbackList));
        topicRepository.save(topic);
    }


    public Topic findEntityById(long selectedTopicId) {
        Topic topic= topicRepository.findById(selectedTopicId).get();
        return topic;

    }
}









