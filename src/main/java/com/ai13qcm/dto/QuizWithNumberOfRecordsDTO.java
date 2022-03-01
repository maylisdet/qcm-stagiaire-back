package com.ai13qcm.dto;

import com.ai13qcm.entities.Question;
import com.ai13qcm.entities.Theme;

import java.sql.Timestamp;
import java.util.List;

public class QuizWithNumberOfRecordsDTO {
    public QuizWithNumberOfRecordsDTO(int id, String label, boolean isActive, List<Question> questions, Theme theme, Timestamp createdAt, int numberOfRecords) {
        this.id = id;
        this.label = label;
        this.isActive = isActive;
        this.questions = questions;
        this.theme = theme;
        this.createdAt = createdAt;
        this.numberOfRecords = numberOfRecords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    private int id;

    private String label;
    private boolean isActive;

    private List<Question> questions;

    private Theme theme;

    private Timestamp createdAt;
    private int numberOfRecords;
}
