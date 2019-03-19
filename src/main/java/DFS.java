import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class DFS {


    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numNodes = in.nextInt();
        int numEdges = in.nextInt();

        adj = new ArrayList[numNodes]; //list representing the nodes adjacent to each node
        for (int i = 0; i < numNodes; i++) {
            adj[i] = new ArrayList<>(); //you must instantiate each new index in the array of lists
        }

        for (int i = 0; i < numEdges; i++) {
            int fromNode = in.nextInt();
            int toNode = in.nextInt();
            adj[fromNode].add(toNode);
            adj[toNode].add(fromNode); //add edge to and from each node
        }

        visited = new boolean[numNodes]; //keeps track of if each node has been visited

        Stack<Integer> stack = new Stack<>(); //a Stack structure simulates the same thing a stack does in real life
        // this allows for a first in last out visiting order of nodes

        stack.push(0); //use 0 as an arrbitrary starting node
        while(!stack.isEmpty()) { //as long as the Stack has nodes in it, continue running the dfs
            int currentNode = stack.pop();
            visited[currentNode] = true;
            for(int nextNode : adj[currentNode]) {
                if(!visited[nextNode])
                    stack.push(nextNode); //add the nextNode to the stack to be visited
            }
        }

        recursiveDFS(0); // a different dfs implementation using the recursive stack rather than
        //a stack object, these tow different methods of doing a DFS perform the same thing


    }

    static void recursiveDFS(int currentNode) {
        visited[currentNode] = true;
        for(int nextNode : adj[currentNode]) {
            if(!visited[nextNode])
                recursiveDFS(nextNode);
        }
    }
}
