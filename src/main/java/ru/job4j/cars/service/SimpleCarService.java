package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.HibernateCarRepository;
import ru.job4j.cars.utils.CollectCarBySameNameWithAllPropertyUtil;

import java.util.*;

@Service
@AllArgsConstructor
@ThreadSafe
public class SimpleCarService implements CarService {
    private final HibernateCarRepository hibernateCarRepository;

    @Override
    public List<Car> findAll() {
        return hibernateCarRepository.findAll();
    }

    @Override
    public Optional<Car> findByProperty(Car car) {
        return hibernateCarRepository.findByProperty(car);
    }

    public Set<CarWithAllProperty> getCarDividedByProperty(CollectCarBySameNameWithAllPropertyUtil collectCarBySameNameWithAllPropertyUtil) {
        collectCarBySameNameWithAllPropertyUtil.setProperty(hibernateCarRepository.findAll());
        return collectCarBySameNameWithAllPropertyUtil.getProperty();
    }

    public Set<CarWithAllProperty> ajaxFindProperty(String query, CollectCarBySameNameWithAllPropertyUtil collectCarBySameNameWithAllPropertyUtil) {
        var properties = query.split("&");
        Map<String, String> props = new HashMap<>();
        for (var property : properties) {
            var prop = property.split("=");
            if (prop.length < 2) {
                continue;
            }
            props.put(prop[0], prop[1]);
        }
        collectCarBySameNameWithAllPropertyUtil.setProperty(hibernateCarRepository.ajaxFindProperty(createCar(props)));
        return collectCarBySameNameWithAllPropertyUtil.getProperty();
    }

    private Car createCar(Map<String, String> props) {
        Model model = new Model(props.get("model.name"));
        Year year = new Year(props.get("year.name"));
        Engine engine = new Engine(props.get("engine.name"));
        Actuator actuator = new Actuator(props.get("actuator.name"));
        Transmission transmission = new Transmission(props.get("transmission.name"));
        Car car = new Car();
        car.setName(props.get("name"));
        car.setYear(year);
        car.setEngine(engine);
        car.setActuator(actuator);
        car.setModel(model);
        car.setTransmission(transmission);
        return car;
    }
}
