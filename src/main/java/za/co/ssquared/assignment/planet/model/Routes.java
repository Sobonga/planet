package za.co.ssquared.assignment.planet.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "routes")
public class Routes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routes_id")
    private long routes_id;

    @Column(name = "route")
    private int route;

    @Column(name = "planet_origin")
    private String planet_origin;

    @Column(name = "planet_destination")
    private String planet_destination;

    @Column(name = "distance")
    private double distance;

    public Routes(){}

    public Routes(long routes_id,int route,String planet_origin,String planet_destination,double distance) {
        this.routes_id = routes_id;
        this.route = route;
        this.planet_origin = planet_origin;
        this.planet_destination = planet_destination;
        this.distance = distance;
    }

    public long getRoutes_id() {
        return routes_id;
    }

    public void setRoutes_id(long routes_id) {
        this.routes_id = routes_id;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public String getPlanet_origin() {
        return planet_origin;
    }

    public void setPlanet_origin(String planet_origin) {
        this.planet_origin = planet_origin;
    }

    public String getPlanet_destination() {
        return planet_destination;
    }

    public void setPlanet_destination(String planet_destination) {
        this.planet_destination = planet_destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Routes{" +
                "routes_id=" + routes_id +
                ", route=" + route +
                ", planet_origin='" + planet_origin + '\'' +
                ", planet_destination='" + planet_destination + '\'' +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Routes)) return false;
        Routes routes = (Routes) o;
        return getRoutes_id () == routes.getRoutes_id () &&
                getRoute () == routes.getRoute () &&
                getDistance () == routes.getDistance () &&
                Objects.equals ( getPlanet_origin (),routes.getPlanet_origin () ) &&
                Objects.equals ( getPlanet_destination (),routes.getPlanet_destination () );
    }

    @Override
    public int hashCode() {

        return Objects.hash ( getRoutes_id (),getRoute (),getPlanet_origin (),getPlanet_destination (),getDistance () );
    }
}
