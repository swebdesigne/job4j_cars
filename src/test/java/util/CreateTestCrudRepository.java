package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.repository.CrudRepository;

public final class CreateTestCrudRepository {
    private CreateTestCrudRepository() {
    }

    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder()
            .configure().build();
    private static final SessionFactory BUILD_SESSION_FACTORY =
            new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();

    public static CrudRepository getRepository() {
        return new CrudRepository(BUILD_SESSION_FACTORY);
    }
}
