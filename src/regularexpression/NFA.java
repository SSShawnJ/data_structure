package regularexpression;

import java.util.HashSet;

import linkedList.LinkedList;
import stack.Stack;
import digraph.Digraph;
import digraph.DirectedDFS;

/*The following features are not supported:
	 *    - The + operator
	 *    - Metacharacters in the text
	 *    - Character classes.
	 *    
*/
public class NFA {

	private char[] re;    //match transition
	private Digraph G;    //epsilon transition
	private int M;        //number of states
	
	public NFA(String regexp){
		Stack<Integer> leftp=new Stack<Integer>();
		Stack<Integer> orOperator=new Stack<Integer>();
		re=regexp.toCharArray();
		M=re.length;
		G=new Digraph(M+1);
		
		for(int i=0;i<M;i++){
			int lp=i;
			if(re[i]=='(')
				leftp.push(i);
			else if(re[i]=='|')
				orOperator.push(i);
			
			else if(re[i]==')'){
				lp=leftp.pop();
				while(!orOperator.isEmpty() && orOperator.peek()>lp){
					int or=orOperator.pop();
					G.addEdge(lp, or+1);
					G.addEdge(or, i);
				}
				
			}
			
			//look ahead
			if(i<M-1 && re[i+1]=='*'){
				G.addEdge(lp, i+1);
				G.addEdge(i+1, lp);
			}
			
			if(re[i]=='(' || re[i]==')' || re[i]=='*')
				G.addEdge(i, i+1);
		}
	}
	
	public boolean recognizes(String txt){
		HashSet<Integer> reachableState=new HashSet<Integer>();
		DirectedDFS dfs=new DirectedDFS(G,0);
		
		for(int i=0;i<G.V();i++){
			if(dfs.marked(i)) reachableState.add(i);
		}
		
		for(int i=0;i<txt.length();i++){
			LinkedList<Integer> states=new LinkedList<Integer>();
			for(int v:reachableState){
				if(v<M){
					if(re[v]==txt.charAt(i) || re[v]=='.')
						states.add(v+1);
				}
			}
			reachableState=new HashSet<Integer>();
			dfs=new DirectedDFS(G,states);
			for(int v=0;v<G.V();v++){
				if(dfs.marked(v)) reachableState.add(v);
			}
		}
		
		if(reachableState.contains(M)) return true;
		return false;
		
	}
	
	
	//unit testing
	public static void main(String[] args) {
		NFA nfa=new NFA("(A*B|AC)D");
		System.out.println(nfa.recognizes("AAAABD"));
		System.out.println(nfa.recognizes("AAAAC"));
		
		nfa=new NFA("(A*|B|(C|.*)*D)");
		System.out.println(nfa.recognizes("ACCCCCEEEED"));
		System.out.println(nfa.recognizes("abcbcbcdaaaabcbcdaaadddD"));
		
		
	}
	
	
}
