import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BFS01 {

    static final int[] dy = {1,0,-1,0}; //represents the changes in distances up and down
    static final int[] dx = {0,1,0,-1}; //represents the changes is distances left and right
    static int n; //size of grid
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = in.nextInt(); //size of grid

        /*
        While matrix is not used in the code given here, it will often need to contain important information
        about which points in the graph can and cannot be visited or should be visited later. For example maybe any
        coordinate i,j is marked with a . or a # and you are only allowed to traverse across points marked with a .
        and #'s represent walls you cannot go through. However in the case of the 01 BFS it could also represent an
        obstruction in the path that should be avoid as well as possible (a pot hole maybe?). The 01 BFS will then take
        the path that goes through the fewest number of these #'s.
         */
        String[][] matrix = new String[n][n]; //visual representation of graph
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], -1);

        //fill adjacency list with connections
        for (int i = 0; i < n; i++)
            matrix[i] = in.nextLine().split("");

        PriorityQueue<Integer> q = new PriorityQueue<>(); //Queue equivalent for graph traversal
        q.add(0); //arbitrary x coordinate start point
        q.add(0); //arbitrary y coordinate start point
        dist[0][0] = 0;
        while (!q.isEmpty()) {
            int curx = q.remove(); //current x coordinate
            int cury = q.remove(); //current y coordinate
            for (int i = 0; i < 4; i++) {
                int nx = curx + dx[i]; //new x coordinate that causes a minimum increase in the total resistance or distance
                int ny = curx + dy[i]; //new y coordinate that causes a minimum increase in the total resistance or distance
                if (inBounds(nx, ny) && dist[nx][ny] == -1) {
                    q.add(nx);
                    q.add(ny);
                }
            }
        }
    }

    private static boolean inBounds(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
