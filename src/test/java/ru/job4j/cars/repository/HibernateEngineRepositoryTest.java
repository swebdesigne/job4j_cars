package ru.job4j.cars.repository;

import util.CreateTestCrudRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Engine;

import java.util.List;

class HibernateEngineRepositoryTest {
    private static final HibernateEngineRepository HIBERNATE_ENGINE_REPOSITORY =
            new HibernateEngineRepository(CreateTestCrudRepository.getRepository());

    @AfterEach
    public void wipeDb() {
        HIBERNATE_ENGINE_REPOSITORY.findAll().forEach(engine -> HIBERNATE_ENGINE_REPOSITORY.delete(engine.getId()));
    }

    @Test
    void whenAdd() {
        var engine = new Engine();
        engine.setName("V2");
        var res = HIBERNATE_ENGINE_REPOSITORY.add(engine).orElse(new Engine());
        Assertions.assertEquals(engine.getName(), res.getName());
    }

    @Test
    void whenFindAll() {
        var engine = new Engine();
        engine.setName("V2");
        var engine2 = new Engine();
        engine2.setName("V6");
        List<Engine> engines = List.of(
                HIBERNATE_ENGINE_REPOSITORY.add(engine).orElse(new Engine()),
                HIBERNATE_ENGINE_REPOSITORY.add(engine2).orElse(new Engine())
        );
        Assertions.assertEquals(engines, HIBERNATE_ENGINE_REPOSITORY.findAll());
    }

    @Test
    void whenFndById() {
        var engine = new Engine();
        engine.setName("V2");
        HIBERNATE_ENGINE_REPOSITORY.add(engine);
        var res = HIBERNATE_ENGINE_REPOSITORY.findById(engine.getId()).orElse(new Engine());
        Assertions.assertEquals(engine, res);
    }

    @Test
    void whenUpdate() {
        var engine = new Engine();
        engine.setName("V2");
        var res = HIBERNATE_ENGINE_REPOSITORY.add(engine).orElse(new Engine());
        res.setName("V6");
        Assertions.assertTrue(HIBERNATE_ENGINE_REPOSITORY.update(res));
    }

    @Test
    void whenDelete() {
        var engine = new Engine();
        engine.setName("V2");
        var res = HIBERNATE_ENGINE_REPOSITORY.add(engine).orElse(new Engine());
        Assertions.assertTrue(HIBERNATE_ENGINE_REPOSITORY.delete(res.getId()));
    }
}