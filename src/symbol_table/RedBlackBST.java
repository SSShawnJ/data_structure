package symbol_table;

import java.util.NoSuchElementException;

import queue.Queue;

public class RedBlackBST<Key extends Comparable<Key>,Value> {
	
	private static final boolean Red=true;
	private static final boolean Black=false;
	
	private Node root;

	private class Node{
		private Key key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean colour;     // color of parent link
        private int N;             // subtree count
		
		public Node(Key key,Value val,boolean colour,int N){
			this.key=key;
			this.val=val;
			this.N=N;
			this.colour=colour;
		}
	}
	
	
	/**
     * Initializes an empty symbol table.
     */
    public RedBlackBST() {}
    
    
    
    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size(){
    	return size(root);
    }
    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    } 
    
    
    /**
     * Is this symbol table empty?
     * @return <tt>true</tt> if this symbol table is empty and <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    
    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return <tt>true</tt> if this symbol table contains <tt>key</tt> and
     *     <tt>false</tt> otherwise
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }
    
    
    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is <tt>null</tt>.
     *
     * @param key the key
     * @param val the value
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void put(Key key,Value val){
    	 if (key == null) throw new NullPointerException("first argument to put() is null");
         if (val == null) {
             //////////////////////////////////////////////delete(key);
             return;
         }

    	root=put(root,key,val);
    	root.colour=Black;
    }
    // insert the key-value pair in the subtree rooted at h
    private Node put(Node x,Key key,Value val){
    	//do standard insert, with red link to parent.
    	if(x==null) return new Node(key,val,Red,1);
    	
    	int cmp=key.compareTo(x.key);
    	if(cmp<0) x.left=put(x.left,key,val);
    	else if(cmp >0) x.right=put(x.right,key,val);
    	else x.val=val;
    	
    	if(isRed(x.right) && !isRed(x.left)) x=rotateLeft(x);
    	if(isRed(x.left) && isRed(x.left.left)) x=rotateRight(x);
    	if(isRed(x.right)&& isRed(x.left)) flipColour(x);
    	
    	x.N=size(x.left)+1+size(x.right);
    	return x;
    }
    
    
    /***************************************************************************
     *  Standard BST search.
     ***************************************************************************/

    
     /**
      * Returns the value associated with the given key.
      * @param key the key
      * @return the value associated with the given key if the key is in the symbol table
      *     and <tt>null</tt> if the key is not in the symbol table
      * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
      */
    public  Value get(Key key){
    	if (key == null) throw new NullPointerException("argument to get() is null");
    	return get(root,key);
    }
    private Value get(Node x,Key key){
    	while (x != null) {
            int cmp = key.compareTo(x.key);
            if(cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }
    
    
    /***************************************************************************
     *  Red-black tree deletion.
     ***************************************************************************/
    
    /**
     * Removes the smallest key and associated value from the symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.colour = Red;

        root = deleteMin(root);
        if (!isEmpty()) root.colour = Black;
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) { 
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }


    /**
     * Removes the largest key and associated value from the symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.colour = Red;

        root = deleteMax(root);
        if (!isEmpty()) root.colour = Black;
        // assert check();
    }

    // delete the key-value pair with the maximum key rooted at h
    private Node deleteMax(Node h) { 
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     * @param  key the key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void delete(Key key) { 
        if (key == null) throw new NullPointerException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.colour = Red;

        root = delete(root, key);
        if (!isEmpty()) root.colour = Black;
        // assert check();
    }

    // delete the key-value pair with the given key rooted at h
    private Node delete(Node h, Key key) { 
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }
    
    
    /***************************************************************************
     *  Utility functions.
     ***************************************************************************/

     /**
      * Returns the height of the BST (for debugging).
      * @return the height of the BST (a 1-node tree has height 0)
      */
     public int height() {
         return height(root);
     }
     private int height(Node x) {
         if (x == null) return -1;
         return 1 + Math.max(height(x.left), height(x.right));
     }

    /***************************************************************************
     *  Ordered symbol table methods.
     ***************************************************************************/

     /**
      * Returns the smallest key in the symbol table.
      * @return the smallest key in the symbol table
      * @throws NoSuchElementException if the symbol table is empty
      */
     public Key min() {
         if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
         return min(root).key;
     } 

     // the smallest key in subtree rooted at x; null if no such key
     private Node min(Node x) { 
         // assert x != null;
         if (x.left == null) return x; 
         else                return min(x.left); 
     } 

     /**
      * Returns the largest key in the symbol table.
      * @return the largest key in the symbol table
      * @throws NoSuchElementException if the symbol table is empty
      */
     public Key max() {
         if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
         return max(root).key;
     } 

     // the largest key in the subtree rooted at x; null if no such key
     private Node max(Node x) { 
         // assert x != null;
         if (x.right == null) return x; 
         else                 return max(x.right); 
     } 


     /**
      * Returns the largest key in the symbol table less than or equal to <tt>key</tt>.
      * @param key the key
      * @return the largest key in the symbol table less than or equal to <tt>key</tt>
      * @throws NoSuchElementException if there is no such key
      * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
      */
     public Key floor(Key key) {
         if (key == null) throw new NullPointerException("argument to floor() is null");
         if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
         Node x = floor(root, key);
         if (x == null) return null;
         else           return x.key;
     }    

     // the largest key in the subtree rooted at x less than or equal to the given key
     private Node floor(Node x, Key key) {
         if (x == null) return null;
         int cmp = key.compareTo(x.key);
         if (cmp == 0) return x;
         if (cmp < 0)  return floor(x.left, key);
         Node t = floor(x.right, key);
         if (t != null) return t; 
         else           return x;
     }

     /**
      * Returns the smallest key in the symbol table greater than or equal to <tt>key</tt>.
      * @param key the key
      * @return the smallest key in the symbol table greater than or equal to <tt>key</tt>
      * @throws NoSuchElementException if there is no such key
      * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
      */
     public Key ceiling(Key key) {
         if (key == null) throw new NullPointerException("argument to ceiling() is null");
         if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
         Node x = ceiling(root, key);
         if (x == null) return null;
         else           return x.key;  
     }

     // the smallest key in the subtree rooted at x greater than or equal to the given key
     private Node ceiling(Node x, Key key) {  
         if (x == null) return null;
         int cmp = key.compareTo(x.key);
         if (cmp == 0) return x;
         if (cmp > 0)  return ceiling(x.right, key);
         Node t = ceiling(x.left, key);
         if (t != null) return t; 
         else           return x;
     }

     /**
      * Return the kth smallest key in the symbol table.
      * @param k the order statistic
      * @return the kth smallest key in the symbol table
      * @throws IllegalArgumentException unless <tt>k</tt> is between 0 and
      *     <em>N</em> &minus; 1
      */
     public Key select(int k) {
         if (k < 0 || k >= size()) throw new IllegalArgumentException();
         Node x = select(root, k);
         return x.key;
     }

     // the key of rank k in the subtree rooted at x
     private Node select(Node x, int k) {
         // assert x != null;
         // assert k >= 0 && k < size(x);
         int t = size(x.left); 
         if      (t > k) return select(x.left,  k); 
         else if (t < k) return select(x.right, k-t-1); 
         else            return x; 
     } 

     /**
      * Return the number of keys in the symbol table strictly less than <tt>key</tt>.
      * @param key the key
      * @return the number of keys in the symbol table strictly less than <tt>key</tt>
      * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
      */
     public int rank(Key key) {
         if (key == null) throw new NullPointerException("argument to rank() is null");
         return rank(key, root);
     } 

     // number of keys less than key in the subtree rooted at x
     private int rank(Key key, Node x) {
         if (x == null) return 0; 
         int cmp = key.compareTo(x.key); 
         if      (cmp < 0) return rank(key, x.left); 
         else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
         else              return size(x.left); 
     } 

    /***************************************************************************
     *  Range count and range search.
     ***************************************************************************/

     /**
      * Returns all keys in the symbol table as an <tt>Iterable</tt>.
      * To iterate over all of the keys in the symbol table named <tt>st</tt>,
      * use the foreach notation: <tt>for (Key key : st.keys())</tt>.
      * @return all keys in the sybol table as an <tt>Iterable</tt>
      */
     public Iterable<Key> keys() {
         if (isEmpty()) return new Queue<Key>();
         return keys(min(), max());
     }

     /**
      * Returns all keys in the symbol table in the given range,
      * as an <tt>Iterable</tt>.
      * @return all keys in the sybol table between <tt>lo</tt> 
      *    (inclusive) and <tt>hi</tt> (exclusive) as an <tt>Iterable</tt>
      * @throws NullPointerException if either <tt>lo</tt> or <tt>hi</tt>
      *    is <tt>null</tt>
      */
     public Iterable<Key> keys(Key lo, Key hi) {
         if (lo == null) throw new NullPointerException("first argument to keys() is null");
         if (hi == null) throw new NullPointerException("second argument to keys() is null");

         Queue<Key> queue = new Queue<Key>();
         // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
         keys(root, queue, lo, hi);
         return queue;
     } 

     // add the keys between lo and hi in the subtree rooted at x
     // to the queue
     private void keys(Node x, Queue<Key> queue, Key lo, Key hi) { 
         if (x == null) return; 
         int cmplo = lo.compareTo(x.key); 
         int cmphi = hi.compareTo(x.key); 
         if (cmplo < 0) keys(x.left, queue, lo, hi); 
         if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
         if (cmphi > 0) keys(x.right, queue, lo, hi); 
     } 

     /**
      * Returns the number of keys in the symbol table in the given range.
      * @return the number of keys in the sybol table between <tt>lo</tt> 
      *    (inclusive) and <tt>hi</tt> (exclusive)
      * @throws NullPointerException if either <tt>lo</tt> or <tt>hi</tt>
      *    is <tt>null</tt>
      */
     public int size(Key lo, Key hi) {
         if (lo == null) throw new NullPointerException("first argument to size() is null");
         if (hi == null) throw new NullPointerException("second argument to size() is null");

         if (lo.compareTo(hi) > 0) return 0;
         if (contains(hi)) return rank(hi) - rank(lo) + 1;
         else              return rank(hi) - rank(lo);
     }

    
    
    
    
    
   	
    /***************************************************************************
     *  Node helper methods.
     ***************************************************************************/
    
    // is node x red; false if x is null ?
    private boolean isRed(Node x){
    	if(x==null) return false;
    	return x.colour==Red;
    }
	
   
    /***************************************************************************
     *  Red-black tree helper functions.
     ***************************************************************************/
	private Node rotateLeft(Node h){
		Node x=h.right;
		x.colour=h.colour;
		x.N=h.N;
		h.right=x.left;
		h.colour=Red;
		x.left=h;
		h.N=size(h.left)+1+size(h.right);
		return x;
	}
	
	private Node rotateRight(Node h){
		Node x=h.left;
		x.N=h.N;
		h.left=x.right;
		x.right=h;
		x.colour=h.colour;
		h.colour=Red;
		h.N=size(h.left)+1+size(h.right);
		return x;
	}
	
	private void flipColour(Node h){
		h.colour=Red;
		h.left.colour=Black;
		h.right.colour=Black;
	}
	
	 // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColour(h);
        if (isRed(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColour(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColour(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
            flipColour(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColour(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
	
	
	
	
	public static void main(String[] args) {
		RedBlackBST<String,Integer> rbbst=new RedBlackBST<String,Integer>();
		rbbst.put("E", 1);
		rbbst.put("D", 1);
		rbbst.put("Q", 1);
		rbbst.put("A", 1);
		rbbst.put("J", 1);
		rbbst.put("M", 1);
		rbbst.put("T", 1);
		rbbst.put("S", 1);
		System.out.println(rbbst.select(5));
		rbbst.delete(rbbst.select(5));
		System.out.println(rbbst.select(5));
		System.out.println(rbbst.keys());
	}
	
	

	
	
}
