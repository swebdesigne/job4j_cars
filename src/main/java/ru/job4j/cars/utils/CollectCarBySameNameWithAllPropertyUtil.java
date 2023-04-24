package ru.job4j.cars.utils;

import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.CarPropertyContainer;
import ru.job4j.cars.model.CarWithAllProperty;

import java.util.*;

public class CollectCarBySameNameWithAllPropertyUtil {
    private CarWithAllProperty carWithAllProperty;
    private CarPropertyContainer carPropertyContainer;
    private final Set<CarWithAllProperty> setCar = new LinkedHashSet<>();

    public void setProperty(List<Car> cars) {
        for (var car : cars) {
            if (Objects.isNull(carWithAllProperty) || !carWithAllProperty.getName().equals(car.getName())) {
                carWithAllProperty = new CarWithAllProperty(car.getName());
            }
            if (!carWithAllProperty.getProperties().containsKey(car.getModel().getName())) {
                carPropertyContainer = new CarPropertyContainer();
            }
            assert carPropertyContainer != null;
            carPropertyContainer.setYears(car.getYear());
            carPropertyContainer.setOwners(car.getOwners());
            carPropertyContainer.setEngines(car.getEngine());
            carPropertyContainer.setActuators(car.getActuator());
            carPropertyContainer.setTransmissions(car.getTransmission());
            carWithAllProperty.setProperties(car.getModel().getName(), carPropertyContainer);
            setCar.add(carWithAllProperty);
        }
    }

    public Set<CarWithAllProperty> getProperty() {
        return new LinkedHashSet<>(setCar);
    }
}
