package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.util.Date;
import java.util.Optional;

/** Класс отвечающий за управления комментариями
 * @author Boblak Kirill
 * */
@Controller
public class CommentControl {
    private final PostService posts;
    private final UserService users;
    private final CommentService comments;

    public CommentControl(PostService posts, UserService users, CommentService comments) {
        this.posts = posts;
        this.users = users;
        this.comments = comments;
    }

    /**
     * @param id поста, к которому принадлежит комментарий
     * */
    @PostMapping("/saveComment")
    public String save(@RequestParam("id") int id, @ModelAttribute Comment comment) {
        comment.setCreated(new Date(System.currentTimeMillis()));
        comment.setAuthor(users.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get());
        comment.setPost(posts.findById(id).get());
        comments.add(comment);
        return "redirect:/post?id=" + id;
    }

    /**
     * @param id комментария, который надо удалить
     * */
    @GetMapping("/deleteComment")
    public String delete(@RequestParam("id") int id) {
        Optional<Comment> comment = comments.findById(id);
        if (comment.isEmpty()) {
            return "redirect:/index";
        }
        int postId = comment.get().getPost().getId();
        comments.delete(comment.get());
        return "redirect:/post?id=" + postId;
    }

    /**
     * @param id поста, к которому принадлежит комментарий
     * */
    @GetMapping("/comment")
    public String create(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", posts.findById(id).get());
        return "comments/create";
    }
}
