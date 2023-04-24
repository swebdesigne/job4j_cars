package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.HibernateEngineRepository;

import java.util.List;

@Service
@ThreadSafe
@AllArgsConstructor
public class SimpleEngineService implements EngineService {
    private final HibernateEngineRepository hibernateEngineRepository;

    @Override
    public List<Engine> findAll() {
        return hibernateEngineRepository.findAll();
    }
}
