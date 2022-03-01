package com.ai13qcm.security;

import com.ai13qcm.entities.User;
import com.ai13qcm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) {
    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        String.format("No user with the email %s found", email)));

    return UserDetailsImpl.build(user);
  }
}
