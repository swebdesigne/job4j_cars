package ru.job4j.cars;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.repository.CrudRepository;
import ru.job4j.cars.repository.HibernatePostRepository;

public class PostRun {
    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder()
            .configure().build();
    private static final SessionFactory SF = new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();
    private static final CrudRepository CRUD_REPOSITORY = new CrudRepository(SF);
    private static final HibernatePostRepository HIBERNATE_POST_REPOSITORY = new HibernatePostRepository(CRUD_REPOSITORY);

    public static void main(String[] args) {
        try {
            if (HIBERNATE_POST_REPOSITORY.findForLastDay().isEmpty()) {
                System.out.println("Find by last day unsuccessful");
            }
            if (HIBERNATE_POST_REPOSITORY.findWithPhoto().isEmpty()) {
                System.out.println("Find by photo unsuccessful");
            }
            if (HIBERNATE_POST_REPOSITORY.findByMark("audi").isEmpty()) {
                System.out.println("Find by mark unsuccessful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(REGISTRY);
        }
    }
}
