package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();
    private final UserService users;

    private final AtomicInteger count = new AtomicInteger(0);

    public PostService(UserService users) {
        this.users = users;
        add(Post.of("Продам машину", "Продаю машину ладу 01.", this.users.getAll().get(0)));
    }

    public void add(Post post) {
        post.setId(count.intValue());
        posts.add(post);
        count.incrementAndGet();
    }

    public Optional<Post> findById(int id) {
        return posts.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public Optional<Post> findByName(String name) {
        return posts.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
    }

    public List<Post> getAll() {
        return posts;
    }
}