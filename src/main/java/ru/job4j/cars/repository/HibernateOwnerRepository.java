package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
@ThreadSafe
@Slf4j
public class HibernateOwnerRepository implements OwnerRepository {
    private final CrudRepository crudRepository;
    private final static String FIND_ALL = "FROM Owner";
    private final static String GET_BY_ID = "FROM Owner WHERE id = :fID";
    private final static String DELETE = "DELETE Owner WHERE id = :fID";

    @Override
    public Optional<Owner> add(Owner owner) {
        crudRepository.run(session -> session.persist(owner));
        return Optional.of(owner);
    }

    @Override
    public List<Owner> findAll() {
        return crudRepository.query(FIND_ALL, Owner.class);
    }

    @Override
    public Optional<Owner> findById(int id) {
        return crudRepository.optional(GET_BY_ID, Owner.class,
                Map.of("fID", id));
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

    @Override
    public boolean update(Owner owner) {
        try {
            crudRepository.run(session -> session.merge(owner));
            return true;
        } catch (Exception e) {
            log.error("The error at update", e);
        }
        return false;
    }
}
