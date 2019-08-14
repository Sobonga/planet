package za.co.ssquared.assignment.planet.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long planet_id;

    private String PlanetNode;

    private String PlanetName;

    public Planet(long planet_id,String PlanetNode,String PlanetName) {
        this.planet_id = planet_id;
        this.PlanetNode = PlanetNode;
        this.PlanetName = PlanetName;
    }


    public long getPlanet_id() {
        return planet_id;
    }

    public void setPlanet_id(long planet_id) {
        this.planet_id = planet_id;
    }

    public String getPlanetNode() {
        return PlanetNode;
    }

    public void setPlanetNode(String planetNode) {
        PlanetNode = planetNode;
    }

    public String getPlanetName() {
        return PlanetName;
    }

    public void setPlanetName(String planetName) {
        PlanetName = planetName;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "planet_id=" + planet_id +
                ", PlanetNode='" + PlanetNode + '\'' +
                ", PlanetName='" + PlanetName + '\'' +
                '}';
    }
}
