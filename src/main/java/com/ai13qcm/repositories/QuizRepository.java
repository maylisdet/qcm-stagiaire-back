package com.ai13qcm.repositories;

import com.ai13qcm.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    List<Quiz> findByIsActive(Boolean isActive);
    @Query(value = "select * from quiz  where id not in (select distinct quiz_id from record where user_id = :userId)", nativeQuery = true)
    List<Quiz> selectAvailableQuizzesForTrainee(Integer userId);
}
