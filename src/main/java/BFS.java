import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class BFS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); //num nodes
        int e = in.nextInt(); //num edges

        ArrayList<Integer>[] adj = new ArrayList[n]; //adjaceny list
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<Integer>();

        //fill adjacency list with connections
        for (int i = 0; i < e; i++) {
            int a = in.nextInt(), b = in.nextInt();
            adj[a].add(b);
            adj[b].add(a); //undirected edges go from A to B and B to A
        }

        ArrayDeque<Integer> q = new ArrayDeque<>(); //Queue equivalent for graph traversal
        int[] dist = new int[n]; //distance to each node from start point
        dist[0] = 0; //start point must be 0 distance to itself
        q.add(0); //arbitrary start point of node 0
        while (!q.isEmpty()) {
            int cur = q.poll(); //current node
            for (int x: adj[cur]) {
                q.add(x); //add adjacent node to queue
                dist[x] = dist[cur] + 1; //distance is one plus the distance to the current node
            }
        }
    }
}
