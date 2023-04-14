package ru.job4j.cars.repository;

import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> create(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAllOrderById();

    Optional<User> findById(int userId);

    public List<User> findByLikeLogin(String key);

    Optional<User> findByLogin(String login);
}
