package com.ai13qcm.controllers;

import com.ai13qcm.entities.Theme;
import com.ai13qcm.repositories.ThemeRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThemeController {

  private final ThemeRepository repo;

  public ThemeController(ThemeRepository repo) {
    this.repo = repo;
  }

  @GetMapping("/api/themes")
  @PreAuthorize("hasAuthority('ADMIN')")
  List<Theme> getAll() {
    return repo.findAll();
  }

  @PostMapping("/api/themes")
  @PreAuthorize("hasAuthority('ADMIN')")
  Theme newTheme(@RequestBody Theme theme) {
    return repo.save(theme);
  }

  @GetMapping("/api/themes/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  Theme findById(@PathVariable Integer id) {
    return repo.findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException(String.format("No theme with the id %d", id)));
  }
}
