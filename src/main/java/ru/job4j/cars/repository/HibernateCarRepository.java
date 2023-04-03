package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@ThreadSafe
@Repository
@Slf4j
public class HibernateCarRepository implements CarRepository {
    private final CrudRepository crudRepository;
    private final static String FIND_ALL = "FROM Car f JOIN FETCH f.owners JOIN FETCH f.engine";
    private final static String FIND_BY_ID = "FROM Car f JOIN FETCH f.owners JOIN FETCH f.engine WHERE f.id = :fID";
    private final static String DELETE = "DELETE Car WHERE id = :fID";

    @Override
    public Optional<Car> add(Car car) {
        crudRepository.run(session -> session.persist(car));
        return Optional.of(car);
    }

    @Override
    public List<Car> findAll() {
        return crudRepository.query(FIND_ALL, Car.class);
    }

    @Override
    public Optional<Car> findById(int id) {
        return crudRepository.optional(FIND_BY_ID,
                Car.class, Map.of("fID", id)
        );
    }

    @Override
    public boolean update(Car car) {
        try {
            crudRepository.run(session -> session.merge(car));
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
