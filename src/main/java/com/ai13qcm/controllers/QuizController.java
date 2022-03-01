package com.ai13qcm.controllers;

import com.ai13qcm.dto.QuizDTO;
import com.ai13qcm.dto.QuizWithNumberOfRecordsDTO;
import com.ai13qcm.entities.Question;
import com.ai13qcm.entities.Quiz;
import com.ai13qcm.entities.Record;
import com.ai13qcm.services.AnswerService;
import com.ai13qcm.services.QuizService;
import com.ai13qcm.services.RecordService;
import com.ai13qcm.services.ThemeService;
import org.springframework.http.HttpStatus;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class QuizController {
  public QuizController(
      RecordService recordService,
      QuizService quizService,
      ThemeService themeService,
      AnswerService answerService) {
    this.recordService = recordService;
    this.quizService = quizService;
    this.themeService = themeService;
    this.answerService = answerService;
  }

  @GetMapping("/api/quizzes")
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  List<QuizWithNumberOfRecordsDTO> getAll(@RequestParam(name = "active", required = false) Boolean active) {
    if (active != null) {
      return this.convertListOfQuizzesToListOfQuizzesWithNumberOfRecords(quizService.findByIsActive(active));
    } else return this.convertListOfQuizzesToListOfQuizzesWithNumberOfRecords(quizService.findAll());
  }

  @PostMapping("/api/quizzes")
  @PreAuthorize("hasAuthority('ADMIN')")
  Quiz newQuiz(@RequestBody Quiz quiz) {
    quiz.getQuestions().forEach(question -> question.setQuiz(quiz));
    return quizService.save(quiz);
  }

  @GetMapping("/api/quizzes/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN','TRAINEE')")
  QuizWithNumberOfRecordsDTO findById(@PathVariable Integer id) {
    return convertQuizToQuizWithNumberOfRecords(quizService.findById(id));
  }

  @GetMapping("/api/quizzes/{id}/records")
  @PreAuthorize("hasAuthority('ADMIN')")
  List<Record> getAllRecordsForAQuestion(@PathVariable Integer id) {
    return recordService.findAllByQuizId(id);
  }

  @PatchMapping("/api/quizzes/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  Quiz partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Integer id) {
    var entity = quizService.findById(id);
    if (updates.containsKey("label")) entity.setLabel((String) updates.get("label"));
    if (updates.containsKey("theme_id"))
      entity.setTheme(themeService.findThemeById((Integer) updates.get("theme_id")));
    return quizService.save(entity);
  }

  @PutMapping("/api/quizzes/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  Quiz update(@RequestBody QuizDTO quizDTO, @PathVariable Integer id) {
    var quiz = convertFromDto(quizDTO);
    quiz.setId(id);
    if (hasRecords(quiz)) {
      var quizCopy = quiz.copy();
      quiz.setActive(false);
      quiz.getQuestions()
          .forEach(
              question -> {
                question.setActive(false);
                question.getAnswers().forEach(answer -> answer.setActive(false));
              });
      quizService.save(quiz);
      return quizService.save(quizCopy);
    }

    return quizService.save(quiz);
  }

  @DeleteMapping("/api/quizzes/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  void deleteQuiz(@PathVariable Integer id) {
    try {
      Quiz quiz = quizService.findById(id);
      for (var question : quiz.getQuestions()) {
        for (var answer : question.getAnswers()) {
          answerService.deleteById(answer.getId());
        }
        question.setActive(false);
      }
      quiz.setActive(false);
      quizService.save(quiz);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @GetMapping("/api/quizzes/{id}/results")
  @PreAuthorize("hasAuthority('ADMIN')")
  List<Integer> getResults(@PathVariable Integer id) {
    List<Integer> marks = null;
    // TODO retrieve best, worst, average mark for the quiz
    return marks;
  }

  @GetMapping("/api/quizzes/{id}/ranks")
  @PreAuthorize("hasAuthority('ADMIN')")
  List<Integer> getRanks(@PathVariable Integer id) {
    List<Integer> marks = null;
    // TODO return a leaderboard of users that have done the quiz
    return marks;
  }

  private Quiz convertFromDto(QuizDTO dto) {
    Quiz entity = new Quiz();
    entity.setActive(dto.isActive());
    entity.setLabel(dto.getLabel());
    entity.setTheme(dto.getTheme());
    entity.setQuestions(
        dto.getQuestions().stream()
            .map(
                questionDTO ->
                    new Question(
                        questionDTO.getId(),
                        questionDTO.getLabel(),
                        questionDTO.isActive(),
                        questionDTO.getCreatedAt(),
                        questionDTO.getAnswers(),
                        questionDTO.getPosition(),
                        entity))
            .collect(Collectors.toList()));

    return entity;
  }

  private QuizWithNumberOfRecordsDTO convertQuizToQuizWithNumberOfRecords(Quiz originalQuiz) {
    return new QuizWithNumberOfRecordsDTO(
        originalQuiz.getId(),
        originalQuiz.getLabel(),
        originalQuiz.isActive(),
        originalQuiz.getQuestions(),
        originalQuiz.getTheme(),
        originalQuiz.getCreatedAt(),
        recordService.countRecordsOfAQuiz(originalQuiz.getId()));
  }

  private List<QuizWithNumberOfRecordsDTO> convertListOfQuizzesToListOfQuizzesWithNumberOfRecords(List<Quiz> quizzes){
    return quizzes.stream().map(this::convertQuizToQuizWithNumberOfRecords).collect(Collectors.toList());
  }

  private boolean hasRecords(Quiz quiz) {
    return recordService.countRecordsOfAQuiz(quiz.getId()) != 0;
  }

  private final RecordService recordService;
  private final QuizService quizService;
  private final ThemeService themeService;
  private final AnswerService answerService;
}
