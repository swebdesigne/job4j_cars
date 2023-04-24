package ru.job4j.cars.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.job4j.cars.model.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class CarPropertyContainer {
    private Set<Engine> engines = new LinkedHashSet<>();
    private Set<Year> years = new LinkedHashSet<>();
    private Set<Transmission> transmissions = new LinkedHashSet<>();
    private Set<Actuator> actuators = new LinkedHashSet<>();
    private Set<Owner> owners = new LinkedHashSet<>();

    public Set<Engine> getEngines() {
        return engines;
    }

    public void setEngines(Engine engine) {
        this.engines.add(engine);
    }

    public Set<Year> getYears() {
        return years;
    }

    public void setYears(Year year) {
        this.years.add(year);
    }

    public Set<Transmission> getTransmissions() {
        return transmissions;
    }

    public void setTransmissions(Transmission transmission) {
        this.transmissions.add(transmission);
    }

    public Set<Actuator> getActuators() {
        return actuators;
    }

    public void setActuators(Actuator actuator) {
        this.actuators.add(actuator);
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "CarPropertyContainer{" +
                "\nengines=" + engines +
                ",\n years=" + years +
                ", \ntransmissions=" + transmissions +
                ", \nactuators=" + actuators +
                ", \nowners=" + owners +
                '}';
    }
}
