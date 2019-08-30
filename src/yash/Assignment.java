package yash;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Assignment {

	// Vaidation function if Tags is not in (Tag1 Tag2 Tag3 Tag4 Tag5 Tag6)

	public static boolean validateTags(String searchStr,List<Quiz> aList) throws Exception
	{	
		//searching elements for neg test cases 
	    	
		boolean found = false;
		Iterator<Quiz>  iter = aList.iterator();
		Quiz curItem=null;
		int pos=0;
		 
		while ( iter.hasNext())
		{
		    pos=pos+1;
		    curItem =(Quiz) iter.next();
		   /* System.out.println(" "+searchStr);
		    System.out.println("   "+curItem.getTag().trim());*/
		    if (searchStr.contains(curItem.getTag().trim())) {
		    found = true;
		    //break;
		    }
		    else 
		    {    
		    	 found = false;
		    	// System.out.println("searchStr: "+curItem.getDifficultylevel());
		    	// System.out.println(" Invalid tag entered: "+curItem.getTag());
		    	 throw new Exception("INVALID TAGS IN THE FILES");
				  
		    }
		 
		}
		
	
		
		return true;
	}

	
	// Vaidation function if DifficultyLevel is not in (HARD MEDIUM EASY)
	public static boolean ValidateDifficultyLevel(String difflevel, List<Quiz> aList) throws Exception {
		
	    	
		boolean found = false;
		Iterator<Quiz>  iter = aList.iterator();
		Quiz curItem=null;
		int pos=0;
		 
		while ( iter.hasNext())
		{
		    pos=pos+1;
		    curItem =(Quiz) iter.next();
		    /*System.out.println(" "+difflevel);
		    System.out.println("   "+curItem.getDifficultylevel().trim());*/
		    if (difflevel.contains(curItem.getDifficultylevel().trim())) {
		    found = true;
		    //break;
		    }
		    else 
		    {    
		    	 found = false;
		    	// System.out.println("searchStr: "+curItem.getDifficultylevel());
		    	// System.out.println(" Invalid tag entered: "+curItem.getTag());
		    	 throw new Exception("INVALID DIFFICULTY LEVEL IN INPUT FILE IS NOT IN ** EASY MEDIUM HARD **");
				  
		    }
		 
		}
		
		return true;
	
		
	}
	public static void main(String[] args) throws Exception {

		try (FileReader reader = new FileReader("C:\\e-GMAT\\QUESTIONS.txt ");  //testdata.txt   ///testdataSuccess.txt
				BufferedReader br = new BufferedReader(reader)) {

			Quiz quiz = null;

			List<Quiz> quizObjList = new ArrayList<>();

			String line;
			while ((line = br.readLine()) != null) {
				quiz = new Quiz();
				String lineSplit[] = line.split("\\|");
			
				quiz.setQuestion(lineSplit[0]);
				quiz.setDifficultylevel(lineSplit[1]);
				quiz.setTag(lineSplit[2]);

				quizObjList.add(quiz);
				// break;
			}
		
			HashMap<Quiz, Integer> m = new HashMap<>();

			int count1 = 0, count2 = 0, count3 = 0;
			int count[] = new int[18];
			
			String tags= " Tag1"+"Tag2"+"Tag3"+"Tag4"+"Tag5"+"Tag6 ";
			String difflevel = "EASYMEDIUMHARD";
			int errcount=0 ;
			
			
			try {
			validateTags(tags,quizObjList);
			
			
			ValidateDifficultyLevel(difflevel ,quizObjList);
			
			
			
			for (Quiz q : quizObjList) {
				
					
				if (q.getTag().equalsIgnoreCase("Tag1") && q.getDifficultylevel().equalsIgnoreCase("EASY")) {

					count[0]++;
					// count1++;
					m.put(q, count[0]);
				}
				if (q.getTag().equalsIgnoreCase("Tag1") && q.getDifficultylevel().equalsIgnoreCase("MEDIUM")) {
					count[1]++;
					// count2++;
					m.put(q, count[1]);
				}
				if (q.getTag().equalsIgnoreCase("Tag1") && q.getDifficultylevel().equalsIgnoreCase("HARD")) {
					count[2]++;
					m.put(q, count[2]);
				}
				if (q.getTag().equalsIgnoreCase("Tag2") && q.getDifficultylevel().equalsIgnoreCase("EASY")) {
					count[3]++;
					m.put(q, count[3]);
				}
				if (q.getTag().equalsIgnoreCase("Tag2") && q.getDifficultylevel().equalsIgnoreCase("MEDIUM")) {
					count[4]++;
					m.put(q, count[4]);
				}
				if (q.getTag().equalsIgnoreCase("Tag2") && q.getDifficultylevel().equalsIgnoreCase("HARD")) {
					count[5]++;
					m.put(q, count[5]);
				}
				if (q.getTag().equalsIgnoreCase("Tag3") && q.getDifficultylevel().equalsIgnoreCase("EASY")) {
					count[6]++;
					m.put(q, count[6]);
				}
				if (q.getTag().equalsIgnoreCase("Tag3") && q.getDifficultylevel().equalsIgnoreCase("MEDIUM")) {
					count[7]++;
					m.put(q, count[7]);
				}
				if (q.getTag().equalsIgnoreCase("Tag3") && q.getDifficultylevel().equalsIgnoreCase("HARD")) {
					count[8]++;
					m.put(q, count[8]);
				}
				if (q.getTag().equalsIgnoreCase("Tag4") && q.getDifficultylevel().equalsIgnoreCase("EASY")) {
					count[9]++;
					m.put(q, count[9]);
				}
				if (q.getTag().equalsIgnoreCase("Tag4") && q.getDifficultylevel().equalsIgnoreCase("MEDIUM")) {
					count[10]++;
					m.put(q, count[10]);
				}
				if (q.getTag().equalsIgnoreCase("Tag4") && q.getDifficultylevel().equalsIgnoreCase("HARD")) {
					count[11]++;
					m.put(q, count[11]);
				}
				if (q.getTag().equalsIgnoreCase("Tag5") && q.getDifficultylevel().equalsIgnoreCase("EASY")) {
					count[12]++;
					m.put(q, count[12]);
				}
				if (q.getTag().equalsIgnoreCase("Tag5") && q.getDifficultylevel().equalsIgnoreCase("MEDIUM")) {
					count[13]++;
					m.put(q, count[13]);
				}
				if (q.getTag().equalsIgnoreCase("Tag5") && q.getDifficultylevel().equalsIgnoreCase("HARD")) {
				//	System.out.println("22222222 ");
					count[14]++;
					m.put(q, count[14]);
				}
				if (q.getTag().equalsIgnoreCase("Tag6") && q.getDifficultylevel().equalsIgnoreCase("EASY")) {
					
					count[15]++;
					
					m.put(q, count[15]);
				}
				if (q.getTag().equalsIgnoreCase("Tag6") && q.getDifficultylevel().equalsIgnoreCase("MEDIUM")) {
					count[16]++;
					m.put(q, count[16]);
				}   // q.getDifficultylevel().equalsIgnoreCaseIgnoreCase(anotherString)
				if (q.getTag().equalsIgnoreCase("Tag6") && q.getDifficultylevel().equalsIgnoreCase("HARD")) {
					count[17]++;
					m.put(q, count[17]);
				}

			}
			// System.out.println("count1 "+m.size());

			Iterator<Entry<Quiz, Integer>> entryIterator = m.entrySet().iterator();
			/*
			 * Quiz key = new Quiz(); key.setDifficultylevel("HARD"); key.setTag("Tag1");
			 */
	/*	System.out.println("Value for HARD and Tag1 is : "+m);
		System.out.println("size"+m.size());*/
		
			int min=Integer.MAX_VALUE;
			int case1ques=0 , remainingQuestionCount=0 ,  case1count=0;
			int  min_sec=Integer.MAX_VALUE;
			while (entryIterator.hasNext()) {
				Entry<Quiz, Integer> entry = entryIterator.next();

				System.out.println("The key is :: " + entry.getKey().getDifficultylevel() + "::"
						+ entry.getKey().getTag() + ", and value is :: " + entry.getValue());
				
				// if()
				// hashmap have key<Diffcultylevel,Tag> and their count
				
				//calculation for 33 questions
				
				//	min_count of (EasyTag1,EasyTag2,MediumTag3,MediumTag4,HardTag5,HardTag6,EasyTag3,EasyTag4,EasyTag5,EasyTag6)
				
			//---------------------------------------------case1 -----------------------------------------------------------------------------------	
				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("Easy")&& entry.getKey().getTag().equalsIgnoreCase("Tag1"))
				{
				 if(min>entry.getValue())
				    min = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("Easy")&& entry.getKey().getTag().equalsIgnoreCase("Tag2"))
				{
					
				 	
				 if(min>entry.getValue())
				 min = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("MEDIUM")&& entry.getKey().getTag().equalsIgnoreCase("Tag3"))
				{
				 if(min>entry.getValue())
				 min = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("MEDIUM")&& entry.getKey().getTag().equalsIgnoreCase("Tag4"))
				{
				 if(min>entry.getValue())
				 min = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("HARD")&& entry.getKey().getTag().equalsIgnoreCase("Tag5"))
				{
				 if(min>entry.getValue())
				 min = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("HARD")&& entry.getKey().getTag().equalsIgnoreCase("Tag6"))
				{
				 if(min>entry.getValue())
				 min = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("Easy")&& entry.getKey().getTag().equalsIgnoreCase("Tag3"))
				{
				 if(min>entry.getValue())
				 min = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("Easy")&& entry.getKey().getTag().equalsIgnoreCase("Tag4"))
				{
				 if(min>entry.getValue())
				 min = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("MEDIUM")&& entry.getKey().getTag().equalsIgnoreCase("Tag5"))
				{
				 if(min>entry.getValue())
				 min = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("MEDIUM")&& entry.getKey().getTag().equalsIgnoreCase("Tag6"))
				{
				 if(min>entry.getValue())
				 min = entry.getValue();
				}
				
			 //   System.out.println("Minimum value of min after "+" :  "+min); ------------------------
				// 
			  //---------------------------------------------case1 -----------------------------------------------------------------------------------			
				// 22 min * 10 will be paper set 
				
				// remainmin
				
				//System.out.println("Case 1 paper count: "+min*10);   /// fix count 
				
				/// starting the second case 
				
			   
				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("Easy")&& entry.getKey().getTag().equalsIgnoreCase("Tag5"))
				{
			/*	System.out.println(min_sec);	
				System.out.println(entry.getValue());*/	
				 if(min_sec>entry.getValue())
					min_sec = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("Easy")&& entry.getKey().getTag().equalsIgnoreCase("Tag6"))
				{
				 if(min_sec>entry.getValue())
					 min_sec = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("MEDIUM")&& entry.getKey().getTag().equalsIgnoreCase("Tag1"))
				{
				 if(min_sec>entry.getValue())
					 min_sec = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("MEDIUM")&& entry.getKey().getTag().equalsIgnoreCase("Tag2"))
				{
				 if(min_sec>entry.getValue())
					 min_sec = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("HARD")&& entry.getKey().getTag().equalsIgnoreCase("Tag3"))
				{
				 if(min_sec>entry.getValue())
					 min_sec = entry.getValue();
				}

				if(entry.getKey().getDifficultylevel().equalsIgnoreCase("HARD")&& entry.getKey().getTag().equalsIgnoreCase("Tag4"))
				{
				 if(min_sec>entry.getValue())
					 min_sec = entry.getValue();
				}

				//System.out.println("min_sec      "+min_sec);
				
			//	 System.out.println("Minimum value of min_sec after "+" :  "+min_sec);
					// 
				
			}
			case1count = min;
		    remainingQuestionCount = 330 - case1count;// case1ques = ;
				
		    System.out.println("case 1 paper count is : "+case1count);	 
			
			int case2papercount = min_sec-6;  // six possibilities
			
			System.out.println("Case 2 paper count: "+case2papercount); 
			
		/*	int diffcase2 = 270 - case2papercount ;
			
			System.out.println("Diff in paper_count "+diffcase2); */
			int paperCount = 0;
			if(case2papercount<0 && case1count >0)
				paperCount = case1count;
			else if (case1count<0 && case2papercount>0)
				paperCount = case2papercount;
			else
				paperCount = case1count +case2papercount ;
			
			System.out.println("");
			
			if(paperCount<0)
				System.out.println("NOT POSSIBLE TO GENERATE PAPER DUE TO INSUFFICIENT DATA **ACCURATE NUMBER OF TAGS OR DIFFICULTY LEVEL MISSING** ");
			else
			System.out.println("------------------The Maximum number of papers can be : " + paperCount);
			}
			catch (Exception e) {
				// TODO: handle exception
				System.err.format("Exception: %s%n", e.getMessage());
				e.getMessage();
			} 
			
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

}