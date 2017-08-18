package digraph;

import stack.Stack;
import edgeweighteddigraph.DirectedEdge;
import edgeweighteddigraph.EdgeWeightedDigraph;

public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	private boolean[] onStack;
	
	
	public DirectedCycle(Digraph G){
		onStack=new boolean[G.V()];
		marked=new boolean[G.V()];
		edgeTo=new int[G.V()];
		for(int v=0;v<G.V();v++){
			if(!marked[v]&& cycle == null)
				dfs(G,v);
		}
		
	}
	
	public DirectedCycle(EdgeWeightedDigraph G){
		onStack=new boolean[G.V()];
		marked=new boolean[G.V()];
		edgeTo=new int[G.V()];
		for(int v=0;v<G.V();v++){
			if(!marked[v]&& cycle == null)
				dfs(G,v);
		}
		
	}
	
	private void dfs(Digraph G,int v){
		onStack[v]=true;
		marked[v]=true;
		for(int w:G.adj(v)){
			// short circuit if directed cycle found
			if(this.hasCycle())return;
			
			//found new vertex, so recur
			else if(!marked[w]){
				edgeTo[w]=v;
				dfs(G,w);
			}
			
			// trace back directed cycle
			else if(onStack[w]){
				cycle=new Stack<Integer>();
				for(int x=v;v!=w;x=edgeTo[x]){
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v]=false;
	}
	
	private void dfs(EdgeWeightedDigraph G,int v){
		onStack[v]=true;
		marked[v]=true;
		for(DirectedEdge e:G.adj(v)){
			int w=e.to();
			// short circuit if directed cycle found
			if(this.hasCycle())return;
			
			
			
			//found new vertex, so recur
			else if(!marked[w]){
				edgeTo[w]=v;
				dfs(G,w);
			}
			
			// trace back directed cycle
			else if(onStack[w]){
				cycle=new Stack<Integer>();
				for(int x=v;v!=w;x=edgeTo[x]){
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v]=false;
	}
	
	public boolean hasCycle(){
		return cycle!=null;
	}
	
	
	public Iterable<Integer> cycle(){
		return cycle;
	}
	
	
	
	
	
	
	

}
