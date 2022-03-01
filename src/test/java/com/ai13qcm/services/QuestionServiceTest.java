package com.ai13qcm.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class QuestionServiceTest {
  /*
   @Test
   void save() {
     var creationDate = Timestamp.from(Instant.now());
     var expectedQuestion =
         new Question(
             1,
             "Quel est le resultat du test ? ",
             true,
             creationDate,
             List.of(
                 new Answer(1, "Answer 1", true, true, 1),
                 new Answer(2, "Answer 2", false, true, 2)), 1);
     when(repo.save(any(Question.class)))
         .thenReturn(
             new Question(
                 1,
                 "Quel est le resultat du test ? ",
                 true,
                 creationDate,
                 List.of(
                     new Answer(1, "Answer 1", true, true, 1),
                     new Answer(2, "Answer 2", false, true, 2)), 2));
     var questionFromRepo = repo.save(expectedQuestion);
     assertThat(questionFromRepo, is(equalTo(expectedQuestion)));
   }

   @Test
   void findById() {
     var creationDate = Timestamp.from(Instant.now());

     var expectedQuestion =
         new Question(
             1,
             "Quel est le resultat du test ? ",
             true,
             creationDate,
             List.of(
                 new Answer(1, "Answer 1", true, true, 1),
                 new Answer(2, "Answer 2", false, true, 1)), 1);
     when(repo.findById(1))
         .thenReturn(
             java.util.Optional.of(
                 new Question(
                     1,
                     "Quel est le resultat du test ? ",
                     true,
                     creationDate,
                     List.of(
                         new Answer(1, "Answer 1", true, true, 1),
                         new Answer(2, "Answer 2", false, true, 1)),2)));
     var questionFromRepo = repo.findById(1).get();
     assertEquals(questionFromRepo, expectedQuestion);
   }


   @Mock private QuestionRepository repo;

   @Test
   void findAll() {
     var questionsExpected =
             List.of(
                     new Question(
                             1,
                             "Quel est le resultat du test ? ",
                             true,
                             Timestamp.from(Instant.now()),
                             List.of(
                                     new Answer(1, "Answer 1", true, true, 1),
                                     new Answer(2, "Answer 2", false, true, 2)),
                             1),
                     new Question(
                             2,
                             "Suis-je une question ? ",
                             true,
                             Timestamp.from(Instant.now()),
                             List.of(
                                     new Answer(1, "Answer 1", true, true, 1),
                                     new Answer(2, "Answer 2", false, true, 2)),
                             2));
     when(repo.findAll()).thenReturn(List.copyOf(questionsExpected));
     var questionsFromService = service.findAll();
     for (var question : questionsFromService) {
       assertTrue(questionsExpected.contains(question));
     }
     assertEquals(questionsExpected.size(), questionsFromService.size());
   }
   @InjectMocks private QuestionService service;

  */
}
