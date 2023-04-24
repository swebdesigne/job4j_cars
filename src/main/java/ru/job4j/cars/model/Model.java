package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Model(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Model{" +
                "\nid=" + id +
                ",\n name='" + name + '\'' +
                '}';
    }

}
