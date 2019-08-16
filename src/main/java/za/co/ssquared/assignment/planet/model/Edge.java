package za.co.ssquared.assignment.planet.model;

import javax.persistence.*;

@Entity
@Table(name = "edge")
public class Edge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edge_id")
    private long edge_id;

    @Column(name = "from_node_index")
    private int planet_origin;

    @Column(name = "to_node_index")
    private int planet_destination;

    @Column(name = "length")
    private int distance;

    @Column(name = "length")
    private int route;

    public Edge(int planet_origin, int planet_destination, int distance, long edge_id, int route) {
        this.planet_origin = planet_origin;
        this.planet_destination = planet_destination;
        this.distance = distance;
        this.edge_id = edge_id;
        this.route = route;
    }
    public int getPlanet_origin() {
        return planet_origin;
    }
    public int getPlanet_destination() {
        return planet_destination;
    }
    public int getDistance() {
        return distance;
    }

    public long getEdge_id() {
        return edge_id;
    }

    public int GetRoute(){
        return route;
    }

    // determines the neighbouring node of a supplied node, based on the two nodes connected by this edge
    public int getNeighbourIndex(int nodeIndex) {
        if (this.planet_origin == nodeIndex) {
            return this.planet_destination;
        } else {
            return this.planet_origin;
        }
    }


}
