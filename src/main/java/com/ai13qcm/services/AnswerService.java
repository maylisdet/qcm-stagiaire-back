package com.ai13qcm.services;

import com.ai13qcm.entities.Answer;
import com.ai13qcm.repositories.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
  private final AnswerRepository repo;

  public AnswerService(AnswerRepository repo) {
    this.repo = repo;
  }

  public List<Answer> findAll() {
    return repo.findAll();
  }

  public Answer save(Answer answer) {
    return repo.save(answer);
  }

  public Answer findById(Integer id) {
    return repo.findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException(String.format("No answer with the id %d", id)));
  }

  public Answer changePosition(Answer answer, Integer newPosition) {
    final int oldPosition = answer.getPosition();
    var answerToSwapPositionWith =
        answer.getQuestion().getAnswers().stream()
            .filter(ans -> ans.getPosition() == newPosition)
            .findFirst()
            .get();
    answerToSwapPositionWith.setPosition(oldPosition);
    answer.setPosition(newPosition);
    save(answerToSwapPositionWith);
    return save(answer);
  }

  public void deleteById(Integer id) {
    Answer answer = findById(id);
    answer.setActive(false);
    save(answer);
  }
}
