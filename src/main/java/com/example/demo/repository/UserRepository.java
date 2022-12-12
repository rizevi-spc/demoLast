package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * user jpa repository
 */
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserName(String userName);
}
