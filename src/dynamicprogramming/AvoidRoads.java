package dynamicprogramming;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * In the city, roads are arranged in a grid pattern.Each point on the grid represents a corner where two blocks meet.
 * The points are connected by line segments which represent the various street blocks. 
 * Using the cartesian coordinate system, we can assign a pair of integers to each corner as shown below. 
 * 
 * You are standing at the corner with coordinates 0,0. Your destination is at corner width,height.
 *  You will return the number of distinct paths that lead to your destination. Each path must use exactly width+height blocks. 
 *  In addition, the city has declared certain street blocks untraversable. These blocks may not be a part of any path. 
 *  You will be given a String[] bad describing which blocks are bad. 
 *  If (quotes for clarity) "a b c d" is an element of bad, it means the block from corner a,b to corner c,d is untraversable. 
 *  For example, let's say
 *  width  = 6
 *  length = 6
 *  bad = {"0 0 0 1","6 6 5 6"}
 *  The picture below shows the grid, with untraversable blocks darkened in black. A sample path has been highlighted in red.
 */
public class AvoidRoads {

	private static class Point{
		int i;
		int j;
		
		public Point(int i,int j){
			this.i=i;
			this.j=j;
		}
	}
	
	public static void AR(){
		Scanner x= new Scanner(System.in);
		int s=x.nextInt();
		String initial=x.next();
		String last=x.next();
		initial=initial.substring(1, initial.length()-1);
		last=last.substring(1, last.length()-1);

		String[] a=initial.split(",");
		String[] b=last.split(",");
		
		
		Point start=new Point(Integer.parseInt(a[0]),Integer.parseInt(a[1]));
		Point end=new Point(Integer.parseInt(b[0]),Integer.parseInt(b[1]));
		int num=x.nextInt();
		x.close();
		
		Queue<Point> thisLoop=new LinkedList<>();
		Queue<Point> nextLoop=new LinkedList<>();
		
		long[][] board=new long[s][s];
		for(int i=0;i<board.length;i++){
			for (int j = 0; j < board[0].length; j++) {
				board[i][j]=Long.MAX_VALUE;
			}
		}
		System.out.println(board[0][0]);
		
		board[start.i][start.j]=1;
		thisLoop.offer(start);
		
		//main
		
		for(int i=0;i<num;i++){
			while(!thisLoop.isEmpty()){
				Point p=thisLoop.poll();
				Process(p,nextLoop,board);
			}
			
			thisLoop=nextLoop;
			nextLoop=new LinkedList<Point>();
		}
		
		System.out.println(board[end.i][end.j]);
	}
	
	public static void Process(Point p,Queue<Point> next,long[][] board){
		
		
		int s=board.length;
		int i=p.i;
		int j=p.j;
		System.out.println(board[i][j]);
		if((i-1)>=0){
			if(i-1==s && j==s) 
			
			if( board[i-1][j]==Long.MAX_VALUE) 
				board[i-1][j]=board[i][j];
			else
				board[i-1][j]+=board[i][j];
			next.offer(new Point(i-1,j));
		}
		
		if((i+1)<s){
			if( board[i+1][j]==Long.MAX_VALUE) 
				board[i+1][j]=board[i][j];
			else
				board[i+1][j]+=board[i][j];
			next.offer(new Point(i+1,j));
		}
		if((j-1)>=0){
			if( board[i][j-1]==Long.MAX_VALUE) 
				board[i][j-1]=board[i][j];
			else
				board[i][j-1]+=board[i][j];
			next.offer(new Point(i,j+1));
		}
		if((j+1)<s){
			if( board[i][j+1]==Long.MAX_VALUE) 
				board[i][j+1]=board[i][j];
			else
				board[i][j+1]+=board[i][j];
			next.offer(new Point(i,j+1));
		}
		if((i-1)>=0 && (j-1)>=0){
			if( board[i-1][j-1]==Long.MAX_VALUE) 
				board[i-1][j-1]=board[i][j];
			else
				board[i-1][j-1]+=board[i][j];
			next.offer(new Point(i-1,j-1));
		}
		if((i-1)>=0 && (j+1)<s){
			if( board[i-1][j+1]==Long.MAX_VALUE) 
				board[i-1][j+1]=board[i][j];
			else
				board[i-1][j+1]+=board[i][j];
			next.offer(new Point(i-1,j+1));
		}
		if((i+1)<s && (j-1)>=0){
			if( board[i+1][j-1]==Long.MAX_VALUE) 
				board[i+1][j-1]=board[i][j];
			else
				board[i+1][j-1]+=board[i][j];
			next.offer(new Point(i+1,j-1));
		}
		if((i+1)<s && (j+1)<s){
			if( board[i+1][j+1]==Long.MAX_VALUE) 
				board[i+1][j+1]=board[i][j];
			else
				board[i+1][j+1]+=board[i][j];
			next.offer(new Point(i+1,j+1));
		}
		if((i-1)>=0 && (j+2)<s){
			if( board[i-1][j+2]==Long.MAX_VALUE) 
				board[i-1][j+2]=board[i][j];
			else
				board[i-1][j+2]+=board[i][j];
			next.offer(new Point(i-1,j+2));
		}
		if((i-1)>=0 && (j-2)>=0){
			if( board[i-1][j-2]==Long.MAX_VALUE) 
				board[i-1][j-2]=board[i][j];
			else
				board[i-1][j-2]+=board[i][j];
			next.offer(new Point(i-1,j-2));
		}
		if((i-2)>=0 && (j+1)<s){
			if( board[i-2][j+1]==Long.MAX_VALUE) 
				board[i-2][j+1]=board[i][j];
			else
				board[i-2][j+1]+=board[i][j];
			next.offer(new Point(i-2,j+1));
		}
		if((i-2)>=0 && (j-1)>=0){
			if( board[i-2][j-1]==Long.MAX_VALUE) 
				board[i-2][j-1]=board[i][j];
			else
				board[i-2][j-1]+=board[i][j];
			next.offer(new Point(i-2,j-1));
		}
		if((i+1)<s && (j-2)>=0){
			if( board[i+1][j-2]==Long.MAX_VALUE) 
				board[i+1][j-2]=board[i][j];
			else
				board[i+1][j-2]+=board[i][j];
			next.offer(new Point(i+1,j-2));
		}
		if((i+1)<s && (j+2)<s){
			if( board[i+1][j+2]==Long.MAX_VALUE) 
				board[i+1][j+2]=board[i][j];
			else
				board[i+1][j+2]+=board[i][j];
			next.offer(new Point(i+1,j+2));
		}
		if((i+2)<s && (j-1)>=0){
			if( board[i+2][j-1]==Long.MAX_VALUE) 
				board[i+2][j-1]=board[i][j];
			else
				board[i+2][j-1]+=board[i][j];
			next.offer(new Point(i+2,j-1));
		}
		if((i+2)<s && (j+1)<s){
			if( board[i+2][j+1]==Long.MAX_VALUE) 
				board[i+2][j+1]=board[i][j];
			else
				board[i+2][j+1]+=board[i][j];
			next.offer(new Point(i+2,j+1));
		}
		
		
	}
	
	public static void main(String[] args) {
		AR();
	}
}
