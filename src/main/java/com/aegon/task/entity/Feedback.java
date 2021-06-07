package com.aegon.task.entity;

import com.aegon.task.service.TopicService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class Feedback extends BaseEntity {

    @Column
    @Min(1)
    @Max(10)
    private int vote;

    @Column
    @Size
    private String answer;

    public Feedback(){

    }

    //used for testing
    public Feedback(String answer, int vote){
        this.answer = answer;
        this.vote = vote;
    }


    //I have only defined the relationship here, whenever I need to reach the List<Feedback> of a topic, I am getting the list by Feedback repository.
    //It has 2 advantages, 1- it promotes the lazy loading(I only query list of feedbacks of a a topic when I need), 2-I am not creating another topic_feedback_list table
    // (if I have defined the relationship at the topic-entity side it has to be one-to-many and it has to have another table called topic_feedback_list)
    @ManyToOne
    private Topic topic;

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
