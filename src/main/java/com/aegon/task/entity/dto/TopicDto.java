package com.aegon.task.entity.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;


public class TopicDto {

    private long id;

    @NotBlank(message = "Name cant be blank")
    private String topicName;

    @NotBlank(message = "Name cant be blank")
    private String question;

    List<FeedbackDto> feedbackDtoList;

    private Integer NPMScore;

    public List<FeedbackDto> getFeedbackDtoList() {
        return feedbackDtoList;
    }

    public void setFeedbackDtoList(List<FeedbackDto> feedbackDtoList) {
        this.feedbackDtoList = feedbackDtoList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getNPMScore() {
        return NPMScore;
    }

    public void setNPMScore(Integer NPMScore) {
        this.NPMScore = NPMScore;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
