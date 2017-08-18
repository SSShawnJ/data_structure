package symbol_table;

public class RBBST<Key extends Comparable<Key>,Value> {
	private static final boolean red=true;
	private static final boolean black=false;
	
	private Node root;
	
	private class Node{
		Key key;
		Value val;
		Node left,right;
		int N;
		boolean color;
		
		Node(Key key,Value val,int N,boolean color){
			this.key=key;
			this.val=val;
			this.N=N;
			this.color=color;
		}
	}
	
	private boolean isRed(Node x){
		if(x==null) return false;
		return x.color==red;
	}
	
	private Node rotateLeft(Node h){
		Node x=h.right;
		h.right=x.left;
		x.left=h;
		x.color=h.color;
		h.color=red;
		x.N=h.N;
		h.N=1+size(h.left)+size(h.right);
		return x;
	}
	
	private Node rotateRight(Node h){
		Node x=h.left;
		h.left=x.right;
		x.right=h;
		x.color=h.color;
		h.color=red;
		x.N=h.N;
		h.N=1+ size(h.left)+size(h.right);
		return x;
	}
	private void flipColors(Node h){
		h.color=red;
		h.left.color=black;
		h.right.color=black;
	}
	
	public int size(){
		return size(root);
	};
	private int size(Node x){
		if(x==null)return 0;
		return x.N;
	}
	
	
	public void put(Key key,Value val){
		root=put(root,key,val);
		root.color=black;
	}
	
	private Node put(Node h,Key key,Value val){
		if(h==null){return new Node(key,val,1,red);}
		
		int cmp=key.compareTo(h.key);
		if(cmp<0) h.left=put(h.left,key,val);
		else if(cmp>0)h.right=put(h.right,key,val);
		else h.val=val;
		
		if(isRed(h.right) && !isRed(h.left)) h=rotateLeft(h);
		if(isRed(h.right) && isRed(h.left.left)) h=rotateRight(h);
		if(isRed(h.right) && isRed(h.left))  flipColors(h);
		
		h.N=size(h.left)+1+size(h.right);
		return h;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
