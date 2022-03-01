package com.ai13qcm.services;

import com.ai13qcm.entities.Question;
import com.ai13qcm.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

  public QuestionService(QuestionRepository repo) {
    this.repo = repo;
  }

  public List<Question> findAll() {
    return repo.findAll();
  }

  public Question save(Question question) {
    question.getAnswers().forEach(answer -> answer.setQuestion(question));
    return repo.save(question);
  }

  public Question findById(Integer id) {
    return repo.findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException(String.format("No answer with the id %d", id)));
  }

  public void deleteById(Integer id) {
    repo.deleteById(id);
  }

  public Question changePosition(Question question, Integer newPosition) {
    final int oldPosition = question.getPosition();
    var questionToSwapWith =
            question.getQuiz().getQuestions().stream()
                    .filter(q -> q.getPosition() == newPosition)
                    .findFirst()
                    .get();
    questionToSwapWith.setPosition(oldPosition);
    question.setPosition(newPosition);
    save(questionToSwapWith);
    return save(question);
  }

  private final QuestionRepository repo;
}
