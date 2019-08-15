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
    private int from_node_index;

    @Column(name = "to_node_index")
    private int to_node_index;

    @Column(name = "length")
    private int length;

    public Edge(int fromNodeIndex, int toNodeIndex, int length, long edge_id) {
        this.from_node_index = fromNodeIndex;
        this.to_node_index = toNodeIndex;
        this.length = length;
        this.edge_id = edge_id;
    }
    public int getFrom_node_index() {
        return from_node_index;
    }
    public int getTo_node_index() {
        return to_node_index;
    }
    public int getLength() {
        return length;
    }

    public long getEdge_id() {
        return edge_id;
    }

    // determines the neighbouring node of a supplied node, based on the two nodes connected by this edge
    public int getNeighbourIndex(int nodeIndex) {
        if (this.from_node_index == nodeIndex) {
            return this.to_node_index;
        } else {
            return this.from_node_index;
        }
    }


}
