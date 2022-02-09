package ru.mangadash.springbootsecurityproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mangadash.springbootsecurityproject.model.User;
import ru.mangadash.springbootsecurityproject.repository.UserRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> allUsers(String username) {
        List<User> users = userRepository.findByUsername(username);
        if (username != null) {
             return userRepository.findByUsername(username);
        }
        return userRepository.findAll();
    }

//    public List<User> allUsers() {
//        return userRepository.findAll();
//    }

    public void saveUser(User user) {
        log.info("Saving new {}", user);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
