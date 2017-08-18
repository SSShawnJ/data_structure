package edgeweighteddigraph;

import java.io.FileNotFoundException;

import queue.Queue;
import stack.Stack;

public class BellmanFordSP {

	private double[] disTo;
	private boolean[] onQ;
	private DirectedEdge[] edgeTo;
	private Queue<Integer> queue;
	private int cost;
	private Iterable<DirectedEdge> cycle;
	
	
	BellmanFordSP(EdgeWeightedDigraph G,int s){
		disTo=new double[G.V()];
		for(int i=0;i<G.V();i++){
			disTo[i]=Double.POSITIVE_INFINITY;
		}
		disTo[s]=0.0;
		edgeTo=new  DirectedEdge[G.V()];
		onQ=new boolean[G.V()];
		queue=new Queue<Integer>();
		
		queue.enqueue(s);
		onQ[s]=true;
		while(!queue.isEmpty()&& !hasNegativeCycle()){
			int v=queue.dequeue();
			onQ[v]=false;
			relax(G,v);
		}
	}
	
	private void relax(EdgeWeightedDigraph G,int v){
		for(DirectedEdge e:G.adj(v)){
			int w=e.to();
			if(disTo[w]>(disTo[v]+e.weight())){
				disTo[w]=disTo[v]+e.weight();
				edgeTo[w]=e;
				if(!onQ[w]){
					queue.enqueue(w);
					onQ[w]=true;
				}
			}
		}
		if(cost++%G.V()==0){
			//System.out.println(cost);
			findNegativeCycle();
			if (hasNegativeCycle()) return;
		}
		
	}
	
	public double disTo(int v){return disTo[v];}
	public boolean hasPathTo(int v){return edgeTo[v]!=null;}
	public Iterable<DirectedEdge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<DirectedEdge> path=new Stack<DirectedEdge>();
		for(DirectedEdge e=edgeTo[v];e!=null;e=edgeTo[e.from()]){
			path.push(e);
		}
		return path;
	}
	
	
	
	private void findNegativeCycle(){
		int V=edgeTo.length;
		EdgeWeightedDigraph spt=new EdgeWeightedDigraph(V);
		for(int i=0;i<V;i++){
			if(edgeTo[i]!=null){
				spt.addEdge(edgeTo[i]);
			}
		}
		
		EdgeWeightedDirectedCycle cf=new EdgeWeightedDirectedCycle(spt);
		cycle=cf.cycle();
	}
	
	public boolean hasNegativeCycle(){
		return cycle!=null;
	}
	
	public Iterable<DirectedEdge> negativeCycle(){return cycle;}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		EdgeWeightedDigraph g=new EdgeWeightedDigraph("EdgeWeightedDigraph.txt");
		BellmanFordSP s=new BellmanFordSP(g,0);
		System.out.println(s.hasNegativeCycle());
	}
	
}
