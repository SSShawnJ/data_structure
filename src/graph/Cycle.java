package graph;

import java.io.FileNotFoundException;

public class Cycle {
	private boolean[] marked;
	private boolean hasCycle;
	
	public Cycle(Graph G){
		marked=new boolean[G.V()];
		hasCycle=false;
		for(int s=0;s<G.V();s++){
			if(!marked[s]){
				dfs(G,s,s);
			}
		}
	}
	
	private void dfs(Graph G,int v,int from){
		marked[v]=true;
		for(int w:G.adj(v)){
			if(!marked[w])
				dfs(G,w,v);
			else if(w!=v) hasCycle=true;
		}
	}
	
	public boolean hasCycle(){return hasCycle;}
	
	public static void main(String[] args)throws FileNotFoundException {
		Graph a=new Graph("graph.txt");
		Cycle x=new Cycle(a);
		System.out.println(x.hasCycle);
	}

}
