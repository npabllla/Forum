package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Answer;
import ru.job4j.forum.repository.AnswerRepository;

import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answers;

    public AnswerService(AnswerRepository answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.save(answer);
    }

    public void delete(Answer answer) {
        answers.delete(answer);
    }

    public Optional<Answer> findById(int id) {
        return answers.findById(id);
    }
}
