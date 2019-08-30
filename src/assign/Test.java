package assign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Test {

	
	public static void main(String[] args) {
		List<Object> ar=new ArrayList<Object>();
		Condtion c=new  Condtion();
		ar=c.added();
		System.out.println(ar);
		// criteria to generate quiz
		int i=1;
		int n=600;
		int k=0;
		int z=2;
		int counter=0;
		while(n>0) {
				if(ar.get(i).equals("tag1")||ar.get(i).equals("tag2")||ar.get(i).equals("tag3")
					||ar.get(i).equals("tag4")||ar.get(i).equals("tag5")||ar.get(i).equals("tag6")&&
					(ar.get(z).equals("easy")||ar.get(z).equals("hard"))&&
					(ar.get(z).equals("easy")||ar.get(z).equals("medium"))&&
					(ar.get(z).equals("hard")||ar.get(z).equals("medium")))
						{
					System.out.println(ar.get(k));
					++counter;
				}
				
			k=k+3;
			--n;
			i=i+3;
			z=z+3;
			if(counter==10) {
				n=n-10;
				break;
			}
				
			
		}
		
		
        
	}

}
