package za.co.ssquared.assignment.planet.model;

import javax.persistence.*;
import java.util.ArrayList;
// now we must create graph object and implement dijkstra algorithm

@Entity
@Table(name = "graph")
public class Graph {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graph_id")
    private long graph_id;

    @Column(name = "nodes")
    private Node[] nodes;

    @Column(name = "no_of_nodes")
    private int no_of_nodes;

    @Column(name = "edges")
    private Edge[] edges;

    @Column(name = "no_of_edges")
    private int no_of_edges;


    public Graph(Edge[] edges) {
        this.edges = edges;

        // create all nodes ready to be updated with the edges

        this.no_of_nodes = calculateNoOfNodes(edges);
        this.nodes = new Node[this.no_of_nodes];
        for (int n = 0; n < this.no_of_nodes; n++) {
            this.nodes[n] = new Node();
        }
        // add all the edges to the nodes, each edge added to two nodes (to and from)
        this.no_of_edges = edges.length;
        for (int edgeToAdd = 0; edgeToAdd < this.no_of_edges; edgeToAdd++) {
            this.nodes[edges[edgeToAdd].getTo_node_index ()].getEdges().add(edges[edgeToAdd]);
            this.nodes[edges[edgeToAdd].getTo_node_index ()].getEdges().add(edges[edgeToAdd]);
        }
    }
    private int calculateNoOfNodes(Edge[] edges) {
        int noOfNodes = 0;
        for (Edge e : edges) {
            if (e.getTo_node_index () > noOfNodes)
                noOfNodes = e.getTo_node_index ();
            if (e.getTo_node_index () > noOfNodes)
                noOfNodes = e.getTo_node_index ();
        }
        noOfNodes++;
        return noOfNodes;
    }
    // next video to implement the Dijkstra algorithm !!!
    public void calculateShortestDistances() {
        // node 0 as source
        this.nodes[0].setDistance_from_source(0);
        int nextNode = 0;
        // visit every node
        for (int i = 0; i < this.nodes.length; i++) {
            // loop around the edges of current node
            ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();
            for (int joinedEdge = 0; joinedEdge < currentNodeEdges.size(); joinedEdge++) {
                int neighbourIndex = currentNodeEdges.get(joinedEdge).getNeighbourIndex(nextNode);
                // only if not visited
                if (!this.nodes[neighbourIndex].isVisited()) {
                    int tentative = this.nodes[nextNode].getDistance_from_source () + currentNodeEdges.get(joinedEdge).getLength();
                    if (tentative < nodes[neighbourIndex].getDistance_from_source ()) {
                        nodes[neighbourIndex].setDistance_from_source (tentative);
                    }
                }
            }
            // all neighbours checked so node visited
            nodes[nextNode].setVisited(true);
            // next node must be with shortest distance
            nextNode = getNodeShortestDistanced();
        }
    }
    // now we're going to implement this method in next part !
    private int getNodeShortestDistanced() {
        int storedNodeIndex = 0;
        int storedDist = Integer.MAX_VALUE;
        for (int i = 0; i < this.nodes.length; i++) {
            int currentDist = this.nodes[i].getDistance_from_source ();
            if (!this.nodes[i].isVisited() && currentDist < storedDist) {
                storedDist = currentDist;
                storedNodeIndex = i;
            }
        }
        return storedNodeIndex;
    }
    // display result
    public void printResult() {
        String output = "Number of nodes = " + this.no_of_nodes;
        output += "\nNumber of edges = " + this.no_of_edges;
        for (int i = 0; i < this.nodes.length; i++) {
            output += ("\nThe shortest distance from node 0 to node " + i + " is " + nodes[i].getDistance_from_source ());
        }
        System.out.println(output);
    }
    public Node[] getNodes() {
        return nodes;
    }
    public int getNo_of_nodes() {
        return no_of_nodes;
    }
    public Edge[] getEdges() {
        return edges;
    }
    public int getNo_of_edges() {
        return no_of_edges;
    }

    public long getGraph_id() {
        return graph_id;
    }
}
