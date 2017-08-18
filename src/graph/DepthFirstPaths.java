package graph;

import java.util.Iterator;

import stack.Stack;

public class DepthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private int s;

	// take in Graph object and start vertex
	public DepthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);

	}

	private void dfs(Graph G, int s) {
		marked = new boolean[G.V()];

		// to be able to iterate over each adjacency list, keeping track of
		// which
		// vertex in each adjacency list needs to be explored next
		Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
		for (int v = 0; v < G.V(); v++)
			adj[v] = G.adj(v).iterator();

		// depth-first search using an explicit stack
		Stack<Integer> stack = new Stack<Integer>();
		marked[s] = true;
		stack.push(s);
		while (!stack.isEmpty()) {
			int v = stack.peek();
			if (adj[v].hasNext()) {
				int w = adj[v].next();
				// StdOut.printf("check %d\n", w);
				if (!marked[w]) {
					// discovered vertex w for the first time
					marked[w] = true;
					edgeTo[w] = v;
					stack.push(w);
					// StdOut.printf("dfs(%d)\n", w);
				}
			} else {
				// StdOut.printf("%d done\n", v);
				stack.pop();
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for (int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}

}
