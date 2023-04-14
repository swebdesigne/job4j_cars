package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.repository.HibernatePostRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SimplePostService implements PostService {
    private final HibernatePostRepository hibernatePostRepository;

    @Override
    public List<ru.job4j.cars.model.Post> findAll() {
        return hibernatePostRepository.findAll();
    }
}
