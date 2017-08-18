package dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

public class LCS {

	private String a;
	private String b;
	private int[][] dp;
	
	
	public  LCS(String a,String b){
		this.a=a;
		this.b=b;
		
		int n=a.length();
		int m=b.length();
		dp=new int[n+1][m+1];
		
		generateLCS(dp,a,b,n,m);
		
	}
	
	public int LCSLength(){
		return dp[a.length()][b.length()];
	}
	
	public String getLCS(){
		int n=a.length();
		int m=b.length();
		
		return getLCS(n,m);
	}
	
	private String getLCS(int n,int m){
		if(n==0 || m==0) return "";
		if(charAt(a,n)==charAt(b,m)) return getLCS(n-1,m-1)+charAt(a,n);
		
		if(dp[n-1][m] > dp[n][m-1]) return getLCS(n-1,m);
		else return getLCS(n,m-1);
	}
	
	public Set<String> getAllLCS(){
		Set<String> set=new HashSet<>();
		int n=a.length();
		int m=b.length();
		
		getAllLCS(set,"",n,m);
		
		return set;
	}
	
	private void getAllLCS(Set<String> set,String x,int n,int m){
		if(n==0 || m==0){
			set.add(x);
			return;
		}
		
		if(charAt(a,n)==charAt(b,m)){
			getAllLCS(set,charAt(a,n)+x,n-1,m-1);
		}
		
		if(dp[n-1][m] >= dp[n][m-1] && dp[n-1][m]==dp[n][m]) getAllLCS(set,x,n-1,m);
		if(dp[n][m-1]>=dp[n-1][m] && dp[n][m-1]==dp[n][m]) getAllLCS(set,x,n,m-1);
		
	}
	
	
	private static void generateLCS(int[][] dp,String a,String b,int n,int m){
		for(int i=1;i<=n;i++){
			for(int j=1;j<=m;j++){
				if(charAt(a,i)==charAt(b,j)) dp[i][j]=dp[i-1][j-1]+1;
				else
					dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		
		
		
	}
	
	
	private static char charAt(String x,int index){
		return x.charAt(index-1);
	}
	
	
	public static void main(String[] args) {
		LCS lcs=new LCS("GAC","AGCAT");
		System.out.println(lcs.LCSLength());
		System.out.println(lcs.getLCS());
		System.out.println(lcs.getAllLCS());
	}
	
	
	
	
	
	
}
