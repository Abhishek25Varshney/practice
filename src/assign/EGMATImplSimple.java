package assign;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EGMATImplSimple {
	
	//for total questions
	private static int totalQ;
	//for storing tag counts and difficulty level counts
	private static Map<String,Integer> tagCountStore, diffLevelCountStore;
	
	public static void main(String[] args){    
		
		File file = new File("C:\\e-GMAT\\QUESTIONS.txt"); 
		Scanner sc;
		tagCountStore = new HashMap<String, Integer>();		
		diffLevelCountStore = new HashMap<String, Integer>();
		addKeysToStore();
	    totalQ = 0;
		
	    try {
			sc = new Scanner(file);		
		    while (sc.hasNextLine()) { 
		    	//assuming that each line contains 1 question each
		    	String line= sc.nextLine().trim();
		    	if(!line.equals("")) {
		    		initializeCountsAndAddQuestions(line);	    		
		    	}	      
		    }
		    sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Question File is not present in the proper path. EXCEPTION:"+e);
		}  			
		//LOGIC START
	    String diffKey = Collections.min(diffLevelCountStore.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
		String tagKey = Collections.min(tagCountStore.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
		int minValue = diffLevelCountStore.get(diffKey)/2 < tagCountStore.get(tagKey) ? diffLevelCountStore.get(diffKey)/2 : tagCountStore.get(tagKey) ;				
		
		if((totalQ/10) < minValue)
			 minValue = totalQ/10;		
		//LOGIC END
		System.out.println("Quiz Count:"+ minValue);	    
    }  
	
	//for increasing the tag count by one
	private static void tagCountStoreUpdate(String key) {
		
		if(tagCountStore.containsKey(key))
			tagCountStore.put(key, tagCountStore.get(key)+1);
		else
			tagCountStore.put(key, 1);		
	}

	//for increasing the difficulty level count by one
	private static void diffLevelCountStoreUpdate(String key) {
		
		if(diffLevelCountStore.containsKey(key))
			diffLevelCountStore.put(key, diffLevelCountStore.get(key)+1);
		else
			diffLevelCountStore.put(key, 1);			
	}
	
	private static void addKeysToStore() {
		tagCountStore.put("T1", 0);
		tagCountStore.put("T2", 0);
		tagCountStore.put("T3", 0);
		tagCountStore.put("T4", 0);
		tagCountStore.put("T5", 0);
		tagCountStore.put("T6", 0);	
		diffLevelCountStore.put("EASY", 0);
		diffLevelCountStore.put("MEDIUM", 0);
		diffLevelCountStore.put("HARD", 0);
	}
	
	//for parsing through each line in txt file and finding different counts 
	private static void initializeCountsAndAddQuestions(String line) {
		
			String[] splitValue = line.split("\\|");
			int splitLength = splitValue.length;
			if(splitValue[splitLength-2] != null && splitValue[splitLength-1] != null) {
				//incrementing total questions value by one
				++totalQ;
				
				if(splitValue[splitLength-1].toLowerCase().contains("tag") ) {
					String[] splitTag = splitValue[splitLength-1].split("g");
					tagCountStoreUpdate("T"+splitTag[1]);
				}			
				if(splitValue[splitLength-2].toLowerCase().contains("easy"))
					diffLevelCountStoreUpdate("EASY");
				else if(splitValue[splitLength-2].toLowerCase().contains("medium"))
					diffLevelCountStoreUpdate("MEDIUM");
				else 
					diffLevelCountStoreUpdate("HARD");			
			}
	}
}
