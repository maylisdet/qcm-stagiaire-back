package com.ai13qcm.dto;

import com.ai13qcm.entities.Theme;

import java.util.List;

public class QuizDTO {
    private String label;
    private boolean isActive;
    private List<QuestionDTO> questions;
    private Theme theme;
    private int id;

    public QuizDTO(String label, boolean isActive, List<QuestionDTO> questions, Theme theme) {
        this.label = label;
        this.isActive = isActive;
        this.questions = questions;
        this.theme = theme;
    }

    public QuizDTO(String label, boolean isActive, List<QuestionDTO> questions, Theme theme, int id) {
        this.label = label;
        this.isActive = isActive;
        this.questions = questions;
        this.theme = theme;
        this.id = id;
    }

    public QuizDTO() {
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

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
