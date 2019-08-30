package assign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Condtion {
	Scanner scr= new Scanner(System.in);
	ArrayList aa= new ArrayList();
	public <List> ArrayList added() {
		
		int n=1;
		while(n<=600) {
			System.out.println("enter the question" );
			String question=scr.nextLine();
			System.out.println("Enter any of six  tag");
			String tag=scr.nextLine();
			System.out.println("Enter any difficulty level");
			String difficulty=scr.nextLine();
			aa.add(question);
			 aa.add(tag);
			 aa.add(difficulty);
			 ++n;
			
		}
		 return aa;
		 
		 
	}
	
	

}
