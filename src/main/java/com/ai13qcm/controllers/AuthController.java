package com.ai13qcm.controllers;

import com.ai13qcm.entities.Role;
import com.ai13qcm.entities.User;
import com.ai13qcm.payload.request.LoginRequest;
import com.ai13qcm.payload.request.SignupRequest;
import com.ai13qcm.payload.response.JwtResponse;
import com.ai13qcm.payload.response.MessageResponse;
import com.ai13qcm.repositories.RoleRepository;
import com.ai13qcm.security.JwtUtils;
import com.ai13qcm.security.UserDetailsImpl;
import com.ai13qcm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired AuthenticationManager authenticationManager;

  @Autowired
  UserService userRepository;

  @Autowired RoleRepository roleRepository;

  @Autowired PasswordEncoder encoder;

  @Autowired JwtUtils jwtUtils;

  @Autowired public JavaMailSender emailSender;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    return ResponseEntity.ok(
        new JwtResponse(
            jwt,
            userDetails.getId(),
            userDetails.getFirstName(),
            userDetails.getLastName(),
            userDetails.getEmail(),
            roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account

    String strRole = signUpRequest.getRole();
    Role userRole = null;
    if (strRole == null) {
      userRole =
          roleRepository
              .findByLabel(Role.ROLE_TRAINEE)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    } else {
      userRole =
          roleRepository
              .findByLabel(strRole)
              .orElseThrow(
                  () ->
                      new RuntimeException(
                          String.format("No role with the label %s found !", strRole)));
    }
    User user =
        new User(
            signUpRequest.getFirstname(),
            signUpRequest.getLastname(),
            signUpRequest.getEmail(),
            signUpRequest.getPhone(),
            signUpRequest.getCompany(),
            signUpRequest.getPassword(),
            userRole);
    try{
      userRepository.save(user);
    }catch (IllegalArgumentException e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(user.getEmail());
    message.setSubject("[No-Reply] Confirmation email");
    message.setText(
            String.format("login : %s password : %s", user.getEmail(), signUpRequest.getPassword()));

    // Send Message!
    this.emailSender.send(message);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
