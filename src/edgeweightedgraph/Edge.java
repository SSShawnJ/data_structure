package edgeweightedgraph;

public class Edge implements Comparable<Edge> {

	private final double weight;
	private final int v;
	private final int w;
	
	//initializing constructor
	public Edge(int v,int w,double weight){
		if (v < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
        if (w < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
		this.v=v;
		this.w=w;
		this.weight=weight;
	}
	
	//weight of this edge
	public double weight(){return weight;}

	//either of this edge's vertices
	public int either(){return v;}
	
	//the other vertex
	public int other(int vertex){
		if(vertex==v) return w;
		else if(vertex==w)return v;
		else throw new RuntimeException("Inconsistent edge");
	}
	
	//compare this edge to that
	public int compareTo(Edge that){
		if(this.weight()>that.weight()) return +1;
		else if(this.weight()>that.weight()) return -1;
		else return 0;
	}
	
	//String representation 
	public String toString(){
		return String.format("%d-%d %.5f", v,w,weight);
	}
}
