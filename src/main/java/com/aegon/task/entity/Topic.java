package com.aegon.task.entity;

import org.springframework.context.annotation.Lazy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Topic extends BaseEntity {

    @Column
    private String question;

    @Column
    private String topicName;

    @Column
    private double NPMScore;

    @Column
    private String IPAddress;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public double getNPMScore() {
        return NPMScore;
    }

    public void setNPMScore(double NPMScore) {
        this.NPMScore = NPMScore;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

}
