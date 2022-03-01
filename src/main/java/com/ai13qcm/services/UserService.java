package com.ai13qcm.services;

import com.ai13qcm.entities.User;
import com.ai13qcm.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }
    public List<User> getAll(){
        return repo.findAll();
    }
    public User save( User user){
        if (user.getPassword().length() < 6)
            throw new IllegalArgumentException("Password length must be greater than or equal to 6");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String emailValidationRegexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern validator = Pattern.compile(emailValidationRegexp);
        if (!validator.matcher(user.getEmail()).matches()){
            throw new IllegalArgumentException("Invalid email");
        }
        return repo.save(user);
    }

    public User findById( Integer id){
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("No user with the id %d", id)));
    }
    public List<User> findAllTrainees(){
        return repo.findAllTrainees();
    }
    public void deleteById( Integer id){
        repo.deleteById(id);
    }


    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    public UserDetails loadUserByEmail(String mail) {
        return null;
    }

}
