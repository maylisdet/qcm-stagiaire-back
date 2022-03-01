package com.ai13qcm.controllers;

import com.ai13qcm.entities.Record;
import com.ai13qcm.services.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class RecordController {
  private final RecordService service;

  public RecordController(RecordService service) {
    this.service = service;
  }

  @GetMapping("/api/records")
  @PreAuthorize("hasAuthority('ADMIN')")
  List<Record> getAll() {
    return service.findAll();
  }

  @PostMapping("/api/records")
  @PreAuthorize("hasAnyAuthority('ADMIN','TRAINEE')")
  @PostAuthorize(
      "hasAuthority('ADMIN') or returnObject.getUser().getId() == authentication.getPrincipal().getId()")
  Record newRecord(@RequestBody Record record) {
    record.calculateAndUpdateScore();
    return service.save(record);
  }

  @GetMapping("/api/records/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN','TRAINEE')")
  @PostAuthorize(
      "hasAuthority('ADMIN') or returnObject.getUser().getId() == authentication.getPrincipal().getId()")
  Record findById(@PathVariable Integer id) {
    try {
      return service.findById(id);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PatchMapping("/api/records/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  Record partialUpdates(@RequestBody Map<String, Object> updates, @PathVariable Integer id) {
    var entity = service.findById(id);
    if (updates.containsKey("duration")) {
      entity.setDuration((Integer) updates.get("duration"));
    }
    return entity;
  }

  @PutMapping("/api/records/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  Record update(@RequestBody Record newEntity, @PathVariable Integer id) {
    newEntity.setId(id);
    return service.save(newEntity);
  }
}
