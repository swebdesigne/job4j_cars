package ru.job4j.cars.service;

import ru.job4j.cars.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> create(User user);
    boolean update(User user);
    Optional<User> findByLogin(String login);
    boolean delete(User user);
}
