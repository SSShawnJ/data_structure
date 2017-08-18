package graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import linkedList.LinkedList;
import stack.StackLinkedList;
/*This is the adjacency Linked List representation of graph.
 * 
 * */

public class Graph {

	
	
	
	private static final String NEWLINE = System.getProperty("line.separator");

	
	private final int V;
	private int E;
	private LinkedList<Integer>[] adj;
	
	public Graph(int V){
		this.V=V;
		this.E=0;
		adj=(LinkedList<Integer>[]) new LinkedList[V];
		for(int i=0;i<V;i++){
			adj[i]=new LinkedList<Integer>();
		}
	}
	
	public Graph(String file)throws FileNotFoundException{
		Scanner sc=new Scanner(new File(file));
		this.V=sc.nextInt();
		this.E = 0;
		//number of edges;
        sc.nextInt();
		adj=(LinkedList<Integer>[]) new LinkedList[V];
		for(int i=0;i<V;i++){
			adj[i]=new LinkedList<Integer>();
		}
		
		//read edges
		while(sc.hasNext()){
			int v1=sc.nextInt();
			int v2=sc.nextInt();
			
			addEdge(v1,v2);
		}
		sc.close();
	}
	
	
	 public Graph(Graph G) {
	        this(G.V());
	        this.E = G.E();
	        for (int v = 0; v < G.V(); v++) {
	            // reverse so that adjacency list is in same order as original
	            StackLinkedList<Integer> reverse = new StackLinkedList<Integer>();
	            for (int w : G.adj[v]) {
	                reverse.push(w);
	            }
	            for (int w : reverse) {
	                adj[v].add(w);
	            }
	        }
	    }
	
	
	public int V(){return V;}
	
	public int E(){return E;}
	
	public void addEdge(int v,int w){
		validateVertex(v);
		validateVertex(w);
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public int degree(int v){
		validateVertex(v);
		return adj[v].size();
	}
	
	public int maxDegree(){
		int max=0;
		for(int v=0;v<this.V;v++){
			if(adj[v].size()>max)
				max=adj[v].size();
		}
		return max;
	}
	
	public double averageDegree(){
		return 2.0*this.E/this.V;
	}
	
	public int numberOfSelfLoops(){
		int count=0;
		for(int v=0;v<this.V;v++){
			for(int w:adj(v)){
				if(v==w) count++;
			}
		}
		return count/2;    //each edge counted twice;
	}
	
	public String toString(){
		StringBuilder s=new StringBuilder();
		s.append(this.V+" vertices, "+this.E+" edges"+NEWLINE);
		for(int v=0;v<V;v++){
			s.append(v+":");
			for(int w:adj(v)){
				s.append(w+" ");
			}
			s.append(NEWLINE);
		}
		return new String(s);
	}
	
	private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Graph x=new Graph("graph.txt");
		System.out.println(x.toString());
	}
	
	
	
	
	

}
