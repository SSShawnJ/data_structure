package dynamicprogramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LIS {

	
	public static void printAllLIS(int[] A){
		 //Longest increasing sequence with this i
        int[] S=new int[A.length];
        for(int i=0;i<S.length;i++){
            S[i]=1;
        }
        
        
        //the previous value come to this i
        ArrayList<Set<Integer>> track=new ArrayList<>(A.length);
        for(int i=0;i<A.length;i++){
            track.add(i, new HashSet<Integer>());
        }
       
        
        int longest=1;
        
        for(int i=1;i<S.length;i++){
            for(int j=0;j<i;j++){
                if(A[i]>A[j] && S[j]+1>=S[i]) {
                    S[i]=S[j]+1;
                    if(S[i]==longest) {
                    	track.get(i).add(j);
                    }
                    else if(S[i]>longest){
                    	longest=S[i];
                    	track.get(i).clear();
                    	track.get(i).add(j);
                    }
                }
            }
        }
        
        for(int i=0;i<S.length;i++){
            System.out.print(track.get(i)+" ");
        }
        System.out.println();
        
        
        for(int i=S.length-1;i>=0;i--){
        	if(S[i]==longest){
        		String s="";
        		printLIS(track,A,i,s);
        	}
        	
        }
        
	}
	
	private static void printLIS(ArrayList<Set<Integer>> track,int[] A,int i,String s){
		s=A[i]+s;
		for(int n:track.get(i)){
			if(n==i || n==0){System.out.println(A[n]+s);}
			else{
			printLIS(track,A,n,s);
			}
		}
	}
 
	
	
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
/*
* 5
* 2
* 7
* 4
* 3
* 8
*/
        
        
        Scanner x=new Scanner(System.in);
        int n=x.nextInt();
        
        int[] A=new int[n];
        for(int i=0;i<A.length;i++){
            A[i]=x.nextInt();
        }
        x.close();
        printAllLIS(A);
       
    }
	
}
