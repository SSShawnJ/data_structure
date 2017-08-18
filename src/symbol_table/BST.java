package symbol_table;

import java.util.NoSuchElementException;

import queue.Queue;

public class BST<Key extends Comparable<Key>,Value> {
	private Node root;
	
	private class Node{
		private Key key;           // sorted by key
	    private Value val;         // associated data
	    private Node left, right;  // left and right subtrees
	    private int N;             // number of nodes in subtree
	    
		public Node(Key key,Value val,int N){
			this.key=key;this.val=val;this.N=N;
		}
	}
	
	
	 /**
     * Initializes an empty symbol table.
     */
    public BST() {
    }

	
    /**
     * Returns true if this symbol table is empty.
     * @return <tt>true</tt> if this symbol table is empty; <tt>false</tt> otherwise
     */
	public boolean isEmpty(){
		return size()==0;
	}
	
	
	/**
     * Does this symbol table contain the given key?
     *
     * @param  key the key
     * @return <tt>true</tt> if this symbol table contains <tt>key</tt> and
     *         <tt>false</tt> otherwise
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */	
	 public boolean contains(Key key) {
	        if (key == null) throw new NullPointerException("argument to contains() is null");
	        return get(key) != null;
	    }
	
	 /**
	  * Returns the number of key-value pairs in this symbol table.
	  * @return the number of key-value pairs in this symbol table
      */
	public int size(){
		return size(root);
	}
	// return number of key-value pairs in BST rooted at x
	private int size(Node x){
		if(x==null) return 0;
		else return x.N;
	}
	
	/**
     * Returns the number of keys in the symbol table in the given range.
     *
     * @return the number of keys in the sybol table between <tt>lo</tt> 
     *         (inclusive) and <tt>hi</tt> (exclusive)
     * @throws NullPointerException if either <tt>lo</tt> or <tt>hi</tt>
     *         is <tt>null</tt>
     */
    public int size(Key lo, Key hi) {
        if (lo == null) throw new NullPointerException("first argument to size() is null");
        if (hi == null) throw new NullPointerException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }

	
	
	
	 /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is <tt>null</tt>.
     *
     * @param  key the key
     * @param  val the value
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */	
	public void put(Key key,Value val){
		 if (key == null) throw new NullPointerException("first argument to put() is null");
	     if (val == null) {
	         delete(key);
             return;
         }
		root=put(root,key,val);
	}
	private Node put(Node x,Key key,Value val){
		if(x==null) return new Node(key,val,1);
		
		int cmp=key.compareTo(x.key);
		if(cmp<0) x.left=put(x.left,key,val);
		else if(cmp>0) x.right=put(x.right,key,val);
		else x.val=val;
		x.N=size(x.left)+1+size(x.right);
		return x;
	}

	/**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and <tt>null</tt> if the key is not in the symbol table
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */	
	public Value get(Key key){
		return get(root,key);
	}
	private Value get(Node x,Key key){
		if(x==null) return null;
		
		int cmp=key.compareTo(x.key);
		if(cmp<0) return get(x.left,key);
		else if(cmp>0) return get(x.right,key);
		else return x.val;
	}
	
	/**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */	
	public Key min(){
		if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		return min(root).key;
	}
	private Node min(Node x){
		if(x==null) return null;
		if(x.left==null) return x;
		return min(x.left);
	}
	
