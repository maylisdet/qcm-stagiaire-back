package com.ai13qcm.services;

import com.ai13qcm.entities.Answer;
import com.ai13qcm.repositories.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AnswerServiceTest {

  @Test
  void findAll() {
    var expectedAnswers = List.of(
            new Answer(1, "Answer 1", true, true, 1),
            new Answer(2, "Answer 2", false, true, 1));
    when(answerRepository.findAll())
        .thenReturn(
            List.of(
                new Answer(1, "Answer 1", true, true, 1),
                new Answer(2, "Answer 2", false, true, 1)));
    var answersFromService = answerService.findAll();
    for (var answer : answersFromService){
      assertTrue(expectedAnswers.contains(answer));
    }
    assertEquals(answersFromService.size(), expectedAnswers.size());
  }

  @Test
  void save() {
    var expectedAnswer = new Answer(1, "Answer 1", true, true, 1);
    when(answerRepository.save(any(Answer.class))).thenReturn(new Answer(1, "Answer 1", true, true, 1));
    var answer = answerRepository.save(expectedAnswer);
    assertEquals(expectedAnswer, answer);

  }

  @Test
  void findById() {
    var expectedAnswer = new Answer(1, "Answer 1", true, true, 1);
    when(answerRepository.findById(1)).thenReturn(java.util.Optional.of(new Answer(1, "Answer 1", true, true, 1)));
    var answer = answerRepository.findById(1);
    assertTrue(answer.isPresent());
    assertEquals(expectedAnswer, answer.get());
  }


  @Mock AnswerRepository answerRepository;
  @InjectMocks AnswerService answerService;
}
