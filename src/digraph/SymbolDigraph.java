package digraph;

import java.util.Scanner;

import symbol_table.ST;

public class SymbolDigraph {
	 private ST<String, Integer> st;  // string -> index
	    private String[] keys;           // index  -> string
	    private Digraph G;

	    /**  
	     * Initializes a Digraph from a file using the specified delimiter.
	     * Each line in the file contains
	     * the name of a vertex, followed by a list of the names
	     * of the vertices adjacent to that vertex, separated by the delimiter.
	     * @param filename the name of the file
	     * @param delimiter the delimiter between fields
	     */
	    public SymbolDigraph(String filename, String delimiter) {
	        st = new ST<String, Integer>();

	        // First pass builds the index by reading strings to associate
	        // distinct strings with an index
	        Scanner in = new Scanner(filename);
	        // while (in.hasNextLine()) {
	        while (in.hasNextLine()) {
	            String[] a = in.nextLine().split(delimiter);
	            for (int i = 0; i < a.length; i++) {
	                if (!st.contains(a[i]))
	                    st.put(a[i], st.size());
	            }
	        }
	        in.close();
	        

	        // inverted index to get string keys in an array
	        keys = new String[st.size()];
	        for (String name : st.keys()) {
	            keys[st.get(name)] = name;
	        }

	        // second pass builds the Digraph by connecting first vertex on each
	        // line to all others
	        G = new Digraph(st.size());
	        in = new Scanner(filename);
	        while (in.hasNextLine()) {
	            String[] a = in.nextLine().split(delimiter);
	            int v = st.get(a[0]);
	            for (int i = 1; i < a.length; i++) {
	                int w = st.get(a[i]);
	                G.addEdge(v, w);
	            }
	        }
	        in.close();
	    }

	    /**
	     * Does the Digraph contain the vertex named <tt>s</tt>?
	     * @param s the name of a vertex
	     * @return <tt>true</tt> if <tt>s</tt> is the name of a vertex, and <tt>false</tt> otherwise
	     */
	    public boolean contains(String s) {
	        return st.contains(s);
	    }

	    /**
	     * Returns the integer associated with the vertex named <tt>s</tt>.
	     * @param s the name of a vertex
	     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named <tt>s</tt>
	     */
	    public int index(String s) {
	        return st.get(s);
	    }

	    /**
	     * Returns the name of the vertex associated with the integer <tt>v</tt>.
	     * @param v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
	     * @return the name of the vertex associated with the integer <tt>v</tt>
	     */
	    public String name(int v) {
	        return keys[v];
	    }

	    /**
	     * Returns the graph assoicated with the symbol Digraph. It is the client's responsibility
	     * not to mutate the Digraph.
	     * @return the graph associated with the symbol Digraph
	     */
	    public Digraph G() {
	        return G;
	    }


	    

}
