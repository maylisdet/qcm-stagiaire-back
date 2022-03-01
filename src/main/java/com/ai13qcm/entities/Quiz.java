package com.ai13qcm.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "quiz")
public class Quiz {

  public Quiz() {
    this.isActive = true;
    this.createdAt = Timestamp.from(Instant.now());
  }

  public Quiz(int id, String label, boolean isActive, List<Question> questions, Theme theme) {
    this.id = id;
    this.label = label;
    this.isActive = isActive;
    this.questions = questions;
    this.theme = theme;
    this.createdAt = Timestamp.from(Instant.now());
  }

  public Quiz(
      String label, boolean isActive, List<Question> questions, Theme theme, Timestamp createdAt) {
    this.label = label;
    this.isActive = isActive;
    this.questions = questions;
    this.theme = theme;
    this.createdAt = createdAt;
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

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String label;
  private boolean isActive;

  @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL, mappedBy = "quiz")
  private List<Question> questions;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "theme", referencedColumnName = "id")
  private Theme theme;

  private Timestamp createdAt;

  public Quiz copy() {
    return new Quiz(
        this.label,
        this.isActive,
        this.questions.stream().map(question -> question.copy()).collect(Collectors.toList()),
        this.theme,
        this.createdAt);
  }
}
