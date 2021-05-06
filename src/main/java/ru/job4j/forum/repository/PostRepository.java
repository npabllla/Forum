package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query(value = "select * from posts where id = (select post_id from comments where id=:id)", nativeQuery = true)
    Optional<Post> findPostByCommentId(int id);
}