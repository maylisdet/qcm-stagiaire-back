package com.ai13qcm.controllers;

import com.ai13qcm.dto.QuestionDTO;
import com.ai13qcm.entities.Question;
import com.ai13qcm.services.AnswerService;
import com.ai13qcm.services.QuestionService;
import com.ai13qcm.services.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
public class QuestionController {
  private final QuestionService service;
  private final QuizService quizService;
  private final AnswerService answerService;

  public QuestionController(
      QuestionService repo, QuizService quizService, AnswerService answerService) {
    this.service = repo;
    this.quizService = quizService;
    this.answerService = answerService;
  }

  @GetMapping("/api/questions")
  @PreAuthorize("hasAuthority('ADMIN')")
  List<Question> getAll() {
    return service.findAll();
  }

  @PostMapping("/api/questions")
  @PreAuthorize("hasAuthority('ADMIN')")
  Question newQuestion(@RequestBody QuestionDTO question) {

    return service.save(convertFromDtoToEntity(question));
  }

  @GetMapping("/api/questions/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  Question findById(@PathVariable Integer id) {
    try {
      return service.findById(id);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PutMapping("/api/questions/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  Question update(@RequestBody QuestionDTO questionDto, @PathVariable Integer id) {
    var question = convertFromDtoToEntity(questionDto);
    question.setId(id);
    return service.save(question);
  }

  @PatchMapping("/api/questions/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  Question partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Integer id) {
    var currentQuestionEntity = service.findById(id);

    if (updates.containsKey("label")) {
      currentQuestionEntity.setLabel((String) updates.get("label"));
    }
    if (updates.containsKey("active")) {
      currentQuestionEntity.setActive((Boolean) updates.get("active"));
    }
    if (updates.containsKey("creationDate")) {
      String newDateString = (String) updates.get("creationDate");
      Timestamp newCreationDate =
          Timestamp.valueOf(LocalDateTime.parse(newDateString, DateTimeFormatter.ISO_DATE_TIME));
      currentQuestionEntity.setCreatedAt(newCreationDate);
    }
    if (updates.containsKey("position")) {
      final int newPosition = (Integer) updates.get("position");
      service.changePosition(currentQuestionEntity, newPosition);
    }
    return service.save(currentQuestionEntity);
  }

  @DeleteMapping("/api/questions/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  void deleteQuestion(@PathVariable Integer id) {
    try {
      Question question = service.findById(id);
      for (var answer : question.getAnswers()) {
        answerService.deleteById(answer.getId());
      }
      question.setActive(false);
      service.save(question);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  private Question convertFromDtoToEntity(QuestionDTO questionDTO) {
    var question = new Question();
    var quizRelatedToTheQuestion = quizService.findById(questionDTO.getQuiz_id());
    question.setAnswers(questionDTO.getAnswers());
    question.setQuiz(quizRelatedToTheQuestion);
    question.setActive(questionDTO.isActive());
    question.setPosition(questionDTO.getPosition());
    question.setCreatedAt(questionDTO.getCreatedAt());
    question.setLabel(questionDTO.getLabel());
    return question;
  }
}
