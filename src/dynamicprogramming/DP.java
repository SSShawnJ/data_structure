package dynamicprogramming;

public class DP {

	
	public static int DPcoins(int[] coins, int sum){
		int[] array=new int[sum+1];	
		array[0]=0;
		for (int i = 1; i < array.length; i++) {
			array[i]=Integer.MAX_VALUE;
		}
		
		//bakc tracking
		int[] b=new int[sum+1];
		
		
		//DP
		for(int i=1;i<array.length;i++){
			for(int c:coins){
				int dif=i-c;
				
				if(dif>=0){
					if(array[dif]<Integer.MAX_VALUE && array[dif]+1<array[i]) array[i]=array[dif]+1;
					b[i]=dif;
				}
				
			}
		}
		
		//print array
		for(int i=0;i<array.length;i++){
			System.out.print(i+":"+array[i]+" ");
		}
		System.out.println();
		
		//print back tarcking
		for(int i=b.length-1;i>0;i=b[i]){
			int j=b[i];
			System.out.print((i-j)+" ");
		}
		System.out.println();
		
		
		if(array[sum]>=Integer.MAX_VALUE)
			return -1;
		else 
			return array[sum];
		
	}
	
	
	public static int DPcoins2(int[] coins,int sum){
		int[] array=new int[sum+1];	
		array[0]=0;
		for (int i = 1; i < array.length; i++) {
			array[i]=Integer.MAX_VALUE;
		}
		
		//bakc tracking
				int[] b=new int[sum+1];
		
		for(int c:coins){
			for(int i=0;i<array.length;i++){
				if(i+c>=array.length) break;
				
				if((array[i]+1)<array[i+c] && array[i]<Integer.MAX_VALUE){
					array[i+c]=array[i]+1;
					b[i+c]=i;
				}
			}
		}
		
		//print array
				for(int i=0;i<array.length;i++){
					System.out.print(i+":"+array[i]+" ");
				}
				System.out.println();
				
				//print back tarcking
				for(int i=b.length-1;i>0;i=b[i]){
					int j=b[i];
					System.out.print((i-j)+" ");
				}
				System.out.println();
				
				
				if(array[sum]>=Integer.MAX_VALUE)
					return -1;
				else 
					return array[sum];
	}
	
	
	public static void main(String[] args) {
		int[] a={2,3,5};
		System.out.println(DPcoins2(a,11));
	}
}
