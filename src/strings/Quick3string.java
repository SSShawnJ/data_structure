package strings;

import sorting.Insertion;

public class Quick3string {

	private static final int CUTOFF =  15;   // cutoff to insertion sort
	
	public static void sort(String[] a){
		sort(a,0,a.length-1,0);
	}
	
	private static void sort(String[] a,int lo,int hi,int d){
		if(lo>=hi)return;
		else if (hi <= lo + CUTOFF) {
            Insertion.Sort(a, lo, hi, d);
            return;
        }
		
		int lt=lo, gt=hi;
		int v=charAt(a[lo],d);
		int i=lo+1;
		
		while(i<=gt){
			int t=charAt(a[i],d);
			if(t<v) swap(a,lt++,i++);
			else if(t>v) swap(a,gt--,i);
			else i++;
		}
		
		sort(a,lo,lt-1,d);
		if(v>0) sort(a,lt,gt,d+1);
		sort(a,gt+1,hi,d);
		
	}
	
	
	private static void swap(String[] a,int lo, int high){
		String temp=a[lo];
		a[lo]=a[high];
		a[high]=temp;
	}
	
	
	
	private static int charAt(String s,int d){
		if(d>=s.length()) return -1;
		else return s.charAt(d);
	}
	
	
}
