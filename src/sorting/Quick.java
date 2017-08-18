package sorting;

import java.util.Comparator;
import java.util.Random;

import stopwatch.Stopwatch;


public class Quick {

	//API:Quick Sort Comparable[](unstable)
	@SuppressWarnings("rawtypes")
	public static void Sort(Comparable[] a) {
		if(a.length<=10){
			Insertion.Sort(a);
			return;
		}
		Sort(a, 0, a.length - 1);
	}
	
	//API:Quick Sort Object[] using Comparator(unstable)
	@SuppressWarnings("rawtypes")
	public static void Sort(Object[] a,Comparator c) {
		if(a.length<=10){
			Insertion.Sort(a,c);
			return;
		}
		Sort(a, 0, a.length - 1,c);
	}

	@SuppressWarnings("rawtypes")
	private static void Sort(Comparable[] a, int lo, int high) {
		if(lo>=high) return;
		int index = partition(a, lo, high);
		//if(lo<index-1)
		Sort(a, lo, index-1);
		//if(index<high)
		Sort(a, index+1, high);

	}
	
	@SuppressWarnings("rawtypes")
	private static void Sort(Object[] a, int lo, int high,Comparator c) {
		if(lo>=high) return;
		int index = partition(a, lo, high,c);
		//if(lo<index-1)
		Sort(a, lo, index-1,c);
		//if(index<high)
		Sort(a, index, high,c);

	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static int partition(Comparable[] a, int lo, int high) {
		Comparable pivot = a[lo];
		int i=lo;
		int j=high+1;
		while (true) {
			while (a[++i].compareTo(pivot)<0){
				if(i>=high) break;
			}
			while (a[--j].compareTo(pivot)>0){
				if(j<=lo) break;
			}
			if (i >= j) break;
			swap(a,i,j);
		}
		swap(a,lo,j);
		return j;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static int partition(Object[] a, int lo, int high,Comparator c) {
		Random x = new Random();
		Object pivot = a[lo+x.nextInt(high-lo+1)];
		while (lo <= high) {
			while (c.compare(a[lo],pivot)<0){
				lo++;
			    if(lo>=high) break;
			}
			while (c.compare(a[high],pivot)>0){
				high--;
				if(high<=lo)break;
			}
			if (lo <= high) {
				swap(a,lo, high);
				lo++;
				high--;
			}
		}
		return lo;
	}
	
	
	@SuppressWarnings("rawtypes")
	private static void swap(Comparable[] a,int lo, int high){
		Comparable temp=a[lo];
		a[lo]=a[high];
		a[high]=temp;
	}
	
	private static void swap(Object[] a,int lo, int high){
		Object temp=a[lo];
		a[lo]=a[high];
		a[high]=temp;
	}

	private static void printArray(Object a[]){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+",");
		}
		System.out.println();
	}
	
	
	
	public static void main(String[] args) {
		String[] a={"2","3","4","62","1","3","9","5","7","12","32"};
		printArray(a);
		Stopwatch c=new Stopwatch();
		Quick.Sort(a);
		System.out.println(c.elapsedTime());
		printArray(a);

	}

}
