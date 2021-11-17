import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph {
    private static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    int vertices;
    ArrayList<Edge> allEdges = new ArrayList<>();

    public Graph(int vertices) {
        this.vertices = vertices;
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        allEdges.add(edge); //add to total edges
    }

    public void kruskalMST() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));
        // add all the edges to priority queue, //sort the edges on weights
        pq.addAll(allEdges);

        // create a parent []
        int[] parent = new int[vertices];
        // makeset
        makeSet(parent);
        ArrayList<Edge> mst = new ArrayList<>();
        // process vertices - 1 edges
        int index = 0;
        while (index < vertices - 1) {
            Edge edge = pq.remove();
            // check if adding this edge creates a cycle
            int x_set = find(parent, edge.source);
            int y_set = find(parent, edge.destination);
            if (x_set != y_set) {
                // add it to our final result
                mst.add(edge);
                index++;
                union(parent, x_set, y_set);
            } // otherwise, will create a cycle
        }
        // print MST
        printGraph("Kruskal spanning tree", mst);
        System.out.print("\n");
        printWeight("Kruskal spanning tree", mst);
    }

    public void makeSet(int[] parent) {
        // make set- creating a new element with a parent pointer to itself.
        int bound = vertices;
        for (int i = 0; i < bound; i++) {
            parent[i] = i;
        }
    }

    public int find(int [] parent, int vertex){
        // chain of parent pointers from x upwards through the tree
        // until an element is reached whose parent is itself
        return parent[vertex] != vertex ? find(parent, parent[vertex]) : vertex;
    }

    public void union(int [] parent, int x, int y){
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);
        // make x as parent of y
        parent[y_set_parent] = x_set_parent;
    }

    public void printGraph(String message, ArrayList<Edge> edgeList){
        System.out.println(message + " edges: vertex 1, vertex 2, weight of the edge");
        System.out.print("\n");

        for (Edge edge : edgeList) {
            String s = "edge: " + edge.source +
                    ", " + edge.destination +
                    ", " + edge.weight;
            System.out.println(s);
        }
    }

    public void printWeight(String name, ArrayList<Edge> edgeList) {
        int number = 0;

        for (Edge edge : edgeList) {
            number += edge.weight;
        }

        System.out.println(name + " weight is " + number);
    }
}
