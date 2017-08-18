package edgeweighteddigraph;

import stack.Stack;
import digraph.Topological;

public class AcyclicSP {
	
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	
	public AcyclicSP(EdgeWeightedDigraph G,int s){
		distTo=new double[G.V()];
		edgeTo=new DirectedEdge[G.V()];
		for(int i=0;i<G.V();i++){
			distTo[i]=Double.POSITIVE_INFINITY;
		}
		
		distTo[s]=0.0;
		
		Topological top=new Topological(G);
		
		for(int v:top.order()){
			relax(G,v);
		}
		
	}
	
	
	private void relax(EdgeWeightedDigraph G,int v){
		for(DirectedEdge e:G.adj(v)){
			int w=e.to();
			if(distTo[w]>distTo[v]+e.weight()){
				distTo[w]=distTo[v]+e.weight();
				edgeTo[w]=e;
			}
		}
	}
	
	
	public double distTo(int v){return distTo[v];}
	
	public boolean hasPathTo(int v){return distTo[v]<Double.POSITIVE_INFINITY;}
	
	public Iterable<DirectedEdge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for(DirectedEdge e=edgeTo[v];e!=null;e=edgeTo[e.from()]){
			path.push(e);
		}
		return path;
	}
	

}
