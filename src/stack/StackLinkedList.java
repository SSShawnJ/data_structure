package stack;

import java.util.Iterator;

public class StackLinkedList<Item> implements Iterable<Item> {
	private Node first;
	private int size;
	private class Node{
		Item item;
		Node next;
	}
	
	public Item pop(){
		Item item=first.item;
		first=first.next;
		size--;
		return item;
	}
	
	public void push(Item item){
		Node oldfirst=first;
		first=new Node();
		first.item=item;
		first.next=oldfirst;
		size++;
	}
	
	
	public Iterator<Item> iterator(){
		return new ReverseOrderIterator();
	}
	private class ReverseOrderIterator implements Iterator<Item>{
		private Node current=first;
		public boolean hasNext(){return current!=null;}
		public Item next(){
			Item item=current.item;
			current=current.next;
			return item;
		}
		public void remove(){}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
