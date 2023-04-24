package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.HibernatePostRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePostService implements PostService {
    private final HibernatePostRepository hibernatePostRepository;

    @Override
    public List<Post> findAll() {
        return hibernatePostRepository.findAll();
    }

    @Override
    public Optional<Post> add(Post post) {
        return hibernatePostRepository.add(post);
    }

    public Optional<Post> findById(int id) {
        return hibernatePostRepository.findById(id);
    }
}
