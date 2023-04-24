package ru.job4j.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "year")
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Year(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nYear{" +
                "\nid=" + id +
                ", \nname='" + name + '\'' +
                '}';
    }

}
