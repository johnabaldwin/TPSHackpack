//Prim's Minimum Spanning Tree Algorithm

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prims {

    static class Edge {
        int from, to, weight;
        public Edge(int f, int t, int w) {
            from = f;
            to = t;
            weight = w;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); //Number of nodes
        int e = in.nextInt(); //Number of edges
        int s = in.nextInt(); //Start node

        //Adjancency list for each node that represents the nodes connected to each node
        ArrayList<Edge>[] adj = new ArrayList[n];
        for (int i = 0; i < e; i++) {
            int from = in.nextInt(); //the node an edge starts from
            int to = in.nextInt(); //the node an edge goes to
            int weight = in.nextInt(); //the cost of an edge
            //You must create an edge to and from each node in an undirected graph
            adj[from].add(new Edge(from, to, weight));
            adj[from].add(new Edge(to, from, weight));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        for(int i = 0; i<adj[s].size(); i++){ //Add all edges from the start node to the PQ
            pq.add(adj[0].get(i));
        }
        boolean[] visited = new boolean[n]; //Has each node been seen?
        visited[s] = true;
        int ans = 0;
        while(pq.size()>0){ //Always take the smallest untraveled edge available to a new node
            Edge now = pq.poll(); //the current edge being considered
            if(visited[now.to]){
                continue;
            }
            visited[now.to] = true;
            ans += now.weight;
            for(int i =0; i<adj[now.to].size(); i++){
                pq.add(adj[now.to].get(i));
            }
        }
        System.out.println(ans);
    }
}
