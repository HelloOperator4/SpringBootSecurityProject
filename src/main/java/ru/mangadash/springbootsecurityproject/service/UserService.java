package ru.mangadash.springbootsecurityproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mangadash.springbootsecurityproject.model.Image;
import ru.mangadash.springbootsecurityproject.model.User;
import ru.mangadash.springbootsecurityproject.repository.UserRepository;

import java.io.IOException;
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

    public void saveUser(User user, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;

        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            user.addImageToUser(image1);
        }

        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            user.addImageToUser(image2);
        }

        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            user.addImageToUser(image3);
        }
        log.info("Saving new User. Username: {}; Email: {};", user.getUsername(), user.getEmail());
        User userFromDB = userRepository.save(user);
        userFromDB.setPreviewImageId(userFromDB.getImages().get(0).getId());
        userRepository.save(user);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
