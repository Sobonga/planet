package za.co.ssquared.assignment.planet.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planet_id")
    private long planet_id;

    @Column(name = "planet_node")
    private String planet_node;

    @Column(name = "planet_name")
    private String planet_name;

    public Planet(){}

    public Planet(long planet_id,String planet_node,String planet_name) {
        this.planet_id = planet_id;
        this.planet_node = planet_node;
        this.planet_name = planet_name;
    }

    public long getPlanet_id() {
        return planet_id;
    }

    public void setPlanet_id(long planet_id) {
        this.planet_id = planet_id;
    }

    public String getPlanet_node() {
        return planet_node;
    }

    public void setPlanet_node(String planet_node) {
        this.planet_node = planet_node;
    }

    public String getPlanet_name() {
        return planet_name;
    }

    public void setPlanet_name(String planet_name) {
        this.planet_name = planet_name;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "planet_id=" + planet_id +
                ", planet_node='" + planet_node + '\'' +
                ", planet_name='" + planet_name + '\'' +
                '}';
    }
}
