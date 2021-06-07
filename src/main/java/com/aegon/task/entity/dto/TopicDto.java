package com.aegon.task.entity.dto;

import javax.validation.constraints.NotBlank;


public class TopicDto {

    private long id;

    @NotBlank(message = "Name cant be blank")
    private String topicName;

    @NotBlank(message = "Name cant be blank")
    private String question;

    private double NPMScore;

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

    public double getNPMScore() {
        return NPMScore;
    }

    public void setNPMScore(double NPMScore) {
        this.NPMScore = NPMScore;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
