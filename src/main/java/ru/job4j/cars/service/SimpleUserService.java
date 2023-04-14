package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.HibernateUserRepository;

import java.util.Optional;

@Service
@ThreadSafe
@AllArgsConstructor
public class SimpleUserService implements UserService {
    private final HibernateUserRepository hibernateUserRepository;

    @Override
    public Optional<User> create(User user) {
        return hibernateUserRepository.create(user);
    }

    @Override
    public boolean update(User user) {
        return hibernateUserRepository.update(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return hibernateUserRepository.findByLogin(login);
    }

    @Override
    public boolean delete(User user) {
        return hibernateUserRepository.delete(user);
    }
}
