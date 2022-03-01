package com.ai13qcm.controllers;

import com.ai13qcm.dto.AnswerDTO;
import com.ai13qcm.entities.Answer;
import com.ai13qcm.services.AnswerService;
import com.ai13qcm.services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class AnswerController {

  private final AnswerService service;
  private final QuestionService questionService;

  public AnswerController(AnswerService repo, QuestionService questionService) {
    this.service = repo;
    this.questionService = questionService;
  }

  @GetMapping("/api/answers")
  @PreAuthorize("hasAuthority('ADMIN')")
  List<Answer> getAll() {
    return service.findAll();
  }

  @PostMapping("/api/answers")
  @PreAuthorize("hasAuthority('ADMIN')")
  Answer newAnswer(@RequestBody AnswerDTO answer) {
    return service.save(convertAnswerDtoToEntity(answer));
  }

  @GetMapping("/api/answers/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  Answer findById(@PathVariable Integer id) {
    try {
      return service.findById(id);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PutMapping("/api/answers/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  Answer update(@RequestBody AnswerDTO answerDTO, @PathVariable Integer id) {
    var entity = convertAnswerDtoToEntity(answerDTO);
    entity.setId(id);
    return service.save(entity);
  }

  @PatchMapping("/api/answers/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  Answer partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Integer id) {
    var currentEntity = service.findById(id);
    if (updates.containsKey("label")) {
      currentEntity.setLabel((String) updates.get("label"));
    }
    if (updates.containsKey("isCorrect")) {
      currentEntity.setCorrect((Boolean) updates.get("isCorrect"));
    }
    if (updates.containsKey("active")) {
      currentEntity.setActive((Boolean) updates.get("active"));
    }
    if (updates.containsKey("position")) {
      service.changePosition(currentEntity, (Integer) updates.get("position"));
    }

    return service.save(currentEntity);
  }

  @DeleteMapping("/api/answers/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  void deleteAnswer(@PathVariable Integer id) {
    try {
      service.deleteById(id);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  private Answer convertAnswerDtoToEntity(AnswerDTO answerDTO) {
    var question = questionService.findById(answerDTO.getQuestion_id());
    return new Answer(
        0,
        answerDTO.getLabel(),
        answerDTO.isCorrect(),
        answerDTO.isActive(),
        answerDTO.getPosition(),
        question);
  }
}
