package com.ai13qcm.controllers;

import com.ai13qcm.entities.Role;
import com.ai13qcm.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class RoleController {
  private final RoleService service;

  public RoleController(RoleService service) {
    this.service = service;
  }

  @GetMapping("/api/roles")
  @PreAuthorize("hasRole('ADMIN')")
  List<Role> getAll() {
    return service.findAll();
  }

  @PostMapping("/api/roles")
  @PreAuthorize("hasRole('ADMIN')")
  Role newRole(@RequestBody Role role) {
    return service.save(role);
  }

  @GetMapping("/api/roles/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  Role findById(@PathVariable Integer id) {
    try {
      return service.findById(id);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/api/roles/{id}")
  void deleteRole(@PathVariable Integer id) {
    service.deleteById(id);
  }
}
