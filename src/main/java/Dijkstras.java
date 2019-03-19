import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstras {

    static class Edge implements Comparable<Edge> {
        int fromNode, toNode, edgeWeight;

        public Edge(int f, int t, int w) {
            fromNode = f;
            toNode = t;
            edgeWeight = w;
        }

        @Override
        public int compareTo(Edge o) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numNodes = in.nextInt(); //the number of nodes in the graph
        int numEdges = in.nextInt(); //the number of edges in the graph

        //Adjancency list for each node that represents the nodes connected to each node
        ArrayList<Edge>[] AdjacencyList = new ArrayList[numNodes];

        //instantiation of the Adjacency list
        for (int i = 0; i < numNodes; i++)
            AdjacencyList[i] = new ArrayList<Edge>();

        for (int i = 0; i < numEdges; i++) {
            int fromNode = in.nextInt();
            int toNode = in.nextInt();
            int edgeWeight = in.nextInt();
            AdjacencyList[fromNode].add(new Edge(fromNode, toNode, edgeWeight));

            //In Dijkstra's algorithm, the graph can be either a simple graph (undirected),
            //or a DAG (directed acyclic [no cycles] graph). Add this line if the graph is the later.
            AdjacencyList[toNode].add(new Edge(toNode, fromNode, edgeWeight));
        }

        /**
         *
         * Dijkstra's works by always taking the lowest available edge.
         * The PriorityQueue of {@code Edge} sorts by {@code Edge.edgeWeight}
         * so the smallest available edge weight is always taken first.
         */
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        for (int i = 0; i < AdjacencyList[0].size(); i++) {
            pq.add(AdjacencyList[0].get(i));
        }

        /*
         * The status of a node being visited of not is only necesarry
         * for knowing if a node is NOT visited unlike in BFS where it serves
         * the purpose of knowing if a node has been visited or has not been visited.
         */
        boolean[] visitedNodes = new boolean[numNodes];

        /*
         * The distance array is used to keep track of the minimum distance
         * required to travel to each node. The value at each node changes if
         * a new shortest path to that node is found in the program execution.
         */
        int[] distanceTo = new int[numNodes];
        /*
         * Use Integer.MAX_VALUE to so that the first path to any node always works.
         * (i.e. if you filled with 10 and there was a path length 11 that starting path
         * wouldn't be counted and the shortest path would incorrectly be output as 10)
         */
        Arrays.fill(distanceTo, Integer.MAX_VALUE);

        // Using 0 as arbitrary starting location, when algorithm begins
        // the first node is visited and distance to it is 0.
        visitedNodes[0] = true;
        distanceTo[0] = 0;

        //While there are still edges left to travel
        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();

            //the node this edge connects to is marked as visited
            visitedNodes[currentEdge.toNode] = true;


            //This bit here make the code a little bit cleaner
            boolean edited = false; //Is the distance to currentEdge.toNode edited

            // If the distance to currentEdge.toNode is greater than the distance through some alternate path,
            // take the alternate path, change teh distance in distanceTo array and make edited true
            if (distanceTo[currentEdge.toNode] > distanceTo[currentEdge.fromNode] + currentEdge.edgeWeight) {
                distanceTo[currentEdge.toNode] = distanceTo[currentEdge.fromNode] + currentEdge.edgeWeight;
                edited = true;
            }

            //for all the edges connected to currentEdge.toNode
            for (int i = 0; i < AdjacencyList[currentEdge.toNode].size(); i++) {
                //if that node is unvisited or the distance to currentEdge.toNode has been decreased,
                //add that node to the priority queue
                if (!visitedNodes[AdjacencyList[currentEdge.toNode].get(i).toNode] || edited)
                    pq.add(AdjacencyList[currentEdge.toNode].get(i));
            }
        }

        //Print out the distance to each node
        for (int i = 0; i < distanceTo.length; i++) {
            System.out.print(distanceTo[i] + " ");
        }
    }
}
