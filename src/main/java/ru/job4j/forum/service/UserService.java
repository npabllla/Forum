package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        users.add(User.of("test", "testuser@mail.ru", "123"));
    }

    public List<User> getAll() {
        return users;
    }

    public void add(User user) {
        users.add(user);
    }

    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(e -> e.getEmail().equals(email))
                .findFirst();
    }
}
