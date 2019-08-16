package za.co.ssquared.assignment.planet.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "node")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "node_id")
    private long node_id;

    @Column(name = "distance_from_source")
    private int distance_from_source = Integer.MAX_VALUE;

    @Column(name = "visited")
    private boolean visited;

    @Column(name = "edges")
    private ArrayList<Routes> routes = new ArrayList<Routes>(); // now we must create edges

    public int getDistance_from_source() {
        return distance_from_source;
    }
    public void setDistance_from_source(int distance_from_source) {
        this.distance_from_source = distance_from_source;
    }
    public boolean isVisited() {
        return visited;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public ArrayList<Routes> getRoutes() {
        return routes;
    }
    public void setRoutes(ArrayList<Routes> routes) {
        this.routes = routes;
    }

    public long getNode_id() {
        return node_id;
    }

    public void setNode_id(long node_id) {
        this.node_id = node_id;
    }
}
