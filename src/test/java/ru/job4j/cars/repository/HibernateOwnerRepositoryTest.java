package ru.job4j.cars.repository;

import util.CreateTestCrudRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.User;

import java.util.List;

class HibernateOwnerRepositoryTest {
    private static final HibernateUserRepository HIBERNATE_USER_REPOSITORY =
            new HibernateUserRepository(CreateTestCrudRepository.getRepository());
    private static final HibernateOwnerRepository HIBERNATE_OWNER_REPOSITORY =
            new HibernateOwnerRepository(CreateTestCrudRepository.getRepository());

    @AfterEach
    public void wipeDb() {
        HIBERNATE_OWNER_REPOSITORY.findAll().forEach(owner -> HIBERNATE_OWNER_REPOSITORY.delete(owner.getId()));
        HIBERNATE_USER_REPOSITORY.findAllOrderById().forEach(HIBERNATE_USER_REPOSITORY::delete);
    }

    private Owner createOwner(String userName, String ownerName) {
        var user = new User();
        user.setLogin(userName);
        user.setPassword("123");
        var owner = new Owner();
        owner.setName(ownerName);
        owner.setUser(
                HIBERNATE_USER_REPOSITORY.create(user).orElse(new User())
        );
        return HIBERNATE_OWNER_REPOSITORY.add(owner).orElse(new Owner());
    }

    @Test
    void whenAdd() {
        var userName = "Igor";
        var ownerName = "Alina";
        Assertions.assertEquals(ownerName, createOwner(userName, ownerName).getName());
    }

    @Test
    void whenFindAll() {
        List<Owner> owners = List.of(
                createOwner("Igor", "Alina"),
                createOwner("Boris", "Petr")
        );
        Assertions.assertEquals(owners, HIBERNATE_OWNER_REPOSITORY.findAll());
    }


    @Test
    void whenFindById() {
        var owner = createOwner("Igor", "Alina");
        var res = HIBERNATE_OWNER_REPOSITORY.findById(owner.getId()).orElse(new Owner());
        Assertions.assertEquals(owner, res);
    }

    @Test
    void whenDelete() {
        var owner = createOwner("Igor", "Alina");
        Assertions.assertTrue(HIBERNATE_OWNER_REPOSITORY.delete(owner.getId()));
    }

    @Test
    void whenUpdate() {
        var owner = createOwner("Igor", "Alina");
        owner.setName("Boris");
        Assertions.assertTrue(HIBERNATE_OWNER_REPOSITORY.update(owner));
    }
}