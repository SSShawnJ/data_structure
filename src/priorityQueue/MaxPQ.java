package priorityQueue;

public class MaxPQ<Key extends Comparable<Key>> {
	@SuppressWarnings("unchecked")
	private Key[] pq=(Key[])new Comparable[2];
	private int N=0;
	
	public boolean isEmpty(){return N==0;}
	
	public int size(){return N;}
	
	public void insert(Key v){
		if(pq.length==(N+1)) {resize((N+1)*2);}
		pq[++N]=v;
		swim(N);
	}
	
	public Key delMax(){
		Key max=pq[1];
		exch(1,N--);
		pq[N+1]=null;
		sink(1);
		if(N>0 && N==pq.length/4){resize(pq.length/2);}
		return max;
	}
	
	public boolean less(int i,int j){
		return pq[i].compareTo(pq[j])<0;
	}
	
	public void exch(int i,int j){
		Key temp=pq[i];
		pq[i]=pq[j];
		pq[j]=temp;
	}
	
	
	public void swim(int i){
		while(i>1 && less(i/2,i)){
			exch(i/2,i);
			i=i/2;
		}	
	}
	
	public void sink(int i){
		while(2*i<=N){
			int j=2*i;
			if (j<N && less(j,j+1)){j++;}
			if (less(j,i)){break;}
			exch(j,i);
			i=j;  //i=i*2
		}
	}
	
	private void resize(int max){
		@SuppressWarnings("unchecked")
		Key[] temp=(Key[])new Comparable[max+1];
		for(int i=0;i<=N;i++){
			temp[i]=pq[i];
		}
		pq=temp;
		
	}

	public static void main(String[] args) {
		MaxPQ<String> x=new MaxPQ<String>();
		x.insert("h");
		x.insert("g");
		x.insert("l");
	    x.insert("d");
		x.insert("u");
		x.insert("q");
		x.insert("h");
		x.insert("g");
		x.insert("l");
	    x.insert("d");
		x.insert("u");
		x.insert("q");
		System.out.println(x.delMax());
		System.out.println(x.delMax());
		System.out.println(x.delMax());
		System.out.println(x.delMax());
		System.out.println(x.delMax());
		System.out.println(x.delMax());
		System.out.println(x.delMax());
		System.out.println(x.delMax());
		System.out.println(x.delMax());
		System.out.println(x.delMax());
		System.out.println(x.delMax());
		System.out.println(x.delMax());	

	}

}
