package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {
}
