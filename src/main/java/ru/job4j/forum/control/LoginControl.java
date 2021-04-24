package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import java.util.Optional;

@Controller
public class LoginControl {
    private final UserService users;

    public LoginControl(UserService users) {
        this.users = users;
    }

    @PostMapping("/login")
    public String loginPage(@ModelAttribute User user, Model model) {
        Optional<User> userFromMem = users.findByEmail(user.getEmail());
        if (userFromMem.isEmpty() || !userFromMem.get().getPassword().equals(user.getPassword())) {
            model.addAttribute("error", "Неверный логин или пароль");
            return "login";
        }
        System.out.println(userFromMem.get().getPassword());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String reg(@ModelAttribute Post post) {
        return "login";
    }

}