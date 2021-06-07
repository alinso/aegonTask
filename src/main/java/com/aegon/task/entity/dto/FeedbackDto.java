package com.aegon.task.entity.dto;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FeedbackDto {

    private long id;

    @NotEmpty(message = "answer cannot be empty")
    private String answer;

    @NotNull(message = "vote cant be null")
    private int vote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
