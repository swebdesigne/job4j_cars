package ru.job4j.cars.repository;

import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> findAll();
    List<Post> findForLastDay();

    Optional<Post> findWithPhoto();

    Optional<Post> findByMark(String name);
}
