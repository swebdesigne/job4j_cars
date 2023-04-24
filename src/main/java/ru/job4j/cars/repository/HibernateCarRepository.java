package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.*;

@AllArgsConstructor
@ThreadSafe
@Repository
@Slf4j
public class HibernateCarRepository implements CarRepository {
    private final CrudRepository crudRepository;
    private final static String FIND_ALL = "FROM Car f " +
            "JOIN FETCH f.model " +
            "JOIN FETCH f.owners " +
            "JOIN FETCH f.engine " +
            "JOIN FETCH f.year " +
            "JOIN FETCH f.actuator " +
            "JOIN FETCH f.transmission " +
            "ORDER BY f.name, f.model.name, f.year.name, f.engine.name, f.actuator.name, f.transmission.name";
    private final static String FIND_BY_ID = "FROM Car f JOIN FETCH f.owners JOIN FETCH f.engine WHERE f.id = :fID";
    private final static String FIND_BY_PROPERTY = "FROM Car f " +
            "JOIN FETCH f.model " +
            "JOIN FETCH f.owners " +
            "JOIN FETCH f.engine " +
            "JOIN FETCH f.year " +
            "JOIN FETCH f.actuator " +
            "JOIN FETCH f.transmission ";
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
    public Optional<Car> findByProperty(Car car) {
        Map<String, Object> props = new HashMap<>();
        String query = generateQuery(car, props);
        return crudRepository.optional(query, Car.class, props);
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

    public List<Car> ajaxFindProperty(Car car) {
        Map<String, Object> props = new HashMap<>();
        String query = generateQuery(car, props);
        return crudRepository.query(query, Car.class, props);
    }

    private String generateQuery(Car car, Map<String, Object> props) {
        String query = "";
        query += FIND_BY_PROPERTY;
        query += conditionForQuery(car.getName(), props, "fCAR", "WHERE f.name = :");
        query += conditionForQuery(car.getYear().getName(), props, "fYEAR", "AND f.year.name = :");
        query += conditionForQuery(car.getModel().getName(), props, "fMODEL", "AND f.model.name = :");
        query += conditionForQuery(car.getEngine().getName(), props, "fENGINE", "AND f.engine.name = :");
        query += conditionForQuery(car.getActuator().getName(), props, "fACTUATOR", "AND f.actuator.name = :");
        query += conditionForQuery(car.getTransmission().getName(), props, "fTRANSMISSION", "AND f.transmission.name = :");
        return query;
    }

    private String conditionForQuery(String obj, Map<String, Object> props, String name, String where) {
        if (Objects.nonNull(obj)) {
            props.put(name, obj);
            return where + name + " ";
        }
        return "";
    }
}
