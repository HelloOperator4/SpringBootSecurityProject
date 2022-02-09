package ru.mangadash.springbootsecurityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mangadash.springbootsecurityproject.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}
