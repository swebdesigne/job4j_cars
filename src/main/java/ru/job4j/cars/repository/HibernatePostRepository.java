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
    private final static String FIND_ALL = "FROM Post f " +
            "JOIN FETCH f.car.year " +
            "JOIN FETCH f.car.model " +
            "JOIN FETCH f.car.engine " +
            "JOIN FETCH f.car.transmission " +
            "JOIN FETCH f.car.actuator";
    private final static String FIND_BY_LAST_DAY = "FROM Post WHERE created >= :fCreated";
    private final static String FIND_WITH_PHOTO = "FROM Post WHERE photo != null";
    private final static String FIND_BY_MARK_OF_CAR = "FROM Post c JOIN FETCH c.car WHERE c.car.name = :fName";
    private final static String FIND_BY_ID_OF_CAR = "FROM Post c " +
            "JOIN FETCH c.car " +
            "JOIN FETCH c.car.model " +
            "JOIN FETCH c.car.actuator " +
            "JOIN FETCH c.car.owners " +
            "JOIN FETCH c.car.transmission " +
            "JOIN FETCH c.car.year " +
            "WHERE c.id = :fID";

    @Override
    public Optional<Post> add(Post post) {
        try {
            crudRepository.run(session -> session.persist(post));
            return Optional.of(post);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Post> findAll() {
        return crudRepository.query(FIND_ALL, Post.class);
    }

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

    public Optional<Post> findById(int id) {
        return crudRepository.optional(FIND_BY_ID_OF_CAR,
                Post.class, Map.of("fID", id)
        );
    }
}
