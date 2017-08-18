package graph;

public class CC {
	private boolean[] marked;   // marked[v] = has vertex v been marked?
    private int[] id;           // id[v] = id of connected component containing v
    private int[] size;         // size[id] = number of vertices in given component
    private int count;          // number of connected components

    /**
     * Computes the connected components of the undirected graph <tt>G</tt>.
     *
     * @param G the undirected graph
     */
    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    // depth-first search
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * Returns the component id of the connected component containing vertex <tt>v</tt>.
     *
     * @param  v the vertex
     * @return the component id of the connected component containing vertex <tt>v</tt>
     */
    public int id(int v) {
        return id[v];
    }

    /**
     * Returns the number of vertices in the connected component containing vertex <tt>v</tt>.
     *
     * @param  v the vertex
     * @return the number of vertices in the connected component containing vertex <tt>v</tt>
     */
    public int size(int v) {
        return size[id[v]];
    }

    /**
     * Returns the number of connected components in the graph <tt>G</tt>.
     *
     * @return the number of connected components in the graph <tt>G</tt>
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices <tt>v</tt> and <tt>w</tt> are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return <tt>true</tt> if vertices <tt>v</tt> and <tt>w</tt> are in the same
     *         connected component; <tt>false</tt> otherwise
     */
    public boolean connected(int v, int w) {
        return id(v) == id(w);
    }

    
   

}
