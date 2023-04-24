package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transmission")
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Transmission(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nTransmission{" +
                "\nid=" + id +
                ",\n name='" + name + '\'' +
                '}';
    }
}
