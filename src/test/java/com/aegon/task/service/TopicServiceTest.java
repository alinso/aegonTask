package com.aegon.task.service;

import com.aegon.task.entity.Feedback;
import com.aegon.task.entity.Topic;
import com.aegon.task.entity.dto.TopicDto;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TopicServiceTest {

    @Autowired
    TopicService topicService;

    @Test
    void toDtoList() {
        List<Topic> topicList = new ArrayList<>();
        for(int i=0;i<11;i++){
            topicList.add(new Topic("question"+i, "name"+i, i*5, (long) (i * 3)));
        }
        List<TopicDto> topicDtoList = topicService.toDtoList(topicList);

        for(int k=0;k<11;k++){
            assert topicDtoList.get(k).getTopicName() == topicList.get(k).getTopicName();
            assert topicDtoList.get(k).getQuestion() == topicList.get(k).getQuestion();
            assert topicDtoList.get(k).getNPMScore() == topicList.get(k).getNPMScore();
            assert topicDtoList.get(k).getId() == topicList.get(k).getId();
        }
    }

    @Test
    void calculateNPMScore_case1(){
        List<Feedback> feedbackList = new ArrayList<>();
        for(int i=0;i<11;i++){
         feedbackList.add(new Feedback("feedback"+i, i ));
        }
        int score = (int)topicService.calculateNPMScore(feedbackList);
        assert score == -45 ;
    }

    @Test
    void calculateNPMScore_case2(){
        List<Feedback> feedbackList = new ArrayList<>();
        for(int i=0;i<11;i++){
            feedbackList.add(new Feedback("feedback"+i, 9 ));
        }
        int score = (int)topicService.calculateNPMScore(feedbackList);
        assert score == 100 ;
    }

    @Test
    void calculateNPMScore_case3(){
        List<Feedback> feedbackList = new ArrayList<>();
        for(int i=0;i<11;i++){
            feedbackList.add(new Feedback("feedback"+i, 3 ));
        }
        int score = (int)topicService.calculateNPMScore(feedbackList);
        assert score == -100 ;
    }
}