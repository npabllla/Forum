package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Answer;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository comments;

    public CommentService(CommentRepository comments) {
        this.comments = comments;
    }

    public void add(Comment comment) {
        comments.save(comment);
    }

    public void delete(Comment comment) {
        comments.delete(comment);
    }

    public Optional<Comment> findById(int id) {
        return comments.findById(id);
    }

    public Optional<Comment> findCommentByAnswer(Answer answer) {
        return comments.findCommentByAnswers(answer);
    }

    public List<Comment> findCommentsByPostId(int postId) {
        return comments.findCommentsByPostId(postId);
    }
}