	 /**
     * Returns the largest key in the symbol table.
     *
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
	public Key max(){
		if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
		return max(root).key;
	}
	private Node max(Node x){
		if(x.right==null) return x;
		return max(x.right);
	}
	
	/**
     * Returns the largest key in the symbol table less than or equal to <tt>key</tt>.
     *
     * @param  key the key
     * @return the largest key in the symbol table less than or equal to <tt>key</tt>
     * @throws NoSuchElementException if there is no such key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
	public Key floor(Key key){
		if (key == null) throw new NullPointerException("argument to floor() is null");
	    if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
		Node x=floor(root,key);
		if(x==null) return null;
		return x.key;
	}
	private Node floor(Node x,Key key){
		if(x==null) return null;
		int cmp=key.compareTo(x.key);
		if(cmp==0) return x;
		if(cmp<0) return floor(x.left,key);
		Node t=floor(x.right,key);
		if(t!=null) return t;
		return x;
	}
	
	 /**
     * Returns the smallest key in the symbol table greater than or equal to <tt>key</tt>.
     *
     * @param  key the key
     * @return the smallest key in the symbol table greater than or equal to <tt>key</tt>
     * @throws NoSuchElementException if there is no such key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
	public Key ceiling(Key key){
		if (key == null) throw new NullPointerException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
		Node x=ceiling(root,key);
		if(x==null) return null;
		return x.key;
	}
	private Node ceiling(Node x,Key key){
		if(x==null) return null;
		int cmp=key.compareTo(x.key);
		if(cmp==0) return x;
		if(cmp>0) return ceiling(x.right,key);
		Node t=ceiling(x.left,key);
		if(t!=null) return t;
		return x;
	}
	
	
	 /**
     * Return the kth smallest key in the symbol table.
     *
     * @param  k the order statistic
     * @return the kth smallest key in the symbol table
     * @throws IllegalArgumentException unless <tt>k</tt> is between 0 and
     *        <em>N</em> &minus; 1
     */
	public Key select(int i){
		if (i < 0 || i >= size()) throw new IllegalArgumentException();
		Node x=select(root,i);
		if(x==null) return null;
		return x.key;
	}
	 // Return key of rank k. 
	private Node select(Node x,int k){
		if(x==null) return null;
		int t=size(x.left);
		if(t>k) return select(x.left,k);
		else if(t<k) return select(x.right,k-t-1);
		else return x;
	}
	
	
	 /**
     * Return the number of keys in the symbol table strictly less than <tt>key</tt>.
     *
     * @param  key the key
     * @return the number of keys in the symbol table strictly less than <tt>key</tt>
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
	public int rank(Key key){
		if (key == null) throw new NullPointerException("argument to rank() is null");
		return rank(root,key);
	}
	private int rank(Node x,Key key){
		if(x==null) return 0;
		int cmp=key.compareTo(x.key);
		if(cmp>0)return size(x.left)+1+rank(x.right,key);
		else if(cmp<0)return rank(x.left,key);
		else return size(x.left);
	}
	
	/**
     * Removes the smallest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
	public void deleteMin(){
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		root=deleteMin(root);
	}
	private Node deleteMin(Node x){	
		if(x.left==null) return x.right;
		x.left=deleteMin(x.left);
		x.N=size(x.left)+1+size(x.right);
		return x;
	}
	
	/**
     * Removes the largest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
	
    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     * @param  key the key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
	public void delete(Key key){
		 if (key == null) throw new NullPointerException("argument to delete() is null");
		root=delete(root,key);
	}
	private Node delete(Node x,Key key){
		if(x==null) return null;
		
		int cmp=key.compareTo(x.key);
		if(cmp>0) x.right=delete(x.right,key);
		else if(cmp<0) x.left=delete(x.left,key);
		else{
			if(x.left==null) return x.right;
			if(x.right==null) return x.left;
			Node t=x;
			x=min(t.right);
			x.right=deleteMin(t.right);
			x.left=t.left;
			
		}
		x.N=size(x.left)+1+size(x.right);
		return x;
	}
	
	
	/**
     * Returns all keys in the symbol table as an <tt>Iterable</tt>.
     * To iterate over all of the keys in the symbol table named <tt>st</tt>,
     * use the foreach notation: <tt>for (Key key : st.keys())</tt>.
     *
     * @return all keys in the symbol table
     */
	public Iterable<Key> keys(){
		return keys(min(),max());
	}
	/**
     * Returns all keys in the symbol table in the given range,
     * as an <tt>Iterable</tt>.
     *
     * @return all keys in the sybol table between <tt>lo</tt> 
     *         (inclusive) and <tt>hi</tt> (exclusive)
     * @throws NullPointerException if either <tt>lo</tt> or <tt>hi</tt>
     *         is <tt>null</tt>
     */
	public Iterable<Key> keys(Key lo,Key hi){
		if (lo == null) throw new NullPointerException("first argument to size() is null");
        if (hi == null) throw new NullPointerException("second argument to size() is null");
        
		Queue<Key> queue=new Queue<Key>();
		keys(root,lo,hi,queue);
		return queue;
	}
	private void keys(Node x,Key lo,Key hi,Queue<Key> queue){
		if(x==null) return;
		int cmplo=lo.compareTo(x.key);
		int cmphi=hi.compareTo(x.key);
		if(cmplo<0) keys(x.left,lo,hi,queue);
		if(cmplo<=0 && cmphi>=0) queue.enqueue(x.key);
		if(cmphi>0) keys(x.right,lo,hi,queue);
	}
	
	
	/**
     * Returns the height of the BST (for debugging).
     *
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /**
     * Returns the keys in the BST in level order (for debugging).
     *
     * @return the keys in the BST in level order traversal
     */
    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }
	
	public static void main(String[] args) {
		BST<String,Integer> bst=new BST<String,Integer>();
		bst.put("E", 1);
		bst.put("D", 1);
		bst.put("Q", 1);
		bst.put("A", 1);
		bst.put("J", 1);
		bst.put("M", 1);
		bst.put("T", 1);
		bst.put("S", 1);
		System.out.println(bst.select(5));
		bst.delete(bst.select(5));
		System.out.println(bst.select(5));
		System.out.println(bst.keys());
		
	}
	
	
	
	
	
	
	
	
	
}
