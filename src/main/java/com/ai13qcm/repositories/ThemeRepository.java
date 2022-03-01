package com.ai13qcm.repositories;

import com.ai13qcm.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    Optional<Theme>  findByLabel(String label);
}
