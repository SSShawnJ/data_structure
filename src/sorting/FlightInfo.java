package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

public class FlightInfo {
	private final String flightNum;
	private final String depTime;
	private final String ariTime;
	private final String destination;
	
	public FlightInfo(String num,String arilocation,String depT,String ariT){
		flightNum=num;
		destination=arilocation;
		depTime=depT;
		ariTime=ariT;
		
	}
	
	//Comparator classes implement Comparator Interface
	public static class flightNumOrder implements Comparator<FlightInfo>{
		public int compare(FlightInfo a,FlightInfo b){
			return a.flightNum.compareTo(b.flightNum);
		}
	}
	
	public static class depTimeOrder implements Comparator<FlightInfo>{
		public int compare(FlightInfo a,FlightInfo b){
			return a.depTime.compareTo(b.depTime);
		}
	}
	
	public static class ariTimeOrder implements Comparator<FlightInfo>{
		public int compare(FlightInfo a,FlightInfo b){
			return a.ariTime.compareTo(b.ariTime);
		}
	}
	
	public  static class destinationOrder implements Comparator<FlightInfo>{
		public int compare(FlightInfo a,FlightInfo b){
			return a.destination.compareTo(b.destination);
		}
	}
	
	
	public String toString(){
		return String.format("%s  "+"%-11s"+"%s  "+"%s",this.flightNum,this.destination,this.depTime,this.ariTime);
		
	}
	
	
	
	public static void main(String[] args)throws FileNotFoundException {
		Scanner in=new Scanner(new File("FlightInfoInput.txt"));
		int N=in.nextInt();
		FlightInfo[] a=new FlightInfo[N];
		for(int i=0;i<N;i++){
			a[i]=new FlightInfo(in.next(),in.next(),in.next(),in.next());
		}
		in.close();
		for(FlightInfo i:a){
			System.out.println(i.toString());
		}
		System.out.println();

		Merge.Sort(a,new depTimeOrder());
		for(FlightInfo i:a){
			System.out.println(i.toString());
		}
		System.out.println();
		Merge.Sort(a,new destinationOrder());
		for(FlightInfo i:a){
			System.out.println(i.toString());
		}
	}

}
