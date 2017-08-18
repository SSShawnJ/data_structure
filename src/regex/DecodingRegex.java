package regex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DecodingRegex {


	public static void main(String[] args) throws FileNotFoundException {
		Scanner x;
		x = new Scanner(new File("lala.txt"));
		String regex="^((\\\\[0-9A-F][0-9A-Z])|[A-Z0-9])*$";
		
		
		while(x.hasNext()){
			String input=x.next();
			if(input.matches(regex)){
				char[] c=input.toCharArray();
				for(int i=0;i<c.length;i++){
					char s=c[i];
					if(s=='\\'){
						int n=getvalue(c[++i]);
						i++;
						for(int j=0;j<n;j++){
							System.out.print(c[i]);
						}
					}
					else
					System.out.print(c[i]);
				}
				System.out.println();
				
				
			}
			else{
				System.out.println("CORRUPTED");
			}
			
			
		}
		x.close();
		System.out.println("Finish");
		
	}
	
	public static int getvalue(char c){
		if(c=='A')
			return 13;
		else if(c=='B')
			return 14;
		else if(c=='C')
			return 15;
		else if(c=='D')
			return 16;
		else if(c=='E')
			return 17;
		else if(c=='F')
			return 18;
		else if(c=='1')
			return 4;
		else if(c=='2')
			return 5;
		else if(c=='3')
			return 6;
		else if(c=='4')
			return 7;
		else if(c=='5')
			return 8;
		else if(c=='6')
			return 9;
		else if(c=='7')
			return 10;
		else if(c=='8')
			return 11;
		else if(c=='9')
			return 12;
		else if(c=='0')
			return 3;
		else
			return 0;
		
		

	}
}
