package strings;

import queue.Queue;

public class TrieST<Value> {

	private static int R=256;
	private Node root=new Node();
	
	private static class Node{
		private Object val;
		private Node[] next=new Node[R];
	}
	
	public Value get(String key){
		Node x=get(root,key,0);
		if(x==null) return null;
		return (Value)x.val;
	}
	private Node get(Node x,String key,int d){
		if(x==null) return null;
		if(d==key.length())return x;//no next character to get
		char c=key.charAt(d);
		return get(x.next[c],key,d+1);
	}
	
	
	public void put(String key,Value val){
		root=put(root,key,val,0);
	}
	private Node put(Node x,String key,Value val,int d){
		//change value associated with key if in subtrie rooted at x
		if(x==null) x=new Node();
		if(d==key.length()){x.val=val;return x;}//no next character to add
		char c=key.charAt(d);//use dth key char to identify subtrie
		x.next[c]=put(x.next[c],key,val,d+1);
		return x;
		
	} 
	
	
	public Iterable<String> keys(){
		return keysWithPrefix("");
	}
	
	
	public Iterable<String> keysWithPrefix(String pre){
		Queue<String> q=new Queue<String>();
		collect(get(root,pre,0),pre,q);
		return q;
	}
	
	private void collect(Node x,String pre,Queue<String> q){
		if(x==null) return;
		if(x.val!=null) q.enqueue(pre);
		for(char c=0;c<R;c++){
			collect(x.next[c],pre+c,q);
		}
	}
	
	
	public Iterable<String> keysThatMatch(String pattern){
		Queue<String> q=new Queue<String>();
		collect(root,"",pattern,q);
		return q;
	}
	private void collect(Node x,String pre,String pattern,Queue<String> q){
		if(x==null) return;
		int d=pre.length();
		if(d==pattern.length() && x.val!=null) q.enqueue(pre);
		if(d==pattern.length())return;
		
		char next=pattern.charAt(d);
		for (char c = 0; c < R; c++) {
			if(next=='.' || c==next){
				collect(x.next[c],pre+c,pattern,q);
			}
		}
	}
	
	public String longestPrefixOf(String s){
		int length=search(root,s,0,0);
		return s.substring(0,length);
	}
	private int search(Node x,String s,int d,int length){
		if(x==null) return length;
		if(x.val!=null) length=d;
		if(d==s.length()) return length;
		char c=s.charAt(d);
		return search(x.next[c],s,d+1,length);
		
	}
	
	public void delete(String key){
		root=delete(root,key,0);
	}
	private Node delete(Node x,String key,int d){
		if(x==null) return null;
		if(d==key.length()) x.val=null;
		else{
			char c=key.charAt(d);
			x.next[c]=delete(x.next[c],key,d+1);
		}
		
		if(x.val!=null) return x;
		for(char c=0;c<R;c++){
			if(x.next[c]!=null) return x;
		}
		
		return null;
	}
	
	
}
