package ru.mangadash.springbootsecurityproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.mangadash.springbootsecurityproject.model.User;
import ru.mangadash.springbootsecurityproject.service.UserService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String users(@RequestParam(name = "username", required = false) String username, Model model) {
        model.addAttribute("users", userService.allUsers(username));
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserFrom(User user, Model model) {
        model.addAttribute("user", user);
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@RequestParam("file1") MultipartFile file1,
                             @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3, User user) throws IOException {
        userService.saveUser(user, file1, file2, file3);
        return "redirect:/users";
    }

    @GetMapping(value = {"/user-delete/{id}", "/user-info/user-delete/{id}"})
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping(value = {"/user-update/{id}", "/user-info/user-update/{id}"})
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@RequestParam("file1") MultipartFile file1,
                             @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3, User user) throws IOException {
        userService.saveUser(user, file1, file2, file3);
        return "redirect:/users";
    }

    @GetMapping("/user-info/{id}")
    public String userInfo(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("images", user.getImages());
        return "user-info";
    }
}
