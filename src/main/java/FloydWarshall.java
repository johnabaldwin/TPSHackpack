import java.util.Scanner;


/*
This is a graph theory algorithm that is used to solve the All Pairs Shortest path problem. This means that given
any two nodes in a graph, after running this algorithm you will be able to query the shortest path in O(1) time. However,
this algorithm runs in O(n^3).
 */

public class FloydWarshall {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int nodes = in.nextInt();
        int edges = in.nextInt();
        int queries = in.nextInt(); // Number of paths asked for

        int[][] adjMatrix = new int[nodes][nodes];
        /*
            Unlike most other algorithms, Floyd's (as it is commonly called) requires the use of an adjacency matrix
            rather than an adjacency list because of the fact that you must keep track of ALL nodes and ALL paths.
         */
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                if (j == i)
                    adjMatrix[i][j] = 0; //value of a node connected to itself
                else
                    adjMatrix[i][j] = Integer.MAX_VALUE; //node with no connections
            }
        }

        for (int i = 0; i < edges; i++) {
            int e1 = in.nextInt();
            int e2 = in.nextInt();
            adjMatrix[e1][e2] = in.nextInt(); //add in directed edges with weights
        }

        // Floyd Warshall's
        for (int k = 0; k < adjMatrix.length; k++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                for (int i = 0; i < adjMatrix.length; i++) {
                    if (adjMatrix[i][k] == Integer.MAX_VALUE || adjMatrix[k][j] == Integer.MAX_VALUE) {
                        continue; //prevent using nodes with no connections
                    }
                    if (adjMatrix[i][k] + adjMatrix[k][j] < adjMatrix[i][j]) {
                        //if the new path is shorter than the original one use the new path
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                    }
                }
            }
        }

        //process queries
        for (int i = 0; i < queries; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            System.out.println(adjMatrix[x][y]);
        }
        System.out.println();

//	    Negative Cycle Detection with Floyd Warshalls
//	    for (int i = 0; i < nodes; i++) {
//		for (int j = 0; j < nodes; j++) {
//		    for (int k = 0; adjMatrix[i][j] != Integer.MIN_VALUE && k < nodes; k++) {
//			if (adjMatrix[k][k] < 0 && adjMatrix[i][k] != Integer.MAX_VALUE && adjMatrix[k][j] != Integer.MAX_VALUE)
//			    return true;
//		    }
//		}
//	    }
    }
}

