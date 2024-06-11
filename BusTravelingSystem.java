//problem statement: Bus Traveling System: Nina wants to travel the city, but is unaware about the bus routes and the cost. The goal is to solve this problem by creating a bus traveling system to find the shortest path and least traveling distance to reach from source to destination.

// This class represents an edge in the graph. Each edge has a destination node and a weight.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Edge {
    int destination;
    int weight;

    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class BusTravelingSystem {

    private static final int INFINITY = Integer.MAX_VALUE;

    public static void main(String[] args) {
        List<List<Edge>> graph = new ArrayList<>();
        int numVertices = 5;

        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 4, 20);
        addEdge(graph, 1, 2, 5);
        addEdge(graph, 1, 4, 12);
        addEdge(graph, 2, 3, 20);
        addEdge(graph, 3, 4, 1);

        int source = 0;
        int destination = 4;

        int[] distances = dijkstra(graph, source);

        System.out.println("Shortest distance: " + distances[destination]);
    }

    private static void addEdge(List<List<Edge>> graph, int src, int dest, int weight) {
        graph.get(src).add(new Edge(dest, weight));
    }

    private static int[] dijkstra(List<List<Edge>> graph, int source) {
        int numVertices = graph.size();
        int[] distances = new int[numVertices];
        boolean[] visited = new boolean[numVertices];
        
        //initially filling all distances with infinity and source distance with 0
        Arrays.fill(distances, INFINITY);
        distances[source] = 0;

        // To iteratively find the minimum distance vertex
        for (int i = 0; i < numVertices; i++) {
            int minDistance = INFINITY;
            int minVertex = -1;

        // iterates over all vertices to find the unvisited vertex with the smallest distance:
            for (int j = 0; j < numVertices; j++) {
                if (!visited[j] && distances[j] < minDistance) {
                    minDistance = distances[j];
                    minVertex = j;
                }
            }

        // if there are no more unvisited vertices with a finite distance
            if (minVertex == -1) break;

            visited[minVertex] = true;

        //this algorithm updates the distances to the neighboring vertices:
            for (Edge edge : graph.get(minVertex)) {
                int destination = edge.destination;
                int weight = edge.weight;
                int newDistance = distances[minVertex] + weight;

                if (newDistance < distances[destination]) {
                    distances[destination] = newDistance;
                }
            }
        }

        return distances;
    }
}
