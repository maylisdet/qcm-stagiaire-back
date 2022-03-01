package com.ai13qcm.repositories;

import com.ai13qcm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    @Query(value = "select * from user u where u.role_id = (select id from role where label = 'TRAINEE')", nativeQuery = true)
    List<User> findAllTrainees();
}