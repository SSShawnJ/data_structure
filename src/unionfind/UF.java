package unionfind;

public class UF {
	 private int[] parent;  // parent[i] = parent of i
	    private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
	    private int count;     // number of components

	    /**
	     * Initializes an empty union-find data structure with <tt>N</tt> sites
	     * <tt>0</tt> through <tt>N-1</tt>. Each site is initially in its own 
	     * component.
	     *
	     * @param  N the number of sites
	     * @throws IllegalArgumentException if <tt>N &lt; 0</tt>
	     */
	    public UF(int N) {
	        if (N < 0) throw new IllegalArgumentException();
	        count = N;
	        parent = new int[N];
	        rank = new byte[N];
	        for (int i = 0; i < N; i++) {
	            parent[i] = i;
	            rank[i] = 0;
	        }
	    }

	    /**
	     * Returns the component identifier for the component containing site <tt>p</tt>.
	     *
	     * @param  p the integer representing one site
	     * @return the component identifier for the component containing site <tt>p</tt>
	     * @throws IndexOutOfBoundsException unless <tt>0 &le; p &lt; N</tt>
	     */
	    public int find(int p) {
	        validate(p);
	        while (p != parent[p]) {
	            parent[p] = parent[parent[p]];    // path compression by halving
	            p = parent[p];
	        }
	        return p;
	    }

	    /**
	     * Returns the number of components.
	     *
	     * @return the number of components (between <tt>1</tt> and <tt>N</tt>)
	     */
	    public int count() {
	        return count;
	    }
	  
	    /**
	     * Returns true if the the two sites are in the same component.
	     *
	     * @param  p the integer representing one site
	     * @param  q the integer representing the other site
	     * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt> are in the same component;
	     *         <tt>false</tt> otherwise
	     * @throws IndexOutOfBoundsException unless
	     *         both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
	     */
	    public boolean connected(int p, int q) {
	        return find(p) == find(q);
	    }
	  
	    /**
	     * Merges the component containing site <tt>p</tt> with the 
	     * the component containing site <tt>q</tt>.
	     *
	     * @param  p the integer representing one site
	     * @param  q the integer representing the other site
	     * @throws IndexOutOfBoundsException unless
	     *         both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
	     */
	    public void union(int p, int q) {
	        int rootP = find(p);
	        int rootQ = find(q);
	        if (rootP == rootQ) return;

	        // make root of smaller rank point to root of larger rank
	        if      (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
	        else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
	        else {
	            parent[rootQ] = rootP;
	            rank[rootP]++;
	        }
	        count--;
	    }

	    // validate that p is a valid index
	    private void validate(int p) {
	        int N = parent.length;
	        if (p < 0 || p >= N) {
	            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));  
	        }
	    }



	public static void main(String[] args) {
		UF x=new UF(8);
		System.out.println(x.connected(1, 2));
		System.out.println(x.count());
		x.union(1, 2);
		System.out.println(x.connected(1, 2));
		System.out.println(x.count());

	}

}
