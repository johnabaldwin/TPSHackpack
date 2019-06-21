
public class UnionFind
{
    private int count; //The number of different sets of vertices
    private int[] parent; //The parent vertex of each set of vertices
    private int[] size; //The size of each set of vertices

    public UnionFind(int p) {
        count = p; //The count starts out as the number of different vertices because no unions have been performed
        parent = new int[p];
        size = new int[p];
        for (int i = 0; i < p; i++) {
            //Each vertex starts out as its own parent and its size is 1 because it is the only vertex in the set
            parent[i] = i;
            size[i] = 1;
        }
    }

    //This method finds the parent node of a vertex p by traveling up the tree created by joining two sets
    public int find(int p) {
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    //This method joins the two sets containing vertex p and vertex q
    public void union(int p, int q) {
        int rootP = find(p); //finds the root vertex of vertex p
        int rootQ = find(q); //finds the root vertex of vertex q
        if (rootP == rootQ) //if the root nodes of each is the same there is no way to join the set
            return;
        //if they arent the same then join the smaller set to the larger one, this keeps the height of the tree small
        //which keeps find(x) fast and makes this algorithm overall run in almost O(1)
        if (size[rootQ] > size[rootP]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    //two vertices p and q are connected if they share a root node so all you need to do is find the root node of each
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

}
