package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Answer;
import ru.job4j.forum.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findCommentsByPostId(int id);

    Optional<Comment> findCommentByAnswers(Answer answer);
}
