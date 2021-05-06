package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.util.Date;
import java.util.Optional;

@Controller
public class PostControl {
    private final PostService posts;

    private final UserService users;

    private final CommentService comments;

    public PostControl(PostService posts, UserService users, CommentService comments) {
        this.posts = posts;
        this.users = users;
        this.comments = comments;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        post.setCreated(new Date(System.currentTimeMillis()));
        Optional<User> user =  users.findByEmail((SecurityContextHolder.getContext().getAuthentication().getName()));
        user.ifPresent(post::setAuthor);
        posts.add(post);
        return "redirect:/post?id=" + posts.findById(post.getId()).get().getId();
    }

    @GetMapping("delete")
    public String delete(@RequestParam("id") int id) {
        if (posts.findById(id).isEmpty()) {
            return "redirect:/index";
        }
        posts.delete(posts.findById(id).get());
        return "redirect:/index";
    }

    @GetMapping("/create")
    public String create() {
        return "/posts/create";
    }

    @GetMapping("/load")
    public String updateLoad(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", posts.findById(id).get());
        return "posts/update";
    }

    @GetMapping("/post")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", posts.findById(id).get());
        model.addAttribute("user",
                users.findByEmail((SecurityContextHolder.getContext().getAuthentication().getName())).get());
        model.addAttribute("comments", comments.findCommentsByPostId(id));
        return "posts/post";
    }
}
