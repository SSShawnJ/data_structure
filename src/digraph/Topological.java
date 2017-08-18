package digraph;

import edgeweighteddigraph.EdgeWeightedDigraph;


public class Topological {
	private Iterable<Integer> order;
	
	public Topological(Digraph G){
		DirectedCycle cyclefinder=new DirectedCycle(G);
		if(!cyclefinder.hasCycle()){
			DepthFirstOrder dfs=new DepthFirstOrder(G);
			order=dfs.reversepostorder();
		}
		
	}
	
	public Topological(EdgeWeightedDigraph G){
		DirectedCycle cyclefinder=new DirectedCycle(G);
		if(!cyclefinder.hasCycle()){
			DepthFirstOrder dfs=new DepthFirstOrder(G);
			order=dfs.reversepostorder();
		}
		
	}
	
	public Iterable<Integer> order(){
		return order;
	}
	
	public boolean isDAG(){
		return order !=null;
	}

}
