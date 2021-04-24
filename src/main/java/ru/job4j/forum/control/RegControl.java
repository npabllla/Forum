package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import java.util.Optional;

@Controller
public class RegControl {
    private final UserService users;

    public RegControl(UserService users) {
        this.users = users;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user, Model model) {
        Optional<User> userFromMem = users.findByEmail(user.getEmail());
        if (userFromMem.isPresent()) {
            model.addAttribute("error", "Данная почта уже используется");
            return "reg";
        }
        users.add(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }
}
