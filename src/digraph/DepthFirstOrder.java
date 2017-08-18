package digraph;

import queue.Queue;
import stack.Stack;
import edgeweighteddigraph.DirectedEdge;
import edgeweighteddigraph.EdgeWeightedDigraph;

public class DepthFirstOrder {
	private boolean marked[];
	
	private Queue<Integer> pre;
	private Queue<Integer> post;
	private Stack<Integer> reversepost;
	
	
	public DepthFirstOrder(Digraph G){
		pre =new Queue<Integer>();
		post =new Queue<Integer>();
		reversepost =new Stack<Integer>();
		
		marked=new boolean[G.V()];
		
		for(int i=0;i<G.V();i++){
			if(!marked[i])
				dfs(G,i);
		}
	}
	
	public DepthFirstOrder(EdgeWeightedDigraph G){
		pre =new Queue<Integer>();
		post =new Queue<Integer>();
		reversepost =new Stack<Integer>();
		
		marked=new boolean[G.V()];
		
		for(int i=0;i<G.V();i++){
			if(!marked[i])
				dfs(G,i);
		}
	}
	
	
	private void dfs(Digraph G,int v){
		pre.enqueue(v);
		marked[v]=true;
		for(int w:G.adj(v)){
			if(!marked[w])
				dfs(G,w);
		}
		post.enqueue(v);
		reversepost.push(v);
	}
	
	private void dfs(EdgeWeightedDigraph G,int v){
		pre.enqueue(v);
		marked[v]=true;
		for(DirectedEdge e:G.adj(v)){
			int w=e.to();
			if(!marked[w])
				dfs(G,w);
		}
		post.enqueue(v);
		reversepost.push(v);
	}
	
	public Iterable<Integer> preorder(){return pre;}
	
	public Iterable<Integer> postorder(){return post;}
	
	public Iterable<Integer> reversepostorder(){return reversepost;}
	
	
	
	

}
