package ru.job4j.cars.repository;

import ru.job4j.cars.model.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {
    void add(Owner owner);

    List<Owner> findAll();

    Optional<Owner> findById(int id);

    boolean delete(int id);

    boolean update(Owner owner);
}
