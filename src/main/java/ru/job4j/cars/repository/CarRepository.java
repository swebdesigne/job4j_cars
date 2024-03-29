package ru.job4j.cars.repository;

import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    Optional<Car> add(Car car);

    List<Car> findAll();

    Optional<Car> findById(int id);

    boolean update(Car car);

    boolean delete(int id);

    Optional<Car> findByProperty(Car car);
}
