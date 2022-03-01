package com.ai13qcm.services;

import com.ai13qcm.entities.Theme;
import com.ai13qcm.repositories.ThemeRepository;
import org.springframework.stereotype.Service;

@Service
public class ThemeService {
    private final ThemeRepository repo;

    public ThemeService(ThemeRepository repo) {
        this.repo = repo;
    }
    public Theme findThemeById(Integer id){
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("No theme with id %d found", id)));
    }
}
