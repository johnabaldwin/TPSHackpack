import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;


//This algorithm is used to find cycles in directed graphs and the order in which you can take elements of a directed graph.
public class TopologicalSort {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numNodes = in.nextInt(); //number of nodes in the graph
        ArrayList<Integer>[] g = new ArrayList[numNodes]; //create the adjacency list
        for (int i = 0; i < numNodes; i++) {
            g[i] = new ArrayList<Integer>();
            //when creating an adjacency list every ArrayList in the array must be instantiated individually
        }

        //The in degree (inDeg) array keeps track of how many edges are going into each node
        int[] inDeg = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            int f = in.nextInt(); //the vertex this edge is coming from
            int t = in.nextInt(); //the vertex this edge is going to
            g[f].add(t); //add vertex T to F's adjacency list
            inDeg[t]++; //increment the in degree of the To edge
        }

        ArrayDeque<Integer> dq = new ArrayDeque<>(); //The queue of unvisited vertices still in the graph

        //The queue of visited vertices in the order they were taken out of the graph.
        ArrayDeque<Integer> dq2 = new ArrayDeque<>();

        //Add all vertices with In Degree of zero because all of these vertices can be taken
        for (int i = 0; i < inDeg.length; i++) {
            if(inDeg[i] == 0) {
                dq.add(i);
            }
        }

        //This part is basically a BFS but every time you remove an edge you decrement the in degree of the To vertex
        //Once a vertex has an in degree of zero you can remove that edge from the graph. Once you have no more in degrees
        // of zero you have either taken the entire graph or you have found a cycle in the graph.
        while (!dq.isEmpty()) {
            int n = dq.poll();
            dq2.add(n);
            for (int i = 0; i < g[n].size(); i++) {
                inDeg[g[n].get(i)]--;
                if(inDeg[g[n].get(i)] == 0) {
                    dq.add(g[n].get(i));
                }
            }
        }
        System.out.println(dq2);
        /*
            To determine if there is a cycle you can simply check the size of the first queue. If it is greater than 0,
            then there is a cycle.
         */
    }
}

/*
6
5 2
5 0
4 0
4 1
2 3
3 1
 */
