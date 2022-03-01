package com.ai13qcm.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="answer")
public class Answer {
    public Answer(int id, String label, boolean isCorrect, boolean isActive, int position) {
        this.id = id;
        this.label = label;
        this.isCorrect = isCorrect;
        this.isActive = isActive;
        this.position = position;
    }

    public Answer(int id, String label, boolean isCorrect, boolean isActive, int position, Question question) {
        this.id = id;
        this.label = label;
        this.isCorrect = isCorrect;
        this.isActive = isActive;
        this.position = position;
        this.question = question;
    }

    public Answer(String label, boolean isCorrect, boolean isActive, int position, Question question) {
        this.label = label;
        this.isCorrect = isCorrect;
        this.isActive = isActive;
        this.position = position;
        this.question = question;
    }

    public Answer() {
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,
            property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("question_id")
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id && isCorrect == answer.isCorrect && isActive == answer.isActive && position == answer.position && Objects.equals(label, answer.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, isCorrect, isActive, position);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String label;
    private boolean isCorrect, isActive;
    private int position;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="question_id", referencedColumnName = "id", nullable = false)
    private Question question;

    public Answer copy() {
        return new Answer(this.label, this.isCorrect, this.isActive, this.position, this.question);
    }
}
