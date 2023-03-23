package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@ThreadSafe
@Repository
@Slf4j
public class HibernateEngineRepository implements EngineRepository {
    private final CrudRepository crudRepository;
    private final static String FIND_ALL = "FROM Engine";
    private final static String FIND_BY_ID = "FROM Engine WHERE id = :fID";
    private final static String DELETE = "DELETE Engine WHERE id = :fID";

    @Override
    public void add(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
    }

    @Override
    public List<Engine> findAll() {
        return crudRepository.query(FIND_ALL, Engine.class);
    }

    @Override
    public Optional<Engine> findById(int id) {
        return crudRepository.optional(FIND_BY_ID, Engine.class,
                Map.of("fID", id));
    }

    @Override
    public boolean update(Engine engine) {
        try {
            crudRepository.run(session -> session.merge(engine));
            return true;
        } catch (Exception e) {
            log.error("The error at update", e);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            crudRepository.run(DELETE, Map.of("fID", id));
            return true;
        } catch (Exception e) {
            log.error("The error at delete", e);
        }
        return false;
    }
}
