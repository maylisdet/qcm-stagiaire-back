package com.ai13qcm.dto;

public class AnswerDTO {
    private int id, position, question_id;
    private String label;
    private boolean isCorrect, isActive;

    public AnswerDTO(int id, int position, int question_id, String label, boolean isCorrect, boolean isActive) {
        this.id = id;
        this.position = position;
        this.question_id = question_id;
        this.label = label;
        this.isCorrect = isCorrect;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
