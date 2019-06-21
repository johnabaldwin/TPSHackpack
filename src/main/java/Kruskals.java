import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
Kruskals is an alternative to Prims for finding a minimum spanning tree. Because I have already detailed Prims, I will
leave the research of this algorithm up to you.
 */
public class Kruskals {
    public static class Edge implements Comparable<Edge> {
        public int from, to, cost;

        public Edge(int a, int b, int c){
            from = a;
            to = b;
            cost = c;
        }

        /**
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Edge o){
            return cost-o.cost;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numNodes = in.nextInt();
        int numEdges = in.nextInt();
        ArrayList<Edge> edges = new ArrayList<Edge>();

        for(int i = 0; i<numEdges; i++){
            int from = in.nextInt();
            int to = in.nextInt();
            int cost = in.nextInt();
            edges.add(new Edge(from,to,cost));
        }
        Collections.sort(edges);

        //initializes adjList, creates ArrayList at each index, & add current node
        int[] component = new int[numNodes];
        ArrayList<Integer>[] lists = new ArrayList[numNodes];
        int[] size = new int[numNodes];

        for(int i = 0; i < numNodes; i++){
            lists[i] = new ArrayList<Integer>();
            lists[i].add(i);
            size[i] = 1;
            component[i] = i;
        }

        int ans = 0; //cost of MST
        for(int i = 0; i < edges.size(); i++){
            int from = edges.get(i).from;
            int to = edges.get(i).to;
            if(component[from]==component[to]){
                continue;
            }
            ans += edges.get(i).cost;
            if(size[component[from]]>size[component[to]]){
                int temp = from;
                from = to;
                to = temp;
            }
            int componentFrom = component[from];
            int componentTo = component[to];
            for(int j = 0; j<lists[componentFrom].size(); j++){
                lists[componentTo].add(lists[componentFrom].get(j));
                component[lists[componentFrom].get(j)] = componentTo;
            }
            size[componentTo] += size[componentFrom];
            size[componentFrom] = 0;
            lists[componentFrom].clear();
        }
        System.out.println(ans);
    }
}