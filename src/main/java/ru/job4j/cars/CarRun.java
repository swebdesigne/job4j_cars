package ru.job4j.cars;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.*;

import java.util.Set;

@ThreadSafe
public class CarRun {
    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder()
            .configure().build();
    private static final SessionFactory SF = new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();
    private static final CrudRepository CRUD_REPOSITORY = new CrudRepository(SF);
    private static final HibernateUserRepository HIBERNATE_USER_REPOSITORY = new HibernateUserRepository(CRUD_REPOSITORY);
    private static final HibernateCarRepository HIBERNATE_CAR_REPOSITORY = new HibernateCarRepository(CRUD_REPOSITORY);
    private static final HibernateOwnerRepository HIBERNATE_OWNER_REPOSITORY = new HibernateOwnerRepository(CRUD_REPOSITORY);
    private static final HibernateEngineRepository HIBERNATE_ENGINE_REPOSITORY = new HibernateEngineRepository(CRUD_REPOSITORY);

    public static void main(String[] args) {
        try {
            var user = new User();
            user.setLogin("admin");
            user.setPassword("123");
            HIBERNATE_USER_REPOSITORY.create(user);
            var owner = new Owner();
            owner.setName("Alina");
            owner.setUser(user);
            owner.setUser(HIBERNATE_USER_REPOSITORY.findByLogin("admin").orElse(new User()));
            System.out.println("owner === " + owner);
            HIBERNATE_OWNER_REPOSITORY.add(owner);
            System.out.println(HIBERNATE_USER_REPOSITORY.findById(4));
            var engine = new Engine();
            engine.setName("V2");
            HIBERNATE_ENGINE_REPOSITORY.add(engine);
            HIBERNATE_OWNER_REPOSITORY.findById(1).ifPresent(o -> System.out.println("Owner ===== " + o));
            var car = new Car();
            car.setEngine(HIBERNATE_ENGINE_REPOSITORY.findById(1).orElse(new Engine()));
            owner = HIBERNATE_OWNER_REPOSITORY.findById(1).orElse(new Owner());
            System.out.println(owner);
            car.setOwners(Set.of(owner));
            System.out.println("car === " + car);
            HIBERNATE_CAR_REPOSITORY.add(car);
            HIBERNATE_CAR_REPOSITORY.findAll().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(REGISTRY);
        }
    }
}
