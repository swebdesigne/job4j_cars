package ru.job4j.cars.repository;

import util.CreateTestCrudRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Set;

class HibernateCarRepositoryTest {
    private static final HibernateUserRepository HIBERNATE_USER_REPOSITORY =
            new HibernateUserRepository(CreateTestCrudRepository.getRepository());
    private static final HibernateCarRepository HIBERNATE_CAR_REPOSITORY =
            new HibernateCarRepository(CreateTestCrudRepository.getRepository());
    private static final HibernateOwnerRepository HIBERNATE_OWNER_REPOSITORY =
            new HibernateOwnerRepository(CreateTestCrudRepository.getRepository());
    private static final HibernateEngineRepository HIBERNATE_ENGINE_REPOSITORY =
            new HibernateEngineRepository(CreateTestCrudRepository.getRepository());

    @AfterEach
    public void wipeDb() {
        HIBERNATE_CAR_REPOSITORY.findAll().forEach(car -> HIBERNATE_CAR_REPOSITORY.delete(car.getId()));
        HIBERNATE_OWNER_REPOSITORY.findAll().forEach(owner -> HIBERNATE_OWNER_REPOSITORY.delete(owner.getId()));
        HIBERNATE_ENGINE_REPOSITORY.findAll().forEach(engine -> HIBERNATE_ENGINE_REPOSITORY.delete(engine.getId()));
        HIBERNATE_USER_REPOSITORY.findAllOrderById().forEach(user -> HIBERNATE_USER_REPOSITORY.delete(user.getId()));
    }

    private Car createCar(String userName, String ownerName) {
        var user = new User();
        user.setLogin(userName);
        user.setPassword("123");
        var owner = new Owner();
        owner.setName(ownerName);
        owner.setUser(
                HIBERNATE_USER_REPOSITORY.create(user).orElse(new User())
        );
        var engine = new Engine();
        engine.setName("V2");
        var car = new Car();
        car.setName("Audi");
        car.setEngine(
                HIBERNATE_ENGINE_REPOSITORY.add(engine).orElse(new Engine())
        );
        car.setOwners(
                Set.of(HIBERNATE_OWNER_REPOSITORY.add(owner).orElse(new Owner()))
        );
        return HIBERNATE_CAR_REPOSITORY.add(car).orElse(new Car());
    }

    @Test
    public void whenAddThenFindAll() {
        List<Car> cars = List.of(
                createCar("Igor", "Alina"),
                createCar("Petr", "Boris")
        );
        Assertions.assertEquals(cars, HIBERNATE_CAR_REPOSITORY.findAll());
    }

    @Test
    public void whenAddThenFindById() {
        var car = createCar("Igor", "Alina");
        var res = HIBERNATE_CAR_REPOSITORY.findById(car.getId()).orElse(new Car());
        Assertions.assertEquals(car, res);
    }

    @Test
    public void whenAddThenUpdate() {
        var car = createCar("Igor", "Alina");
        car.setName("BMW");
        Assertions.assertTrue(HIBERNATE_CAR_REPOSITORY.update(car));
    }

    @Test
    void whenAddThenDelete() {
        var car = createCar("Igor", "Alina");
        Assertions.assertTrue(HIBERNATE_CAR_REPOSITORY.delete(car.getId()));
    }
}