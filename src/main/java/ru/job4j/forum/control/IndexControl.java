package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

/** Класс отвечающий за загрузку основной страницы
 * @author Boblak Kirill
 * */
@Controller
public class IndexControl {
    private final PostService posts;
    private final UserService users;

    public IndexControl(PostService posts, UserService users) {
        this.posts = posts;
        this.users = users;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", posts.getAll());
        model.addAttribute("user", users.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()).get());
        return "index";
    }
}