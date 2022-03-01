package com.ai13qcm.entities;

import com.ai13qcm.logic.Ranking;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Table(name = "record")
@Entity
public class Record {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Record record = (Record) o;
    return id == record.id
        && score == record.score
        && duration == record.duration
        && Objects.equals(quiz, record.quiz)
        && Objects.equals(answers, record.answers)
        && Objects.equals(user, record.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, score, duration, quiz, answers, user);
  }

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private int score;
  private int duration;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "quiz_id", referencedColumnName = "id")
  private Quiz quiz;

  @ManyToMany(cascade = CascadeType.MERGE)
  private List<Answer> answers;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  private transient Ranking ranking;

  public Record(int id, int score, int duration, List<Answer> answers) {
    this.id = id;
    this.score = score;
    this.duration = duration;
    this.answers = answers;
  }

  public Record(int id, int score, int duration, Quiz quiz, List<Answer> answers, User user, Ranking ranking) {
    this.id = id;
    this.score = score;
    this.duration = duration;
    this.quiz = quiz;
    this.answers = answers;
    this.user = user;
    this.ranking = ranking;
  }


  public Record() {}


  public Ranking getRanking() {
    return ranking;
  }

  public void setRanking(Ranking ranking) {
    this.ranking = ranking;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getScore(int score) {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  public int getScore() {
    return score;
  }

  public Quiz getQuiz() {
    return quiz;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void calculateAndUpdateScore() {
    this.score =
        this.answers.stream()
            .filter(answer -> answer.isCorrect())
            .collect(Collectors.toList())
            .size();
  }
}
