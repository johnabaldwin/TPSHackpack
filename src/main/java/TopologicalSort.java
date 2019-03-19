package hackpack;

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
            int f = in.nextInt();
            int t = in.nextInt();
            g[f].add(t);
            inDeg[t]++;
        }

        ArrayDeque<Integer> dq = new ArrayDeque<>();
        ArrayDeque<Integer> dq2 = new ArrayDeque<>();
        for (int i = 0; i < inDeg.length; i++) {
            if(inDeg[i] == 0) {
                dq.add(i);
            }
        }

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
