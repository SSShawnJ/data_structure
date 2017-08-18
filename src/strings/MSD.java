package strings;

import sorting.Insertion;

public class MSD {

	private static int R=256;       //radix
	private static final int M=15;  //cutoff for small subarrays
	private static String[] aux;    //auxiliary array for distribution
	
	private static int charAt(String s,int d){
		if(d>=s.length()) return -1;
		else return s.charAt(d);
	}
	
	public static void sort(String[] a){
		int N=a.length;
		aux=new String[N];
		sort(a,0,N-1,0);
		
	}
	
	private static void sort(String[] a,int lo,int hi,int d){
		// cutoff to insertion sort for small subarrays
		if(hi<=lo+M){Insertion.Sort(a,lo,hi,d);return;}
		
		// compute frequency counts
		int[] count=new int[R+2];
		for (int i = 0; i < count.length; i++) {
			count[charAt(a[i],d)+2]++;
		}
		
		 // transform counts to indicies
		for (int i = 0; i < R+1; i++) {
			count[i+1]+=count[i];
		}
		
		// distribute
		for(int i=lo;i<=hi;i++){
			aux[count[charAt(a[i],d)+1]++]=a[i];
		}
		
		// copy back
		for(int i=lo;i<=hi;i++){
			a[i]=aux[i-lo];
		}
		
		// recursively sort for each character (excludes sentinel -1)
		for(int i=0;i<R;i++){
			sort(a,lo+count[i],lo+count[i+1]-1,d+1);
		}
	}
	
	
}
