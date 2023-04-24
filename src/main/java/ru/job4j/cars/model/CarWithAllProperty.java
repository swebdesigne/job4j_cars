package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class CarWithAllProperty {
    private String name;

    private Map<String, CarPropertyContainer> properties = new HashMap<>();

    public CarWithAllProperty(String carName) {
        this.name = carName;
    }

    public Map<String, CarPropertyContainer> getProperties() {
        return properties;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProperties(String model, CarPropertyContainer properties) {
        if (this.properties.containsKey(model)) {
            this.properties.put(model, properties);
        }
        this.properties.putIfAbsent(model, properties);
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CarWithProperty{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                '}';
    }
}
