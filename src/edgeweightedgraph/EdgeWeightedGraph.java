package edgeweightedgraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import linkedList.LinkedList;
import stack.Stack;

/*parallel edges allowed
 * self-loop has been omitted,one should add it if necessary
 * 
 * */

public class EdgeWeightedGraph {
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final int V;               //number of vertices
	private int E;                     //number of edges
	private LinkedList<Edge>[] adj;    //adjacency lists

	public EdgeWeightedGraph(int V){
		 if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V=V;
		this.E=0;
		adj=(LinkedList<Edge>[])new LinkedList[V];
		for(int i=0;i<V;i++){
			adj[i]=new LinkedList<Edge>();
		}
	}
	
	 /**  
     * Initializes an edge-weighted graph from an input stream.
     * @param  in the input stream
     * @throws IndexOutOfBoundsException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public EdgeWeightedGraph(String file)throws FileNotFoundException {
       Scanner x=new Scanner(new File(file));
       this.V=x.nextInt();
       this.E=x.nextInt();
       if (E < 0) {x.close();throw new IllegalArgumentException("Number of edges must be nonnegative");}
       adj=(LinkedList<Edge>[])new LinkedList[V];
       for(int i=0;i<V;i++){
			adj[i]=new LinkedList<Edge>();
		}
        
       for (int i = 0; i < E; i++) {
            int v = x.nextInt();
            int w = x.nextInt();
            double weight = x.nextDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
        x.close();
    }
	
	
	
	/**
     * Initializes a new edge-weighted graph that is a deep copy of <tt>G</tt>.
     *
     * @param  G the edge-weighted graph to copy
     */
	 public EdgeWeightedGraph(EdgeWeightedGraph G) {
	        this(G.V());
	        this.E = G.E();
	        for (int v = 0; v < G.V(); v++) {
	            // reverse so that adjacency list is in same order as original
	            Stack<Edge> reverse = new Stack<Edge>();
	            for (Edge e : G.adj[v]) {
	                reverse.push(e);
	            }
	            for (Edge e : reverse) {
	                adj[v].add(e);
	            }
	        }
	    }

	
	public int V(){return V;}
	
	public int E(){return E;}
	
	public void addEdge(Edge e){
		int v=e.either();
		int w=e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	
	public int degree(int v) {
        return adj[v].size();
    }
	
	public Iterable<Edge> edges(){
		LinkedList<Edge> list=new LinkedList<Edge>();
		for(int v=0;v<V;v++){
			 int selfLoops = 0;
	         for (Edge e : adj(v)) {
	             if (e.other(v) > v) {
	                 list.add(e);
	             }
	             // only add one copy of each self loop (self loops will be consecutive)
	             else if (e.other(v) == v) {
	                 if (selfLoops % 2 == 0) list.add(e);
                     selfLoops++;
                }
	        }
		}
		return list;
	}
	
	public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

	
	
}
