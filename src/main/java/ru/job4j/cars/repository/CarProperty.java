package ru.job4j.cars.repository;

import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CarProperty {
    Map<String, Set<String>> getPropertyContainer();

    void addProperty(String propertyName, String value);

    void updateProperty(String propertyName, String value);

    void deleteProperty(String propertyName);

    Set<String> getProperty(String propertyName);
}
