package linkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<Item> implements Iterable<Item>
{
	private Node<Item> first;//pointer to the first item
	private int N;//number of items
	
	private class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	
	public LinkedList(){
		first=null;
		N=0;
	}
	
	public boolean isEmpty() {
        return first == null;
    }
	
	 public void add(Item item) {
	        Node<Item> oldfirst = first;
	        first = new Node<Item>();
	        first.item = item;
	        first.next = oldfirst;
	        N++;
	    }
	 
	 public int size(){return N;}
	 
	 
	 public Iterator<Item> iterator()  {
	        return new ListIterator<Item>(first);  
	    }

	    // an iterator, doesn't implement remove() since it's optional
	    private class ListIterator<Item> implements Iterator<Item> {
	        private Node<Item> current;

	        public ListIterator(Node<Item> first) {
	            current = first;
	        }

	        public boolean hasNext()  { return current != null;                     }
	        public void remove()      { throw new UnsupportedOperationException();  }

	        public Item next() {
	            if (!hasNext()) throw new NoSuchElementException();
	            Item item = current.item;
	            current = current.next; 
	            return item;
	        }
	    }
	    
	    public static void main(String[] args) {
			LinkedList<Integer> x=new LinkedList<Integer>();
			x.add(4);
			x.add(5);
			System.out.println(x.size());
		}

}
