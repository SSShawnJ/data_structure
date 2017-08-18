package regex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static void main(String[] args) {
		Matcher m;
		Scanner x=new Scanner(System.in);
        int N=x.nextInt();
        Pattern p=Pattern.compile("[\\w.]+@[a-z]+\\.com");
        
        m=p.matcher(x.nextLine());
        List<String> list=new ArrayList<>(N);
        
        
        Collections.sort(list);
        System.out.println(list);
		
//        String a=" ";
//        char[] c=a.toCharArray();
//        int l=c.length;
//        for(int i=0;i<l/2;i++){
//        	char x=c[l-i-1];
//        	c[l-i-1]=c[i];
//        	c[i]=x;
//        }	
//
//        System.out.println(new String(c));

		
	}
	
	
}
