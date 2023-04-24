package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "actuator")
public class Actuator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Actuator(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nActuator{" +
                "\nid=" + id +
                ", \nname='" + name + '\'' +
                '}';
    }
}
