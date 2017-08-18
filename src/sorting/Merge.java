package sorting;

import java.util.Arrays;
import java.util.Comparator;

public class Merge {
	@SuppressWarnings("rawtypes")
	private static Comparable[] aux;
	private static Object[] auxO;
	
	//API Merge sort the Comparable Object[](stable)
	@SuppressWarnings("rawtypes")
	public static void Sort (Comparable a[]){
		aux=new Comparable[a.length];
		Sort(a,0,a.length-1);
	}
	
	//API Merge sort the object[] using Comparator(stable)
	@SuppressWarnings("rawtypes")
	public static void Sort (Object a[],Comparator c){
		auxO=new Object[a.length];
		Sort(a,0,a.length-1,c);
	}
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void Sort(Object a[],int lo,int hi,Comparator c){
		if (lo>=hi){return;}
		int mid=(lo+hi)/2;
		Sort(a,lo,mid,c);
		Sort(a,mid+1,hi,c);
		//Improvement:
		//Eliminate the copy to the auxiliary array if necessary
		//when a[lo,hi] is already sorted.
		if (c.compare(a[mid],a[mid+1])<=0) return;
		merge(a,lo,mid,hi,c);
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void Sort(Comparable a[],int lo,int hi){
		if (lo>=hi){return;}
		int mid=(lo+hi)/2;
		Sort(a,lo,mid);
		Sort(a,mid+1,hi);
		//Improvement:
		//Eliminate the copy to the auxiliary array if necessary
		//when a[lo,hi] is already sorted.
		if (a[mid].compareTo(a[mid+1])<=0) return;
		merge(a,lo,mid,hi);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void merge(Comparable a[],int lo,int mid,int hi){
		for (int i= lo;i<=hi;i++){
			aux[i]=a[i];
		}
		int j=lo;
		int k=mid+1;
		for(int i=lo;i<=hi;i++){
			if(j>mid){  
				a[i]=aux[k];
				k++;
			}
			else if (k>hi){
				a[i]=aux[j];
				j++;
			}
			else if(aux[j].compareTo(aux[k])<=0){//in-place sort. Changing to "<" becomes reverse order
				a[i]=aux[j];
				j++;
			}
			else {
				a[i]=aux[k];
				k++;
			}
			
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void merge(Object a[],int lo,int mid,int hi,Comparator c){
		for (int i= lo;i<=hi;i++){
			auxO[i]=a[i];
		}
		int j=lo;
		int k=mid+1;
		for(int i=lo;i<=hi;i++){
			if(j>mid){  
				a[i]=auxO[k];
				k++;
			}
			else if (k>hi){
				a[i]=auxO[j];
				j++;
			}
			else if(c.compare(auxO[j],auxO[k])<=0){ //in-place sort. Changing to "<" becomes reverse order
				a[i]=auxO[j];
				j++;
			}
			else {
				a[i]=auxO[k];
				k++;
			}
			
		}
		
	}
	
	
	
	
	public static void main(String[] args) {
		Double[] x={1.0,3.0,7.0,2.0};
		Merge.Sort(x);
		System.out.println(Arrays.toString(x));
		

	}

}
