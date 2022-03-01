package com.ai13qcm.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "question")
public class Question {
  public Question(
      Integer id,
      String label,
      boolean isActive,
      Timestamp createdAt,
      List<Answer> answers,
      int position,
      Quiz quiz) {
    this.id = id;
    this.label = label;
    this.isActive = isActive;
    this.createdAt = createdAt;
    this.answers = answers;
    this.position = position;
    this.quiz = quiz;
  }

  public Question(
      String label,
      boolean isActive,
      int position,
      Timestamp createdAt,
      List<Answer> answers,
      Quiz quiz) {
    this.label = label;
    this.isActive = isActive;
    this.position = position;
    this.createdAt = createdAt;
    this.answers = answers;
    this.quiz = quiz;
  }

  public Question() {
    this.createdAt = Timestamp.from(Instant.now());
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> possibleAnswers) {
    this.answers = possibleAnswers;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp creationDate) {
    this.createdAt = creationDate;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Question question = (Question) o;
    return id == question.id
        && isActive == question.isActive
        && Objects.equals(createdAt, question.createdAt)
        && Objects.equals(label, question.label)
        && Objects.equals(answers, question.answers);
  }

  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  @JsonIdentityReference(alwaysAsId = true)
  @JsonProperty("quiz_id")
  public Quiz getQuiz() {
    return quiz;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, label, isActive, createdAt, answers);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String label;
  private boolean isActive;
  private int position;
  private Timestamp createdAt;

  @OneToMany(
      targetEntity = Answer.class,
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      mappedBy = "question")
  private List<Answer> answers;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "quiz_id", referencedColumnName = "id")
  private Quiz quiz;

  public Question copy() {
    return new Question(
        this.label,
        this.isActive,
        this.position,
        this.createdAt,
        this.answers.stream().map(answer -> answer.copy()).collect(Collectors.toList()),
        this.quiz);
  }
}
