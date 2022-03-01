package com.ai13qcm.dto;

import com.ai13qcm.entities.Answer;

import java.sql.Timestamp;
import java.util.List;

public class QuestionDTO {
    private String label;
    private boolean isActive;
    private Timestamp createdAt;
    private List<Answer> answers;
    private Integer position, quiz_id, id;

    public QuestionDTO(String label, boolean isActive, Timestamp createdAt, List<Answer> answers, int position, int quiz_id) {
        this.label = label;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.answers = answers;
        this.position = position;
        this.quiz_id = quiz_id;
        this.id = null;
    }

    public QuestionDTO(String label, boolean isActive, Timestamp createdAt, List<Answer> answers, int position, int quiz_id, int id) {
        this.label = label;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.answers = answers;
        this.position = position;
        this.quiz_id = quiz_id;
        this.id = id;
    }

    public QuestionDTO() {
        this.id = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Integer quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
