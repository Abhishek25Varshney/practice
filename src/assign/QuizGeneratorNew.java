package assign;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Mohammad Mayar Alam
 *
 */
public class QuizGeneratorNew {

	private static final String[] DIFFICULTY= {"EASY", "MEDIUM", "HARD"};
	private static final String[] TAG= {"Tag1", "Tag2", "Tag3", "Tag4", "Tag5", "Tag6"};
	
	static List<String> easyTag1;
	static List<String> mediumTag1;
	static List<String> hardTag1;
	
	static List<String> easyTag2;
	static List<String> mediumTag2;
	static List<String> hardTag2;

	static List<String> easyTag3;
	static List<String> mediumTag3;
	static List<String> hardTag3;
	
	static List<String> easyTag4;
	static List<String> mediumTag4;
	static List<String> hardTag4;
	
	static List<String> easyTag5;
	static List<String> mediumTag5;
	static List<String> hardTag5;
	
	static List<String> easyTag6;
	static List<String> mediumTag6;
	static List<String> hardTag6;
	
 	public static void main(String[] args) {
 		
		String filename= "C:\\e-GMAT\\QUESTIONS.txt";
		
		List<String> easy[] = new ArrayList[6];
		List<String> medium[] = new ArrayList[6];
		List<String> hard[] = new ArrayList[6];
		
		for(int i=1; i<=6; i++) {
			String desDiff= DIFFICULTY[0];
			String desTag= TAG[i-1];
			
			try(Stream<String> stream= Files.lines(Paths.get(filename))){
				easy[i-1]= stream
							.filter(line -> line.contains(desDiff) && line.endsWith(desTag))
							.collect(Collectors.toList());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=1; i<=6; i++) {
			String desDiff= DIFFICULTY[1];
			String desTag= TAG[i-1];
			
			try(Stream<String> stream= Files.lines(Paths.get(filename))){
				medium[i-1]= stream
							.filter(line -> line.contains(desDiff) && line.endsWith(desTag))
							.collect(Collectors.toList());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=1; i<=6; i++) {
			String desDiff= DIFFICULTY[2];
			String desTag= TAG[i-1];
			
			try(Stream<String> stream= Files.lines(Paths.get(filename))){
				hard[i-1]= stream
							.filter(line -> line.contains(desDiff) && line.endsWith(desTag))
							.collect(Collectors.toList());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		 easyTag1= easy[0];
		 mediumTag1= medium[0];
		 hardTag1= hard[0];
		
		 easyTag2= easy[1];
		 mediumTag2= medium[1];
		 hardTag2= hard[1];
	
		 easyTag3= easy[2];
		 mediumTag3= medium[2];
		 hardTag3= hard[2];
		
		 easyTag4= easy[3];
		 mediumTag4= medium[3];
		 hardTag4= hard[3];
		
		 easyTag5= easy[4];
		 mediumTag5= medium[4];
		 hardTag5= hard[4];
		
		 easyTag6= easy[5];
		 mediumTag6= medium[5];
		 hardTag6= hard[5];
		
		int noOfQuiz= 0;
		
		ArrayList<ArrayList<String>> quizList= new ArrayList<>();
		
		for(int i=1; i<=60; i++) {
			try {
				ArrayList<String> currentList= new ArrayList<>();
				quizList.add(currentList);

				if(i % 3 == 1) {
					if(!checkForMethod1()) {
						method1(currentList, i);
					}else if(!checkForMethod2()) {
						method2(currentList, i);
					}else if(!checkForMethod3()) {
						method3(currentList, i);
					}else {
						//Empty means will throw exception
						System.out.println("No of Quiz "+ noOfQuiz);
						
						for(ArrayList<String> quizNewList: quizList) {
							for(String question: quizNewList) {
//								System.out.println(question);
							}
						}
						break;
					}
				}else if(i % 3 == 2){
					if(!checkForMethod2()) {
						method2(currentList, i);
					}else if(!checkForMethod3()) {
						method3(currentList, i);
					}else if(!checkForMethod1()) {
						method1(currentList, i);
					}else {
						//Empty means will throw exception
						System.out.println("No of Quiz "+ noOfQuiz);
						
						for(ArrayList<String> quizNewList: quizList) {
							for(String question: quizNewList) {
//								System.out.println(question);
							}
						}
						break;
					}
				}else if(i % 3 == 0) {
					if(!checkForMethod3()) {
						method3(currentList, i);
					}else if(!checkForMethod1()) {
						method1(currentList, i);
					}else if(!checkForMethod2()) {
						method2(currentList, i);
					}else{
						//Empty means will throw exception
						System.out.println("No of Quiz "+ noOfQuiz);
						
						for(ArrayList<String> quizNewList: quizList) {
							for(String question: quizNewList) {
//								System.out.println(question);
							}
//							System.out.println();
//							System.out.println();
						}
						break;
					}
				}
				
				noOfQuiz++;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
 	
 	/*
 	 * This method will check that whether the size of easyTag1, mediumTag2, hardTag3, easyTag4, mediumTag5, hardTag6
 	 * is greater than 3 or not
 	 */
 	private static boolean checkForMethod1() {

 		if( easyTag1.size() < 3 || mediumTag2.size() < 3 || hardTag3.size() < 3 || easyTag4.size() < 3 || mediumTag5.size() < 3 || hardTag6.size() < 3 ) {
 			return true;
 		}else {
 			return false;
 		}
	}
 	
 	/*
 	 * This method will check that whether the size of easyTag2, mediumTag3, hardTag1, easyTag5, mediumTag6, hardTag4
 	 * is greater than 3 or not
 	 */
	private static boolean checkForMethod2() {
		
		if(easyTag2.size() < 3 || mediumTag3.size() < 3 || hardTag1.size() < 3 || easyTag5.size() < 3 || mediumTag6.size() < 3 || hardTag4.size() < 3) {
			return true;
		}else {
			return false;
		}
	}

	/*
	 * This method will check that whether the size of easyTag3, mediumTag1, hardTag2, easyTag6, mediumTag4, hardTag5
	 * is greater than 3 or not
	 */
	private static boolean checkForMethod3() {
		
		if(easyTag3.size() < 3 || mediumTag1.size() < 3 || hardTag2.size() < 3 || easyTag6.size() < 3 || mediumTag4.size() < 3 || hardTag5.size() < 3) {
			return true;
		}else {
			return false;
		}
	}

	
	private static void method3(ArrayList<String> currentList, int i) {
		String q1= easyTag3.get(0);
		currentList.add(q1);
		easyTag3.remove(0);
		
		String q2= mediumTag1.get(0);
		currentList.add(q2);
		mediumTag1.remove(0);
		
		String q3= hardTag2.get(0);
		currentList.add(q3);
		hardTag2.remove(0);
		
		String q4= easyTag6.get(0);
		currentList.add(q4);
		easyTag6.remove(0);
		
		String q5= mediumTag4.get(0);
		currentList.add(q5);
		mediumTag4.remove(0);
		
		String q6= hardTag5.get(0);
		currentList.add(q6);
		hardTag5.remove(0);
		
		if(i%2 == 0) {
			String q7= easyTag3.get(0);
			currentList.add(q7);
			easyTag3.remove(0);
			
			String q8= mediumTag1.get(0);
			currentList.add(q8);
			mediumTag1.remove(0);
			
			String q9= hardTag2.get(0);
			currentList.add(q9);
			hardTag2.remove(0);
		}else {
			String q7= easyTag6.get(0);
			currentList.add(q7);
			easyTag6.remove(0);
			
			String q8= mediumTag4.get(0);
			currentList.add(q8);
			mediumTag4.remove(0);
			
			String q9= hardTag5.get(0);
			currentList.add(q9);
			hardTag5.remove(0);
		}
		
		if(i % 6 == 1) {
			String q10= easyTag3.get(0);
			currentList.add(q10);
			easyTag3.remove(0);
		}else if(i % 6 == 2) {
			String q10= mediumTag1.get(0);
			currentList.add(q10);
			mediumTag1.remove(0);
		}else if(i % 6 == 3) {
			String q10= hardTag2.get(0);
			currentList.add(q10);
			hardTag2.remove(0);
		}else if(i % 6 == 4) {
			String q10= easyTag6.get(0);
			currentList.add(q10);
			easyTag6.remove(0);
		}else if(i % 6 == 5) {
			String q10= mediumTag4.get(0);
			currentList.add(q10);
			mediumTag4.remove(0);
		}else if(i % 6 == 0) {
			String q10= hardTag5.get(0);
			currentList.add(q10);
			hardTag5.remove(0);
		}
		
	}

	private static void method2(ArrayList<String> currentList, int i) {
		String q1= easyTag2.get(0);
		currentList.add(q1);
		easyTag2.remove(0);
		
		String q2= mediumTag3.get(0);
		currentList.add(q2);
		mediumTag3.remove(0);
		
		String q3= hardTag1.get(0);
		currentList.add(q3);
		hardTag1.remove(0);
		
		String q4= easyTag5.get(0);
		currentList.add(q4);
		easyTag5.remove(0);
		
		String q5= mediumTag6.get(0);
		currentList.add(q5);
		mediumTag6.remove(0);
		
		String q6= hardTag4.get(0);
		currentList.add(q6);
		hardTag4.remove(0);
		
		if(i%2 == 0) {
			String q7= easyTag2.get(0);
			currentList.add(q7);
			easyTag2.remove(0);
			
			String q8= mediumTag3.get(0);
			currentList.add(q8);
			mediumTag3.remove(0);
			
			String q9= hardTag1.get(0);
			currentList.add(q9);
			hardTag1.remove(0);
		}else {
			String q7= easyTag5.get(0);
			currentList.add(q7);
			easyTag5.remove(0);
			
			String q8= mediumTag6.get(0);
			currentList.add(q8);
			mediumTag6.remove(0);
			
			String q9= hardTag4.get(0);
			currentList.add(q9);
			hardTag4.remove(0);
		}
		
		if(i % 6 == 1) {
			String q10= easyTag2.get(0);
			currentList.add(q10);
			easyTag2.remove(0);
		}else if(i % 6 == 2) {
			String q10= mediumTag3.get(0);
			currentList.add(q10);
			mediumTag3.remove(0);
		}else if(i % 6 == 3) {
			String q10= hardTag1.get(0);
			currentList.add(q10);
			hardTag1.remove(0);
		}else if(i % 6 == 4) {
			String q10= easyTag5.get(0);
			currentList.add(q10);
			easyTag5.remove(0);
		}else if(i % 6 == 5) {
			String q10= mediumTag6.get(0);
			currentList.add(q10);
			mediumTag6.remove(0);
		}else if(i % 6 == 0) {
			String q10= hardTag4.get(0);
			currentList.add(q10);
			hardTag4.remove(0);
		}
		
	}

	private static void method1(ArrayList<String> currentList, int i) {
		
		String q1= easyTag1.get(0);
		currentList.add(q1);
		easyTag1.remove(0);
		
		String q2= mediumTag2.get(0);
		currentList.add(q2);
		mediumTag2.remove(0);
		
		String q3= hardTag3.get(0);
		currentList.add(q3);
		hardTag3.remove(0);
		
		String q4= easyTag4.get(0);
		currentList.add(q4);
		easyTag4.remove(0);
		
		String q5= mediumTag5.get(0);
		currentList.add(q5);
		mediumTag5.remove(0);
		
		String q6= hardTag6.get(0);
		currentList.add(q6);
		hardTag6.remove(0);
		
		if(i%2 == 0) {
			String q7= easyTag1.get(0);
			currentList.add(q7);
			easyTag1.remove(0);
			
			String q8= mediumTag2.get(0);
			currentList.add(q8);
			mediumTag2.remove(0);
			
			String q9= hardTag3.get(0);
			currentList.add(q9);
			hardTag3.remove(0);
		}else {
			String q7= easyTag4.get(0);
			currentList.add(q7);
			easyTag4.remove(0);
			
			String q8= mediumTag5.get(0);
			currentList.add(q8);
			mediumTag5.remove(0);
			
			String q9= hardTag6.get(0);
			currentList.add(q9);
			hardTag6.remove(0);
		}
		
		if(i % 6 == 1) {
			String q10= easyTag1.get(0);
			currentList.add(q10);
			easyTag1.remove(0);
		}else if(i % 6 == 2) {
			String q10= mediumTag2.get(0);
			currentList.add(q10);
			mediumTag2.remove(0);
		}else if(i % 6 == 3) {
			String q10= hardTag3.get(0);
			currentList.add(q10);
			hardTag3.remove(0);
		}else if(i % 6 == 4) {
			String q10= easyTag4.get(0);
			currentList.add(q10);
			easyTag4.remove(0);
		}else if(i % 6 == 5) {
			String q10= mediumTag5.get(0);
			currentList.add(q10);
			mediumTag5.remove(0);
		}else if(i % 6 == 0) {
			String q10= hardTag6.get(0);
			currentList.add(q10);
			hardTag6.remove(0);
		} 
		
	}

}
