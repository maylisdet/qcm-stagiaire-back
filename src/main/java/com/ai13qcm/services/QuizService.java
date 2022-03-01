package com.ai13qcm.services;

import com.ai13qcm.entities.Quiz;
import com.ai13qcm.repositories.QuizRepository;
import com.ai13qcm.repositories.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

  private final QuizRepository repo;
  private final RecordRepository recordRepository;
  public QuizService(QuizRepository repo, RecordRepository recordRepository) {
    this.repo = repo;
    this.recordRepository = recordRepository;
  }

  public List<Quiz> findAll() {
    return repo.findAll();
  }

  public Quiz save(Quiz quiz) {
    quiz.getQuestions()
        .forEach(question -> question.getAnswers().forEach(answer -> answer.setQuestion(question)));
    return repo.save(quiz);
  }

  public Quiz findById(Integer id) {
    return repo.findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException(String.format("No answer with the id %d", id)));
  }
  public List<Quiz> selectAvailableQuizzesForUser(Integer userId){
    return repo.selectAvailableQuizzesForTrainee(userId);
  }
  public void deleteById(Integer id) {
    repo.deleteById(id);
  }
  public List<Quiz> findByIsActive(Boolean active) {
    return repo.findByIsActive(active);
  }
}
