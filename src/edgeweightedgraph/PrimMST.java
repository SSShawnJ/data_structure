package edgeweightedgraph;

import priorityQueue.IndexMinPQ;
import queue.Queue;

public class PrimMST {

	private Edge[] edgeTo;
	private double[] distTo;
	private boolean[] marked;
	private IndexMinPQ<Double> pq;
	
	PrimMST(EdgeWeightedGraph G){
		edgeTo=new Edge[G.V()];
		distTo=new double[G.V()];
		for(int i=0;i<G.V();i++){
			distTo[i]=Double.POSITIVE_INFINITY;
		}
		marked=new boolean[G.V()];
		pq=new IndexMinPQ<Double>(G.V());
		
		for(int v=0;v<G.V();v++){               // run from each vertex to find
			if(!marked[v]) prim(G,v);           // minimum spanning forest
		}
		
	}
	
	
	private void prim(EdgeWeightedGraph G,int s){
		distTo[s]=0.0;
		pq.insert(s, 0.0);
		while(!pq.isEmpty()){
			visit(G,pq.delMin());
		}
	}
	
	private void visit(EdgeWeightedGraph G,int v){
		marked[v]=true;
		for(Edge e:G.adj(v)){
			int w=e.other(v);
			if(marked[w]) continue;
			if(e.weight()<distTo[w]){
				distTo[w]=e.weight();
				edgeTo[w]=e;
				if(pq.contains(w)) pq.changeKey(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}
	
	
	public Iterable<Edge> edges(){
		Queue<Edge> mst=new Queue<Edge>();
		for(int i=0;i<edgeTo.length;i++){
			Edge e = edgeTo[i];
			if(e!=null) mst.enqueue(e);
		}
		return mst;
	}
	
	public double weight(){
		double weight=0.0;
		for(Edge e:edges()){
			weight+=e.weight();
		}
		return weight;
	}
	
	
	
}
