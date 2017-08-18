package sorting;

import java.util.Arrays;
import java.util.Comparator;

public class Insertion {
	
	//API  insertion sort using Comparator(stable)
	@SuppressWarnings("rawtypes")
	public static void Sort(Object[] a,Comparator c){
		int N=a.length;
		for(int i=0;i<N;i++){
			for(int j=i;j>0 && less(c,a[j],a[j-1]);j--){
				swap(a,j,j-1);
			}
		}
	}
	
	//API insertion sort Comparable Object[](stable)
	@SuppressWarnings("rawtypes")
	public static void Sort(Comparable[] a){
		int N=a.length;
		for(int i=0;i<N;i++){
			for(int j=i;j>0 && less(a[j],a[j-1]);j--){
				swap(a,j,j-1);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparator c,Object a,Object b){
		return c.compare(a,b)<0;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparable a,Comparable b){
		return a.compareTo(b)<0;
	}
	
	private static void swap(Object[] a,int i,int j){
		Object temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	
	
	//insertion sort for string  whose first d digits are equal
	public static void Sort(String[] a,int lo,int hi,int d){
		for(int i=lo;i<=hi;i++){
			for(int j=i;j>lo && less(a[j],a[j-1],d);j-- ){
				swap(a,j,j-1);
			}
		}
	}
	private static boolean less(String v,String w,int d){
		for(int i=d;i<Math.min(v.length(), w.length());i++){
			if(v.substring(d).compareTo(w.substring(d))<0) return true;
			else if(v.substring(d).compareTo(w.substring(d))>0) return false;
		}
		return v.length()<w.length();
	}

	public static void main(String[] args) {
		Double[] x={1.0,3.0,7.0,2.0};
		Insertion.Sort(x);
		System.out.println(Arrays.toString(x));

	}

}
