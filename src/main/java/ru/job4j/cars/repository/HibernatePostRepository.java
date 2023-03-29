package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@ThreadSafe
@AllArgsConstructor
@Repository
public class HibernatePostRepository implements PostRepository {
    private final CrudRepository crudRepository;
    private final static String FIND_BY_LAST_DAY = "FROM Post WHERE created >= :fCreated";
    private final static String FIND_WITH_PHOTO = "FROM Post WHERE photo != null";
    private final static String FIND_BY_MARK_OF_CAR = "FROM Post c JOIN FETCH c.car WHERE c.car.name = :fName";

    @Override
    public List<Post> findForLastDay() {
        return crudRepository.query(FIND_BY_LAST_DAY, Post.class,
                Map.of("fCreated", LocalDateTime.now().minusDays(1))
        );
    }

    @Override
    public Optional<Post> findWithPhoto() {
        return crudRepository.optional(FIND_WITH_PHOTO, Post.class);
    }

    @Override
    public Optional<Post> findByMark(String name) {
        return crudRepository.optional(FIND_BY_MARK_OF_CAR,
                Post.class, Map.of("fName", name)
        );
    }
}
