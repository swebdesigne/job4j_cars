package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@ThreadSafe
@Repository
@Slf4j
public class HibernateUserRepository implements UserRepository {
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     *
     * @param user пользователь.
     * @return пользователь с id.
     */
    @Override
    public Optional<User> create(User user) {
        try {
            crudRepository.run(session -> session.persist(user));
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Обновить в базе пользователя.
     *
     * @param user пользователь.
     */
    @Override
    public boolean update(User user) {
        try {
            crudRepository.run(session -> session.merge(user));
            return true;
        } catch (Exception e) {
            log.error("The error at update", e);
        }
        return false;
    }

    /**
     * Удалить пользователя.
     *
     * @param user user
     */
    @Override
    public boolean delete(User user) {
        try {
            crudRepository.run(session -> session.delete(user)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Список пользователь отсортированных по id.
     *
     * @return список пользователей.
     */
    @Override
    public List<User> findAllOrderById() {
        return crudRepository.query("from User order by id asc", User.class);
    }

    /**
     * Найти пользователя по ID
     *
     * @return пользователь.
     */
    @Override
    public Optional<User> findById(int userId) {
        return crudRepository.optional(
                "from User where id = :fId", User.class,
                Map.of("fId", userId)
        );
    }

    /**
     * Список пользователей по login LIKE %key%
     *
     * @param key key
     * @return список пользователей.
     */
    @Override
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query(
                "from User where login like :fKey", User.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    /**
     * Найти пользователя по login.
     *
     * @param login login.
     * @return Optional or user.
     */
    @Override
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional(
                "from User where login = :fLogin", User.class,
                Map.of("fLogin", login)
        );
    }
}