package com.ai13qcm.repositories;

import com.ai13qcm.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
  List<Record> findByQuizId(int quizzId);

  List<Record> findByUserId(int userId);

  @Query(value = "call get_record_ranking(:recordId, :quizId)", nativeQuery = true)
  Map<String, Object> getRankingForRecord(
      @Param("recordId") Integer recId, @Param("quizId") Integer quizzId);
  @Query(value = "select count(*) from record where quiz_id = :quizId", nativeQuery = true)
  Integer countRecordsOfAQuiz(Integer quizId);
  @Transactional
  void deleteAllByUserId(int userId);
}
