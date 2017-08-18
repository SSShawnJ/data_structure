package dynamicprogramming;

public class BiDimensionalDP {

	public static void DP(int[][] apples){
		int N=apples.length;
		int M=apples[0].length;
		
		int[][] a=new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				a[i][j]=apples[i][j]+Math.max(A(i-1,j,a), A(i,j-1,a));
			}
		}
		
		System.out.println(a[N-1][M-1]);
	}
	
	
	public static int A(int i,int j,int[][] a){
		if(i<0 || j<0) return 0;
		return a[i][j];
	}
	
	
	/*
	 * 1
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		int[][] a=new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				a[i][j]=i+j+i;
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		DP(a);
				
	}
}
