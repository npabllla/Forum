package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository posts;

    public PostService(UserService users, PostRepository posts) {
        this.posts = posts;
    }

    public void add(Post post) {
        posts.save(post);
    }

    public Optional<Post> findById(int id) {
        return posts.findById(id);
    }

    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        this.posts.findAll().forEach(posts::add);
        return posts;
    }
}