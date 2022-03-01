package com.ai13qcm.controllers;

import com.ai13qcm.dto.UserDTO;
import com.ai13qcm.entities.Quiz;
import com.ai13qcm.entities.Record;
import com.ai13qcm.entities.User;
import com.ai13qcm.services.QuizService;
import com.ai13qcm.services.RecordService;
import com.ai13qcm.services.RoleService;
import com.ai13qcm.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

  private final UserService userService;
  private final RecordService recordService;
  private final RoleService roleService;
  private final QuizService quizService;

  public UserController(
      UserService service,
      RecordService recordService,
      RoleService roleService,
      QuizService quizService) {
    this.userService = service;
    this.recordService = recordService;
    this.roleService = roleService;
    this.quizService = quizService;
  }

  @GetMapping("/api/users")
  @PreAuthorize("hasAuthority('ADMIN')")
  List<User> getAll(@RequestParam(name = "traineesOnly", required = false) Boolean traineesOnly) {
    if (traineesOnly != null && traineesOnly == true) return userService.findAllTrainees();
    return userService.getAll();
  }

  @PostMapping("/api/users")
  @PreAuthorize("hasAuthority('ADMIN')")
  User newUser(@RequestBody User user) {
    return userService.save(user);
  }

  @PreAuthorize("hasAuthority('ADMIN') or authentication.getPrincipal().getId() == #id")
  @GetMapping("/api/users/{id}")
  User findById(@PathVariable Integer id) {
    try {
      return userService.findById(id);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PutMapping("/api/users/{id}")
  @PreAuthorize("hasAuthority('ADMIN') or authentication.getPrincipal().getId() ==#id")
  User update(@RequestBody UserDTO userDTO, @PathVariable Integer id) {
    var entity = convertFromDTO(userDTO);
    entity.setId(id);
    return userService.save(entity);
  }

  @PatchMapping("/api/users/{id}")
  @PreAuthorize("hasAuthority('ADMIN') or authentication.getPrincipal().getId() ==#id")
  User partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Integer id) {
    var entity = userService.findById(id);
    if (updates.containsKey("firstname")) {
      entity.setFirstname((String) updates.get("firstname"));
    }
    if (updates.containsKey("lastname")) {
      entity.setLastname((String) updates.get("lastname"));
    }
    if (updates.containsKey("email")) {
      entity.setEmail((String) updates.get("email"));
    }
    if (updates.containsKey("company")) {
      entity.setCompany((String) updates.get("company"));
    }
    if (updates.containsKey("phone")) {
      entity.setPhone((String) updates.get("phone"));
    }
    if (updates.containsKey("active")) {
      entity.setActive((Boolean) updates.get("active"));
    }
    return userService.save(entity);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/api/users/{id}")
  void deleteUser(@PathVariable Integer id) {
    try {
      recordService.deleteByUserId(id);
      userService.deleteById(id);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @GetMapping("/api/users/{id}/quizzes")
  @PreAuthorize("hasAnyAuthority('ADMIN','TRAINEE')")
  List<Quiz> getAllQuizByUserId(@PathVariable Integer id) {
    return quizService.selectAvailableQuizzesForUser(id);
  }

  @PreAuthorize("hasAuthority('ADMIN') or authentication.getPrincipal().getId() ==#id")
  @GetMapping("/api/users/{id}/records")
  List<Record> getAllRecordsForTheUser(@PathVariable Integer id) {
    return
            recordService.findAllByUserId(id);
  }

  private User convertFromDTO(UserDTO userDTO) {
    var user = new User();
    user.setActive(userDTO.isActive());
    user.setEmail(userDTO.getEmail());
    user.setFirstname(userDTO.getFirstname());
    user.setLastname(userDTO.getLastname());
    user.setPhone(userDTO.getPhone());
    user.setCompany(userDTO.getCompany());
    user.setRole(roleService.findByLabel(userDTO.getRole()));
    return user;
  }
}
