package com.ai13qcm.controllers;

import com.ai13qcm.entities.Answer;
import com.ai13qcm.services.AnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AnswerControllerTest {

  @Test
  public void getByIdShouldReturnTheAnswerInJSON() throws Exception {
    var expectedAnswer = new Answer(1, "Test answer", true, false, 1);
    when(answerService.findById(1)).thenReturn(new Answer(1, "Test answer", true, false, 1));
    this.mockMvc
        .perform(get(URL + "/{id}", 1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(expectedAnswer.getId())))
        .andExpect(jsonPath("$.label", is(expectedAnswer.getLabel())))
        .andExpect(jsonPath("$.position", is(expectedAnswer.getPosition())))
        .andExpect(jsonPath("$.active", is(expectedAnswer.isActive())))
        .andExpect(jsonPath("$.correct", is(expectedAnswer.isCorrect())));
  }

  @Autowired private MockMvc mockMvc;
  @MockBean private AnswerService answerService;
  private static final String URL = "/api/answers";
}
