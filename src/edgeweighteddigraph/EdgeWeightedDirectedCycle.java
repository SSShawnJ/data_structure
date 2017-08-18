package edgeweighteddigraph;

import stack.Stack;

public class EdgeWeightedDirectedCycle {
	  private boolean[] marked;             // marked[v] = has vertex v been marked?
	    private DirectedEdge[] edgeTo;        // edgeTo[v] = previous edge on path to v
	    private boolean[] onStack;            // onStack[v] = is vertex on the stack?
	    private Stack<DirectedEdge> cycle;    // directed cycle (or null if no such cycle)

	    /**
	     * Determines whether the edge-weighted digraph <tt>G</tt> has a directed cycle and,
	     * if so, finds such a cycle.
	     * @param G the edge-weighted digraph
	     */
	    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
	        marked  = new boolean[G.V()];
	        onStack = new boolean[G.V()];
	        edgeTo  = new DirectedEdge[G.V()];
	        for (int v = 0; v < G.V(); v++)
	            if (!marked[v] && cycle==null) dfs(G, v);
	        // check that digraph has a cycle
	        
	        
	    }

	    
	    // check that algorithm computes either the topological order or finds a directed cycle
	    private void dfs(EdgeWeightedDigraph G, int v) {
	        onStack[v] = true;
	        marked[v] = true;
	        for (DirectedEdge e : G.adj(v)) {
	            int w = e.to();

	            // short circuit if directed cycle found
	            if (cycle != null) return;

	            //found new vertex, so recur
	            else if (!marked[w]) {
	                edgeTo[w] = e;
	                dfs(G, w);
	            }

	            // trace back directed cycle
	            else if (onStack[w]) {
	                cycle = new Stack<DirectedEdge>();
	                while (e.from() != w) {
	                    cycle.push(e);
	                    e = edgeTo[e.from()];
	                }
	                cycle.push(e);
	                return;
	            }
	        }

	        onStack[v] = false;
	    }

	    /**
	     * Does the edge-weighted digraph have a directed cycle?
	     * @return <tt>true</tt> if the edge-weighted digraph has a directed cycle,
	     * <tt>false</tt> otherwise
	     */
	    public boolean hasCycle() {
	        return cycle != null;
	    }

	    /**
	     * Returns a directed cycle if the edge-weighted digraph has a directed cycle,
	     * and <tt>null</tt> otherwise.
	     * @return a directed cycle (as an iterable) if the edge-weighted digraph
	     *    has a directed cycle, and <tt>null</tt> otherwise
	     */
	    public Iterable<DirectedEdge> cycle() {
	        return cycle;
	    }

}
