package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Answer;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.service.AnswerService;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.util.Date;
import java.util.Optional;

/** Класс отвечающий за управления ответами к комментариям
 * @author Boblak Kirill
 * */
@Controller
public class AnswerController {
    private final AnswerService answers;

    private final CommentService comments;

    private final PostService posts;

    private final UserService users;

    public AnswerController(AnswerService answers, CommentService comments, PostService posts, UserService users) {
        this.answers = answers;
        this.comments = comments;
        this.posts = posts;
        this.users = users;
    }

    /**
     * @param id комментария, к которому принадлежит ответ
     * */
    @PostMapping("/saveAnswer")
    public String save(@RequestParam("id") int id, @ModelAttribute Answer answer) {
        Optional<Comment> comment = comments.findById(id);
        if (comment.isEmpty()) {
            return "redirect:/index";
        }
        int postId = posts.findPostByCommentId(comment.get().getId()).get().getId();
        answer.setCreated(new Date(System.currentTimeMillis()));
        answer.setAuthor(users.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get());
        answers.add(answer);
        comment.get().addAnswer(answer);
        comments.add(comment.get());
        return "redirect:/post?id=" + postId;
    }

    /**
     * @param id ответа, который надо удалить
     * */
    @GetMapping("/deleteAnswer")
    public String delete(@RequestParam("id") int id) {
        Optional<Answer> answer = answers.findById(id);
        if (answer.isEmpty()) {
            return "redirect:/index";
        }
        Optional<Comment> comment = comments.findCommentByAnswer(answer.get());
        comment.get().deleteAnswer(answer.get());
        answers.delete(answer.get());
        return "redirect:/post?id=" + posts.findPostByCommentId(comment.get().getId()).get().getId();
    }

    /**
     * @param id комментария, к которому принадлежит ответ
     * */
    @GetMapping("/answer")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("comment", comments.findById(id).get());
        return "answers/create";
    }
}
