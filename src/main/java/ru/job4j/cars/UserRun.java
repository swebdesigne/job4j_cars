package ru.job4j.cars;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.CrudRepository;
import ru.job4j.cars.repository.HibernateUserRepository;

public class UserRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            HibernateUserRepository hibernateUserRepository = new HibernateUserRepository(new CrudRepository(sf));
            var user = new User();
            var user2 = new User();
            user.setLogin("admin");
            user.setPassword("123");
            hibernateUserRepository.create(user);
            System.out.println(user);
            user.setPassword("1234");
            System.out.println(user);
            hibernateUserRepository.update(user);
            user2.setLogin("admin2");
            user2.setPassword("123");
            hibernateUserRepository.create(user2);
            hibernateUserRepository.findAllOrderById().forEach(System.out::println);
            System.out.println("find by id == " + hibernateUserRepository.findById(2));
            hibernateUserRepository.findByLogin("Petrov").ifPresent(u -> System.out.println("find by login == " + u));
            hibernateUserRepository.findByLikeLogin("adm").forEach(us -> System.out.println("find by like adm " + us));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
