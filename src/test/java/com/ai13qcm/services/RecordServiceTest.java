package com.ai13qcm.services;

import com.ai13qcm.entities.*;
import com.ai13qcm.entities.Record;
import com.ai13qcm.repositories.RecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RecordServiceTest {
  //
  //  @Test
  //  void findAll() {
  //    Timestamp creationDate = new Timestamp(System.currentTimeMillis());
  //    User user =
  //        new User(
  //            1,
  //            "Aroun",
  //            "Radjavelou",
  //            "aroun.radjavelou@etu.utc.fr",
  //            "password",
  //            new Role("ADMIN"),
  //            "Thales",
  //            "0736416969",
  //            creationDate,
  //            true);
  //    var questionsExpected =
  //        List.of(
  //            new Question(
  //                1,
  //                "Quel est le resultat du test ? ",
  //                true,
  //                Timestamp.from(Instant.now()),
  //                List.of(
  //                    new Answer(1, "Answer 1", true, true, 1),
  //                    new Answer(2, "Answer 2", false, true, 2)),
  //                1),
  //            new Question(
  //                2,
  //                "Suis-je une question ? ",
  //                true,
  //                Timestamp.from(Instant.now()),
  //                List.of(
  //                    new Answer(1, "Answer 1", true, true, 1),
  //                    new Answer(2, "Answer 2", false, true, 2)),
  //                2));
  //    Quiz quiz = new Quiz();
  //    quiz.setId(1);
  //    quiz.setLabel("quiz1");
  //    quiz.setActive(true);
  //    quiz.setQuestions(questionsExpected);
  //    var recordsExpected =
  //        List.of(
  //            new Record(
  //                1,
  //                3,
  //                10,
  //                List.of(
  //                    new Answer(1, "Answer 1", true, true, 1),
  //                    new Answer(2, "Answer 2", false, true, 2))),
  //            new Record(
  //                1,
  //                3,
  //                10,
  //                List.of(
  //                    new Answer(1, "Answer 1", true, true, 1),
  //                    new Answer(2, "Answer 2", false, true, 2))));
  //    when(repo.findAll()).thenReturn(List.copyOf(recordsExpected));
  //    var recordsFromService = service.findAll();
  //    for (var record : recordsFromService) {
  //      assertTrue(recordsFromService.contains(record));
  //    }
  //    assertEquals(recordsExpected.size(), recordsFromService.size());
  //  }
  //
  //  @Test
  //  void save() {
  //    Timestamp creationDate = new Timestamp(System.currentTimeMillis());
  //    User user =
  //        new User(
  //            1,
  //            "Aroun",
  //            "Radjavelou",
  //            "aroun.radjavelou@etu.utc.fr",
  //            "password",
  //            new Role("ADMIN"),
  //            "Thales",
  //            "0736416969",
  //            creationDate,
  //            true);
  //    var questionsExpected =
  //        List.of(
  //            new Question(
  //                1,
  //                "Quel est le resultat du test ? ",
  //                true,
  //                Timestamp.from(Instant.now()),
  //                List.of(
  //                    new Answer(1, "Answer 1", true, true, 1),
  //                    new Answer(2, "Answer 2", false, true, 2)),
  //                1),
  //            new Question(
  //                2,
  //                "Suis-je une question ? ",
  //                true,
  //                Timestamp.from(Instant.now()),
  //                List.of(
  //                    new Answer(1, "Answer 1", true, true, 1),
  //                    new Answer(2, "Answer 2", false, true, 2)),
  //                2));
  //    Quiz quiz = new Quiz();
  //    quiz.setId(1);
  //    quiz.setLabel("quiz1");
  //    quiz.setActive(true);
  //    quiz.setQuestions(questionsExpected);
  //    var expectedRecord =
  //        new Record(
  //            1,
  //            3,
  //            10,
  //            List.of(
  //                new Answer(1, "Answer 1", true, true, 1),
  //                new Answer(2, "Answer 2", false, true, 2)));
  //    when(repo.save(any(Record.class)))
  //        .thenReturn(
  //            new Record(
  //                1,
  //                3,
  //                10,
  //                List.of(
  //                    new Answer(1, "Answer 1", true, true, 1),
  //                    new Answer(2, "Answer 2", false, true, 2))));
  //    var recordFromRepo = repo.save(expectedRecord);
  //    assertThat(recordFromRepo, is(equalTo(expectedRecord)));
  //  }
  //
  //  @Test
  //  void findById() {
  //    Timestamp creationDate = new Timestamp(System.currentTimeMillis());
  //    User user =
  //        new User(
  //            1,
  //            "Aroun",
  //            "Radjavelou",
  //            "aroun.radjavelou@etu.utc.fr",
  //            "password",
  //            new Role("ADMIN"),
  //            "Thales",
  //            "0736416969",
  //            creationDate,
  //            true);
  //    var questionsExpected =
  //        List.of(
  //            new Question(
  //                1,
  //                "Quel est le resultat du test ? ",
  //                true,
  //                Timestamp.from(Instant.now()),
  //                List.of(
  //                    new Answer(1, "Answer 1", true, true, 1),
  //                    new Answer(2, "Answer 2", false, true, 2)),
  //                1),
  //            new Question(
  //                2,
  //                "Suis-je une question ? ",
  //                true,
  //                Timestamp.from(Instant.now()),
  //                List.of(
  //                    new Answer(1, "Answer 1", true, true, 1),
  //                    new Answer(2, "Answer 2", false, true, 2)),
  //                2));
  //    Quiz quiz = new Quiz();
  //    quiz.setId(1);
  //    quiz.setLabel("quiz1");
  //    quiz.setActive(true);
  //    quiz.setQuestions(questionsExpected);
  //    var expectedRecord =
  //        new Record(
  //            1,
  //            3,
  //            10,
  //            List.of(
  //                new Answer(1, "Answer 1", true, true, 1),
  //                new Answer(2, "Answer 2", false, true, 2)));
  //    when(repo.findById(1))
  //        .thenReturn(
  //            java.util.Optional.of(
  //                new Record(
  //                    1,
  //                    3,
  //                    10,
  //                    List.of(
  //                        new Answer(1, "Answer 1", true, true, 1),
  //                        new Answer(2, "Answer 2", false, true, 2)))));
  //    var recordFromRepo = repo.findById(1).get();
  //    assertEquals(recordFromRepo, expectedRecord);
  //  }

  @Mock private RecordRepository repo;
  @InjectMocks private RecordService service;
}
