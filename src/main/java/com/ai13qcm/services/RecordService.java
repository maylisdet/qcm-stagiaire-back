package com.ai13qcm.services;

import com.ai13qcm.entities.Record;
import com.ai13qcm.logic.Ranking;
import com.ai13qcm.repositories.RecordRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService {
  private final RecordRepository repo;

  public RecordService(RecordRepository repo) {
    this.repo = repo;
  }

  public List<Record> findAll() {
    var records = repo.findAll();
    return this.linkAllRecordsWithTheirRankings(records);
  }

  public List<Record> findAllByUserId(Integer userId) {
    return this.linkAllRecordsWithTheirRankings(repo.findByUserId(userId));
  }

  public List<Record> findAllByQuizId(Integer quizId) {
    return this.linkAllRecordsWithTheirRankings(repo.findByQuizId(quizId));
  }

  public Record save(Record ranking) {

    var recordWithoutRanking = repo.save(ranking);
    return linkARecordWithItsRanking(recordWithoutRanking);
  }

  public Record findById(Integer id) {
    return repo.findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException(String.format("No record with the id %d", id)));
  }

  private List<Record> linkAllRecordsWithTheirRankings(List<Record> recordsWithoutRanking) {
    return recordsWithoutRanking.stream()
        .map(this::linkARecordWithItsRanking)
        .collect(Collectors.toList());
  }

  private Record linkARecordWithItsRanking(Record rec) {
    rec.setRanking(getRankingForRecord(rec.getId(), rec.getQuiz().getId()));
    return rec;
  }

  private Ranking getRankingForRecord(Integer recId, Integer quizzId) {
    var rankingRawValues = repo.getRankingForRecord(recId, quizzId);

    return new Ranking(
        (BigInteger) rankingRawValues.get("nb_respondent"),
        (BigInteger) rankingRawValues.get("score_rank"),
        (Integer) rankingRawValues.get("best_score"),
        (Integer) rankingRawValues.get("duration_of_best_score"));
  }

  public void deleteById(Integer id) {
    repo.deleteById(id);
  }

  public void deleteByUserId(Integer id) {
    repo.deleteAllByUserId(id);
  }

  public Integer countRecordsOfAQuiz(Integer quizId){
    return repo.countRecordsOfAQuiz(quizId);
  }
}
