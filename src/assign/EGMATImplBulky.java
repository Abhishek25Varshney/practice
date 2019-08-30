package assign;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class EGMATImplBulky {
	
	//for total questions and quiz generated
	private static int totalQ, quizCount; 
	//temporary values used
	private static int tempCount,tempEasy,tempMed,tempHard;
	//static Map<String,String> questionStore;
	//for storing tag counts and difficulty level counts
	private static Map<String,Integer> tagCountStore, diffLevelCountStore;
	//for checking if maximum limit is reached for generating quiz
	private static Boolean maxLimitReached;

	private static Set<String> completedTags;
	
	public static void main(String[] args){    
		File file = new File("C:\\e-GMAT\\QUESTIONS.txt"); 
		Scanner sc;
		//questionStore = new HashMap<String, String>();
				tagCountStore = new HashMap<String, Integer>();
				addKeysTotagCountStore();
				diffLevelCountStore = new HashMap<String, Integer>();
			    maxLimitReached = false;
			    totalQ = 0;
			    completedTags = new HashSet<String>();
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
	    //totalQ = questionStore.size();  
		try{  
			
		    while(!maxLimitReached) {
		    	 
		 	    tempCount = 0;tempEasy = 0;tempHard=0;tempMed=0;
		        if(diffLevelCountStore.get("countEASY") >= 2 && diffLevelCountStore.get("countMED") >= 2 && diffLevelCountStore.get("countHARD") >= 2 && getSumOfTag("T1") >=1 && getSumOfTag("T2") >=1 && getSumOfTag("T3") >=1 && getSumOfTag("T4") >=1 && getSumOfTag("T5") >=1 && getSumOfTag("T6") >=1) {
		               
		        	decreaseAllTagsByOne();
		               
		            while(tempCount < 10) {
		        	   
		               completedTags.clear();
		        	   if(tempEasy <2)
		    			   updateRemaining("easy");
		    		   else if(tempMed <2)
		    			   updateRemaining("medium");
		    		   else if(tempHard <2)
		    			   updateRemaining("hard");
		    		   else {
		    			   updateRemaining("any");
		    		   }
		    		   tempCount++;		    		 
		    	   }
		    	   if(!maxLimitReached) {
		    		   ++quizCount;
		    		   totalQ = totalQ-10;
		    	   }
		       }
		       else {
		    	   maxLimitReached = true;
		       }
		       
		    }
		    System.out.println("Quiz Count:"+quizCount);
		}catch (Exception e) {
			System.out.println("Quiz Count:"+quizCount);
		}
	    
    }  
	
	//for getting the sum of a particular tag
	private static int getSumOfTag(String s) {
		try {
			if(s.equals("T1"))
				return (tagCountStore.get("countT1Hard")+tagCountStore.get("countT1Med")+tagCountStore.get("countT1Easy"));
			else if(s.equals("T2"))		
				return (tagCountStore.get("countT2Hard")+tagCountStore.get("countT2Med")+tagCountStore.get("countT2Easy"));
			else if(s.equals("T3"))		
				return (tagCountStore.get("countT3Hard")+tagCountStore.get("countT3Med")+tagCountStore.get("countT3Easy"));
			else if(s.equals("T4"))		
				return (tagCountStore.get("countT4Hard")+tagCountStore.get("countT4Med")+tagCountStore.get("countT4Easy"));
			else if(s.equals("T5"))		
				return (tagCountStore.get("countT5Hard")+tagCountStore.get("countT5Med")+tagCountStore.get("countT5Easy"));
			else if(s.equals("T6"))		
				return (tagCountStore.get("countT6Hard")+tagCountStore.get("countT6Med")+tagCountStore.get("countT6Easy"));
			else
				return 0;
		}
		catch(Exception e) {
			maxLimitReached = true;
			return 0;
		}
					
	}
	
	//for increasing or decreasing the tag count by one
	private static void tagCountStoreUpdate(String key,String plusorminus) {
		try {
			if(plusorminus != null){
				if(tagCountStore.containsKey(key))
					tagCountStore.put(key, tagCountStore.get(key)+1);
				else
					tagCountStore.put(key, 1);
			}
			else {
				if(tagCountStore.get(key) == 0) { 
					 maxLimitReached = true;
				}
				else
					tagCountStore.put(key, tagCountStore.get(key)-1);
			}
		}catch(Exception e) {
			maxLimitReached = true;
		}
	}

	//for increasing or decreasing the difficulty level count by one
	private static void diffLevelCountStoreUpdate(String key,String plusorminus) {
		try {
			if(plusorminus != null){
				if(diffLevelCountStore.containsKey(key))
					diffLevelCountStore.put(key, diffLevelCountStore.get(key)+1);
				else
					diffLevelCountStore.put(key, 1);
			}
			else {
				if(diffLevelCountStore.get(key) == 0) {
					 maxLimitReached = true;
				}
				else
					diffLevelCountStore.put(key, diffLevelCountStore.get(key)-1);
			}
		}catch(Exception e) {
			maxLimitReached = true;
		}
	}
	
	//for finding tag with greatest count
	public static String findTagWithGreatestCount() {
		try {
			String[] tags = new String[]{ "T1","T2","T3","T4","T5","T6" };
			String s = "";
			int count = 0;
			for( int i = 0; i < tags.length ; i++) {
				if(!completedTags.contains(tags[i])) {
					if(count < getSumOfTag(tags[i])) {
						count = getSumOfTag(tags[i]);
						s = tags[i];
					}
				}
			}
			return s;
		}catch(Exception e) {
			maxLimitReached = true;
			return null;
		}
		
	}

	private static void addKeysTotagCountStore() {
		tagCountStore.put("countT1Easy", 0);
		tagCountStore.put("countT2Easy", 0);
		tagCountStore.put("countT3Easy", 0);
		tagCountStore.put("countT4Easy", 0);
		tagCountStore.put("countT5Easy", 0);
		tagCountStore.put("countT6Easy", 0);
		tagCountStore.put("countT1Med", 0);
		tagCountStore.put("countT2Med", 0);
		tagCountStore.put("countT3Med", 0);
		tagCountStore.put("countT4Med", 0);
		tagCountStore.put("countT5Med", 0);
		tagCountStore.put("countT6Med", 0);
		tagCountStore.put("countT1Hard", 0);
		tagCountStore.put("countT2Hard", 0);
		tagCountStore.put("countT3Hard", 0);
		tagCountStore.put("countT4Hard", 0);
		tagCountStore.put("countT5Hard", 0);
		tagCountStore.put("countT6Hard", 0);		
	}
	

	//for decreasing the remaining tags by one if needed
	public static void updateRemaining(String s) {	
		try {
			String greatestTag;
			greatestTag = findTagWithGreatestCount();
			switch(s) {
				case "easy" :
							if(greatestTag.equals("T1")) {	
								if(tagCountStore.get("countT1Easy") == 0) {
									completedTags.add("T1");
									updateRemaining("easy");
								}
								else {
									tagCountStoreUpdate("countT1Easy",null);
									diffLevelCountStoreUpdate("countEASY",null);
									++tempEasy;
								}
							}
							else if(greatestTag.equals("T2")) {
								if(tagCountStore.get("countT2Easy") == 0) {
									completedTags.add("T2");
									updateRemaining("easy");
								}
								else {
									tagCountStoreUpdate("countT2Easy",null);
									diffLevelCountStoreUpdate("countEASY",null);
									++tempEasy;
								}
							}
							else if(greatestTag.equals("T3")) {
								if(tagCountStore.get("countT3Easy") == 0) {
									completedTags.add("T3");
									updateRemaining("easy");
								}
								else {
									tagCountStoreUpdate("countT3Easy",null);
									diffLevelCountStoreUpdate("countEASY",null);
									++tempEasy;
								}
							}
							else if(greatestTag.equals("T4")) {
								if(tagCountStore.get("countT4Easy") == 0) {
									completedTags.add("T4");
									updateRemaining("easy");
								}
								else {
									tagCountStoreUpdate("countT4Easy",null);
									diffLevelCountStoreUpdate("countEASY",null);
									++tempEasy;
								}
							}
							else if(greatestTag.equals("T5")) {
								if(tagCountStore.get("countT5Easy") == 0) {
									completedTags.add("T5");
									updateRemaining("easy");
								}
								else {
									tagCountStoreUpdate("countT5Easy",null);
									diffLevelCountStoreUpdate("countEASY",null);
									++tempEasy;
								}
							}
							else if(greatestTag.equals("T6")) {
								if(tagCountStore.get("countT6Easy") == 0) {
									completedTags.add("T6");
									updateRemaining("easy");
								}
								else {
									tagCountStoreUpdate("countT6Easy",null);
									diffLevelCountStoreUpdate("countEASY",null);
									++tempEasy;
								}
							}
							else {
								maxLimitReached = true;
							}
							break;
				case "medium" :
							if(greatestTag.equals("T1")) {
								if(tagCountStore.get("countT1Med") == 0) {
									completedTags.add("T1");
									updateRemaining("medium");
								}
								else {
									tagCountStoreUpdate("countT1Med",null);
									diffLevelCountStoreUpdate("countMED",null);
									++tempMed;
								}
							}
							else if(greatestTag.equals("T2")) {
								if(tagCountStore.get("countT2Med") == 0) {
									completedTags.add("T2");
									updateRemaining("medium");
								}
								else {
									tagCountStoreUpdate("countT2Med",null);
									diffLevelCountStoreUpdate("countMED",null);
									++tempMed;
								}
							}
							else if(greatestTag.equals("T3")) {
								if(tagCountStore.get("countT3Med") == 0) {
									completedTags.add("T3");
									updateRemaining("medium");
								}
								else {
									tagCountStoreUpdate("countT3Med",null);
									diffLevelCountStoreUpdate("countMED",null);
									++tempMed;
								}
							}
							else if(greatestTag.equals("T4")) {
								if(tagCountStore.get("countT4Med") == 0) {
									completedTags.add("T4");
									updateRemaining("medium");
								}
								else {
									tagCountStoreUpdate("countT4Med",null);
									diffLevelCountStoreUpdate("countMED",null);
									++tempMed;
								}
							}
							else if(greatestTag.equals("T5")) {
								if(tagCountStore.get("countT5Med") == 0) {
									completedTags.add("T5");
									updateRemaining("medium");
								}
								else {
									tagCountStoreUpdate("countT5Med",null);
									diffLevelCountStoreUpdate("countMED",null);
									++tempMed;
								}
							}
							else if(greatestTag.equals("T6")) {
								if(tagCountStore.get("countT6Med") == 0) {
									completedTags.add("T6");
									updateRemaining("medium");
								}
								else {
									tagCountStoreUpdate("countT6Med",null);
									diffLevelCountStoreUpdate("countMED",null);
									++tempMed;
								}
							}
							else {
								maxLimitReached = true;
							}
							break;
							
				case "hard" :
							if(greatestTag.equals("T1")) {
								if(tagCountStore.get("countT1Hard") == 0) {
									completedTags.add("T1");
									updateRemaining("hard");
								}
								else {
									tagCountStoreUpdate("countT1Hard",null);
									diffLevelCountStoreUpdate("countHARD",null);
									++tempHard;
								}
							}
							else if(greatestTag.equals("T2")) {
								if(tagCountStore.get("countT2Hard") == 0) {
									completedTags.add("T2");
									updateRemaining("hard");
								}
								else {
									tagCountStoreUpdate("countT2Hard",null);
									diffLevelCountStoreUpdate("countHARD",null);
									++tempHard;
								}
							}
							else if(greatestTag.equals("T3")) {
								if(tagCountStore.get("countT3Hard") == 0) {
									completedTags.add("T3");
									updateRemaining("hard");
								}
								else {
									tagCountStoreUpdate("countT3Hard",null);
									diffLevelCountStoreUpdate("countHARD",null);
									++tempHard;
								}
							} 
							else if(greatestTag.equals("T4")) {
								if(tagCountStore.get("countT4Hard") == 0) {
									completedTags.add("T4");
									updateRemaining("hard");
								}
								else {
									tagCountStoreUpdate("countT4Hard",null);
									diffLevelCountStoreUpdate("countHARD",null);
									++tempHard;
								}
							} 
							else if(greatestTag.equals("T5")) {
								if(tagCountStore.get("countT5Hard") == 0) {
									completedTags.add("T5");
									updateRemaining("hard");
								}
								else {
									tagCountStoreUpdate("countT5Hard",null);
									diffLevelCountStoreUpdate("countHARD",null);
									++tempHard;
								}
							} 
							else if(greatestTag.equals("T6")) {
								if(tagCountStore.get("countT6Hard") == 0) {
									completedTags.add("T6");
									updateRemaining("hard");
								}
								else {
									tagCountStoreUpdate("countT6Hard",null);
									diffLevelCountStoreUpdate("countHARD",null);
									++tempHard;
								}
							}
							else {
								maxLimitReached = true;
							}
							break;
				case "any" :
							String key = Collections.max(diffLevelCountStore.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
							if(greatestTag.equals("T1")) {
									if(key.equals("countEASY")) {
										if(tagCountStore.get("countT1Easy") == 0) {
											if(tagCountStore.get("countT1Med") == 0) {
												tagCountStoreUpdate("countT1Hard",null);
												diffLevelCountStoreUpdate("countHARD",null);
												++tempHard;
											}
											else {
												tagCountStoreUpdate("countT1Med",null);
												diffLevelCountStoreUpdate("countMED",null);
												++tempMed;
											}
										}
										else {
											tagCountStoreUpdate("countT1Easy",null);
											diffLevelCountStoreUpdate("countEASY",null);
											++tempEasy;
										}
									}
									else if(key.equals("countMED")) {
										if(tagCountStore.get("countT1Med") == 0) {
											if(tagCountStore.get("countT1Easy") == 0) {
												tagCountStoreUpdate("countT1Hard",null);
												diffLevelCountStoreUpdate("countHARD",null);
												++tempHard;
											}
											else {
												tagCountStoreUpdate("countT1Easy",null);
												diffLevelCountStoreUpdate("countEASY",null);
												++tempEasy;
											}
										}
										else {
											tagCountStoreUpdate("countT1Med",null);
											diffLevelCountStoreUpdate("countMED",null);
											++tempMed;
										}
									}
									else{
										if(tagCountStore.get("countT1Hard") == 0) {
											if(tagCountStore.get("countT1Easy") == 0) {
												tagCountStoreUpdate("countT1Med",null);
												diffLevelCountStoreUpdate("countMED",null);
												++tempMed;
											}
											else {
												tagCountStoreUpdate("countT1Easy",null);
												diffLevelCountStoreUpdate("countEASY",null);
												++tempEasy;
											}
										}
										else {
											tagCountStoreUpdate("countT1Hard",null);
											diffLevelCountStoreUpdate("countHARD",null);
											++tempHard;
										}
									}												
							}
							else if(greatestTag.equals("T2")) {
									if(key.equals("countEASY")) {
										if(tagCountStore.get("countT2Easy") == 0) {
											if(tagCountStore.get("countT2Med") == 0) {
												tagCountStoreUpdate("countT2Hard",null);
												diffLevelCountStoreUpdate("countHARD",null);
												++tempHard;
											}
											else {
												tagCountStoreUpdate("countT2Med",null);
												diffLevelCountStoreUpdate("countMED",null);
												++tempMed;
											}
										}
										else {
											tagCountStoreUpdate("countT2Easy",null);
											diffLevelCountStoreUpdate("countEASY",null);
											++tempEasy;
										}
									}
									else if(key.equals("countMED")) {
										if(tagCountStore.get("countT2Med") == 0) {
											if(tagCountStore.get("countT2Easy") == 0) {
												tagCountStoreUpdate("countT2Hard",null);
												diffLevelCountStoreUpdate("countHARD",null);
												++tempHard;
											}
											else {
												tagCountStoreUpdate("countT2Easy",null);
												diffLevelCountStoreUpdate("countEASY",null);
												++tempEasy;
											}
										}
										else {
											tagCountStoreUpdate("countT2Med",null);
											diffLevelCountStoreUpdate("countMED",null);
											++tempMed;
										}
									}
									else {
										if(tagCountStore.get("countT2Hard") == 0) {
											if(tagCountStore.get("countT2Easy") == 0) {
												tagCountStoreUpdate("countT2Med",null);
												diffLevelCountStoreUpdate("countMED",null);
												++tempMed;
											}
											else {
												tagCountStoreUpdate("countT2Easy",null);
												diffLevelCountStoreUpdate("countEASY",null);
												++tempEasy;
											}
										}
										else {
											tagCountStoreUpdate("countT2Hard",null);
											diffLevelCountStoreUpdate("countHARD",null);
											++tempHard;
										}
									}		
							}
							else if(greatestTag.equals("T3")) {
									if(key.equals("countEASY")) {
										if(tagCountStore.get("countT3Easy") == 0) {
											if(tagCountStore.get("countT3Med") == 0) {
												tagCountStoreUpdate("countT3Hard",null);
												diffLevelCountStoreUpdate("countHARD",null);
												++tempHard;
											}
											else {
												tagCountStoreUpdate("countT3Med",null);
												diffLevelCountStoreUpdate("countMED",null);
												++tempMed;
											}
										}
										else {
											tagCountStoreUpdate("countT3Easy",null);
											diffLevelCountStoreUpdate("countEASY",null);
											++tempEasy;
										}
									}
									else if(key.equals("countMED")) {
										if(tagCountStore.get("countT3Med") == 0) {
											if(tagCountStore.get("countT3Easy") == 0) {
												tagCountStoreUpdate("countT3Hard",null);
												diffLevelCountStoreUpdate("countHARD",null);
												++tempHard;
											}
											else {
												tagCountStoreUpdate("countT3Easy",null);
												diffLevelCountStoreUpdate("countEASY",null);
												++tempEasy;
											}
										}
										else {
											tagCountStoreUpdate("countT3Med",null);
											diffLevelCountStoreUpdate("countMED",null);
											++tempMed;
										}
									}
									else {
										if(tagCountStore.get("countT3Hard") == 0) {
											if(tagCountStore.get("countT3Easy") == 0) {
												tagCountStoreUpdate("countT3Med",null);
												diffLevelCountStoreUpdate("countMED",null);
												++tempMed;
											}
											else {
												tagCountStoreUpdate("countT3Easy",null);
												diffLevelCountStoreUpdate("countEASY",null);
												++tempEasy;
											}
										}
										else {
											tagCountStoreUpdate("countT3Hard",null);
											diffLevelCountStoreUpdate("countHARD",null);
											++tempHard;
										}
									}		
							} 
							else if(greatestTag.equals("T4")) {
									if(key.equals("countEASY")) {
										if(tagCountStore.get("countT4Easy") == 0) {
											if(tagCountStore.get("countT4Med") == 0) {
												tagCountStoreUpdate("countT4Hard",null);
												diffLevelCountStoreUpdate("countHARD",null);
												++tempHard;
											}
											else {
												tagCountStoreUpdate("countT4Med",null);
												diffLevelCountStoreUpdate("countMED",null);
												++tempMed;
											}
										}
										else {
											tagCountStoreUpdate("countT4Easy",null);
											diffLevelCountStoreUpdate("countEASY",null);
											++tempEasy;
										}
									}
									else if(key.equals("countMED")) {
										if(tagCountStore.get("countT4Med") == 0) {
											if(tagCountStore.get("countT4Easy") == 0) {
												tagCountStoreUpdate("countT4Hard",null);
												diffLevelCountStoreUpdate("countHARD",null);
												++tempHard;
											}
											else {
												tagCountStoreUpdate("countT4Easy",null);
												diffLevelCountStoreUpdate("countEASY",null);
												++tempEasy;
											}
										}
										else {
											tagCountStoreUpdate("countT4Med",null);
											diffLevelCountStoreUpdate("countMED",null);
											++tempMed;
										}
									}
									else {
										if(tagCountStore.get("countT4Hard") == 0) {
											if(tagCountStore.get("countT4Easy") == 0) {
												tagCountStoreUpdate("countT4Med",null);
												diffLevelCountStoreUpdate("countMED",null);
												++tempMed;
											}
											else {
												tagCountStoreUpdate("countT4Easy",null);
												diffLevelCountStoreUpdate("countEASY",null);
												++tempEasy;
											}
										}
										else {
											tagCountStoreUpdate("countT4Hard",null);
											diffLevelCountStoreUpdate("countHARD",null);
											++tempHard;
										}
									}
							} 
							else if(greatestTag.equals("T5")) {
									if(key.equals("countEASY")) {
										if(tagCountStore.get("countT5Easy") == 0) {
											if(tagCountStore.get("countT5Med") == 0) {
												tagCountStoreUpdate("countT5Hard",null);
												diffLevelCountStoreUpdate("countHARD",null);
												++tempHard;
											}
											else {
												tagCountStoreUpdate("countT5Med",null);
												diffLevelCountStoreUpdate("countMED",null);
												++tempMed;
											}
										}
										else {
											tagCountStoreUpdate("countT5Easy",null);
											diffLevelCountStoreUpdate("countEASY",null);
											++tempEasy;
										}
									}
									else if(key.equals("countMED")) {
										if(tagCountStore.get("countT5Med") == 0) {
											if(tagCountStore.get("countT5Easy") == 0) {
												tagCountStoreUpdate("countT5Hard",null);
												diffLevelCountStoreUpdate("countHARD",null);
												++tempHard;
											}
											else {
												tagCountStoreUpdate("countT5Easy",null);
												diffLevelCountStoreUpdate("countEASY",null);
												++tempEasy;
											}
										}
										else {
											tagCountStoreUpdate("countT5Med",null);
											diffLevelCountStoreUpdate("countMED",null);
											++tempMed;
										}
									}
									else {
										if(tagCountStore.get("countT5Hard") == 0) {
											if(tagCountStore.get("countT5Easy") == 0) {
												tagCountStoreUpdate("countT5Med",null);
												diffLevelCountStoreUpdate("countMED",null);
												++tempMed;
											}
											else {
												tagCountStoreUpdate("countT5Easy",null);
												diffLevelCountStoreUpdate("countEASY",null);
												++tempEasy;
											}
										}
										else {
											tagCountStoreUpdate("countT5Hard",null);
											diffLevelCountStoreUpdate("countHARD",null);
											++tempHard;
										}
									}		
							} 
							else if(greatestTag.equals("T6")) {
									if(key.equals("countEASY")) {
										if(tagCountStore.get("countT6Easy") == 0) {
											if(tagCountStore.get("countT6Med") == 0) {
												tagCountStoreUpdate("countT6Hard",null);
												diffLevelCountStoreUpdate("countHARD",null);
												++tempHard;
											}
											else {
												tagCountStoreUpdate("countT6Med",null);
												diffLevelCountStoreUpdate("countMED",null);
												++tempMed;
											}
										}
										else {
											tagCountStoreUpdate("countT6Easy",null);
											diffLevelCountStoreUpdate("countEASY",null);
											++tempEasy;
										}
									}
									else if(key.equals("countMED")) {
										if(tagCountStore.get("countT6Med") == 0) {
											if(tagCountStore.get("countT6Easy") == 0) {
												tagCountStoreUpdate("countT6Hard",null);
												diffLevelCountStoreUpdate("countHARD",null);
												++tempHard;
											}
											else {
												tagCountStoreUpdate("countT6Easy",null);
												diffLevelCountStoreUpdate("countEASY",null);
												++tempEasy;
											}
										}
										else {
											tagCountStoreUpdate("countT6Med",null);
											diffLevelCountStoreUpdate("countMED",null);
											++tempMed;
										}
									}
									else {
										if(tagCountStore.get("countT6Hard") == 0) {
											if(tagCountStore.get("countT6Easy") == 0) {
												tagCountStoreUpdate("countT6Med",null);
												diffLevelCountStoreUpdate("countMED",null);
												++tempMed;
											}
											else {
												tagCountStoreUpdate("countT6Easy",null);
												diffLevelCountStoreUpdate("countEASY",null);
												++tempEasy;
											}
										}
										else {
											tagCountStoreUpdate("countT6Hard",null);
											diffLevelCountStoreUpdate("countHARD",null);
											++tempHard; 
										}										
									}	
							} 
							break;							
				default :					
					break;				
			}
		}catch(Exception e) {
			maxLimitReached = true;
		}
    }
				
	//for decreasing all tag counts by one
	public static void	decreaseAllTagsByOne() {
		try {
			if(tagCountStore.get("countT1Easy") >= tagCountStore.get("countT1Hard") && tagCountStore.get("countT1Easy") >= tagCountStore.get("countT1Med")) {
				tagCountStoreUpdate("countT1Easy",null);
				diffLevelCountStoreUpdate("countEASY",null);
				++tempEasy;
			}
			else if(tagCountStore.get("countT1Med") >= tagCountStore.get("countT1Hard") && tagCountStore.get("countT1Med") >= tagCountStore.get("countT1Easy")) {
				tagCountStoreUpdate("countT1Med",null);
				diffLevelCountStoreUpdate("countMED",null);
				++tempMed;
			}
			else {
				tagCountStoreUpdate("countT1Hard",null);
				diffLevelCountStoreUpdate("countHARD",null);
				++tempHard;
			}

			if(tagCountStore.get("countT2Easy") >= tagCountStore.get("countT2Hard") && tagCountStore.get("countT2Easy") >= tagCountStore.get("countT2Med")) {
				tagCountStoreUpdate("countT2Easy",null);
				diffLevelCountStoreUpdate("countEASY",null);
				++tempEasy;
			}
			else if(tagCountStore.get("countT2Med") >= tagCountStore.get("countT2Hard") && tagCountStore.get("countT2Med") >= tagCountStore.get("countT2Easy")) {
				tagCountStoreUpdate("countT2Med",null);
				diffLevelCountStoreUpdate("countMED",null);
				++tempMed;
			}
			else {
				tagCountStoreUpdate("countT2Hard",null);
				diffLevelCountStoreUpdate("countHARD",null);
				++tempHard;
			}
			
			if(tagCountStore.get("countT3Easy") >= tagCountStore.get("countT3Hard") && tagCountStore.get("countT3Easy") >= tagCountStore.get("countT3Med")) {
				tagCountStoreUpdate("countT3Easy",null);
				diffLevelCountStoreUpdate("countEASY",null);
				++tempEasy;
			}
			else if(tagCountStore.get("countT3Med") >= tagCountStore.get("countT3Hard") && tagCountStore.get("countT3Med") >= tagCountStore.get("countT3Easy")) {
				tagCountStoreUpdate("countT3Med",null);
				diffLevelCountStoreUpdate("countMED",null);
				++tempMed;
			}
			else {
				tagCountStoreUpdate("countT3Hard",null);
				diffLevelCountStoreUpdate("countHARD",null);
				++tempHard;
			}
			
			if(tagCountStore.get("countT4Easy") >= tagCountStore.get("countT4Hard") && tagCountStore.get("countT4Easy") >= tagCountStore.get("countT4Med")) {
				tagCountStoreUpdate("countT4Easy",null);
				diffLevelCountStoreUpdate("countEASY",null);
				++tempEasy;
			}
			else if(tagCountStore.get("countT4Med") >= tagCountStore.get("countT4Hard") && tagCountStore.get("countT4Med") >= tagCountStore.get("countT4Easy")) {
				tagCountStoreUpdate("countT4Med",null);
				diffLevelCountStoreUpdate("countMED",null);
				++tempMed;
			}
			else {
				tagCountStoreUpdate("countT4Hard",null);
				diffLevelCountStoreUpdate("countHARD",null);
				++tempHard;
			}
			
			if(tagCountStore.get("countT5Easy") >= tagCountStore.get("countT5Hard") && tagCountStore.get("countT5Easy") >= tagCountStore.get("countT5Med")) {
				tagCountStoreUpdate("countT5Easy",null);
				diffLevelCountStoreUpdate("countEASY",null);
				++tempEasy;
			}
			else if(tagCountStore.get("countT5Med") >= tagCountStore.get("countT5Hard") && tagCountStore.get("countT5Med") >= tagCountStore.get("countT5Easy")) {
				tagCountStoreUpdate("countT5Med",null);
				diffLevelCountStoreUpdate("countMED",null);
				++tempMed;
			}
			else {
				tagCountStoreUpdate("countT5Hard",null);
				diffLevelCountStoreUpdate("countHARD",null);
				++tempHard;
			}
			
			if(tagCountStore.get("countT6Easy") >= tagCountStore.get("countT6Hard") && tagCountStore.get("countT6Easy") >= tagCountStore.get("countT6Med")) {
				tagCountStoreUpdate("countT6Easy",null);
				diffLevelCountStoreUpdate("countEASY",null);
				++tempEasy;
			}
			else if(tagCountStore.get("countT6Med") >= tagCountStore.get("countT6Hard") && tagCountStore.get("countT6Med") >= tagCountStore.get("countT6Easy")) {
				tagCountStoreUpdate("countT6Med",null);
				diffLevelCountStoreUpdate("countMED",null);
				++tempMed;
			}
			else {
				tagCountStoreUpdate("countT6Hard",null);
				diffLevelCountStoreUpdate("countHARD",null);
				++tempHard;
			}			
			tempCount += 6;
		}catch(Exception e) {
			maxLimitReached = true;
		}
	}

	//for parsing through each line in txt file and finding different counts 
	private static void initializeCountsAndAddQuestions(String line) {
		try {
			String[] splitValue = line.split("\\|");
			int splitLength = splitValue.length;
			if(splitValue[splitLength-2] != null && splitValue[splitLength-1] != null) {
				++totalQ;
				
				if(splitValue[splitLength-1].toLowerCase().contains("tag1") && splitValue[splitLength-2].toLowerCase().contains("easy"))
					tagCountStoreUpdate("countT1Easy","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag2") && splitValue[splitLength-2].toLowerCase().contains("easy"))
					tagCountStoreUpdate("countT2Easy","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag3") && splitValue[splitLength-2].toLowerCase().contains("easy"))
					tagCountStoreUpdate("countT3Easy","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag4") && splitValue[splitLength-2].toLowerCase().contains("easy"))
					tagCountStoreUpdate("countT4Easy","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag5") && splitValue[splitLength-2].toLowerCase().contains("easy"))
					tagCountStoreUpdate("countT5Easy","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag6") && splitValue[splitLength-2].toLowerCase().contains("easy"))
					tagCountStoreUpdate("countT6Easy","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag1") && splitValue[splitLength-2].toLowerCase().contains("medium"))
					tagCountStoreUpdate("countT1Med","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag2") && splitValue[splitLength-2].toLowerCase().contains("medium"))
					tagCountStoreUpdate("countT2Med","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag3") && splitValue[splitLength-2].toLowerCase().contains("medium"))
					tagCountStoreUpdate("countT3Med","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag4") && splitValue[splitLength-2].toLowerCase().contains("medium"))
					tagCountStoreUpdate("countT4Med","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag5") && splitValue[splitLength-2].toLowerCase().contains("medium"))
					tagCountStoreUpdate("countT5Med","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag6") && splitValue[splitLength-2].toLowerCase().contains("medium"))
					tagCountStoreUpdate("countT6Med","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag1") && splitValue[splitLength-2].toLowerCase().contains("hard"))
					tagCountStoreUpdate("countT1Hard","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag2") && splitValue[splitLength-2].toLowerCase().contains("hard"))
					tagCountStoreUpdate("countT2Hard","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag3") && splitValue[splitLength-2].toLowerCase().contains("hard"))
					tagCountStoreUpdate("countT3Hard","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag4") && splitValue[splitLength-2].toLowerCase().contains("hard"))	
					tagCountStoreUpdate("countT4Hard","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag5") && splitValue[splitLength-2].toLowerCase().contains("hard"))
					tagCountStoreUpdate("countT5Hard","add");
				else if(splitValue[splitLength-1].toLowerCase().contains("tag6") && splitValue[splitLength-2].toLowerCase().contains("hard"))
					tagCountStoreUpdate("countT6Hard","add");
				
				if(splitValue[splitLength-2].toLowerCase().contains("easy"))
					diffLevelCountStoreUpdate("countEASY","add");
				else if(splitValue[splitLength-2].toLowerCase().contains("medium"))
					diffLevelCountStoreUpdate("countMED","add");
				else if(splitValue[splitLength-2].toLowerCase().contains("hard"))
					diffLevelCountStoreUpdate("countHARD","add");
				
				/*String question = "";
				for(int i = 0;i<splitLength - 2; i++) {
					if(!question.equals(""))
						question += "|";
					
					question += splitValue[i];
				}
				questionStore.put(question, splitValue[splitLength-2]+splitValue[splitLength-1]);
				*/
			}
		}catch(Exception e) {
			maxLimitReached = true;
		}
	}

}
