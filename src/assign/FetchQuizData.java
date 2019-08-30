package assign;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FetchQuizData {

	public static int tag1Flag = 0;
	public static int tag2Flag = 0;
	public static int tag3Flag = 0;
	public static int tag4Flag = 0;
	public static int tag5Flag = 0;
	public static int tag6Flag = 0;
	public static int easy = 0;
	public static int medium = 0;
	public static int difficult = 0;

	public static void main(String[] args) throws IOException {
		//File Path
		File file = new File("C:\\e-GMAT\\QUESTIONS.txt");
		HashMap<String, String> quizAndDifficultyHm = new HashMap<String, String>();
		quizAndDifficultyHm = fetchDataFromTxtFile(file, quizAndDifficultyHm);
		int quizzesCount = 0;
		quizzesCount = createQuizPossibilities(quizAndDifficultyHm);
		System.out.println("Possible Number of Quizzes = "+ quizzesCount);
	}

	/**
	 * Method to find possible number of quizzes
	 * @param quizAndDifficultyHm - HashMap with data from Quiz Data File
	 * @return - Count of quizzes
	 */
	private static int createQuizPossibilities(HashMap<String, String> quizAndDifficultyHm) {
		int quizzesCount = 0;
		HashMap<String, String> tempquizAndDifficultyHm = new HashMap<String, String>();
		tempquizAndDifficultyHm.putAll(quizAndDifficultyHm);
		List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String,String>>(quizAndDifficultyHm.entrySet());
		ArrayList<String> selectedData = new ArrayList<String>();
		Collections.shuffle(list);	

		while (tempquizAndDifficultyHm.size() >= 9) {
			if(tempquizAndDifficultyHm.size()< quizAndDifficultyHm.size()) {
				quizAndDifficultyHm = new HashMap<String, String>();
				quizAndDifficultyHm.putAll(tempquizAndDifficultyHm);
				list = new ArrayList<Map.Entry<String,String>>(quizAndDifficultyHm.entrySet());
				Collections.shuffle(list);
			}
			for (Map.Entry<String, String> quesNum : list) {
				String quesNumber = quesNum.getKey();
				String quesType = quesNum.getValue();
				if(tag1Flag < 2 && quesType.contains("Tag1")) {
					if((tag1Flag == 0 || easy < 2) && quesType.contains("EASY")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							easy++;
							tag1Flag++;
						}

					}
					else if((tag1Flag == 0 || medium < 2) && quesType.contains("MEDIUM")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							medium++;
							tag1Flag++;
						}

					}
					else if((tag1Flag == 0 || difficult < 2) && quesType.contains("DIFFICULT")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							difficult++;
							tag1Flag++;
						}

					}
				}
				else if(tag2Flag < 2 && quesType.contains("Tag2")) {

					if((tag2Flag == 0 || easy < 2) && quesType.contains("EASY")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							easy++;
							tag2Flag++;
						}
					}
					else if((tag2Flag == 0 || medium < 2) && quesType.contains("MEDIUM")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							medium++;
							tag2Flag++;
						}
					}
					else if((tag2Flag == 0 || difficult < 2) && quesType.contains("DIFFICULT")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							difficult++;
							tag2Flag++;
						}
					}
				}
				else if(tag3Flag < 2 && quesType.contains("Tag3")) {
					if((tag3Flag == 0 || easy < 2) && quesType.contains("EASY")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							easy++;
							tag3Flag++;
						}
					}
					else if((tag3Flag == 0 || medium < 2) && quesType.contains("MEDIUM")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							medium++;
							tag3Flag++;
						}
					}
					else if((tag3Flag == 0 || difficult < 2) && quesType.contains("DIFFICULT")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							difficult++;
							tag3Flag++;
						}
					}
				}
				else if(tag4Flag < 2 && quesType.contains("Tag4")) {
					if((tag4Flag == 0 || easy < 2) && quesType.contains("EASY")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							easy++;
							tag4Flag++;
						}
					}
					else if((tag4Flag == 0 || medium < 2) && quesType.contains("MEDIUM")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							medium++;
							tag4Flag++;
						}
					}
					else if((tag4Flag == 0 || difficult < 2) && quesType.contains("DIFFICULT")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							difficult++;
							tag4Flag++;
						}
					}
				}
				else if(tag5Flag < 2 && quesType.contains("Tag5")) {
					if((tag5Flag == 0 || easy < 2) && quesType.contains("EASY")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							easy++;
							tag5Flag++;
						}
					}
					else if((tag5Flag == 0 || medium < 2) && quesType.contains("MEDIUM")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							medium++;
							tag5Flag++;
						}
					}
					else if((tag5Flag == 0 || difficult < 2) && quesType.contains("DIFFICULT")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							difficult++;
							tag5Flag++;
						}
					}
				}
				else if(tag6Flag < 2 && quesType.contains("Tag6")) {

					if((tag6Flag == 0 || easy < 2) && quesType.contains("EASY")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							easy++;
							tag6Flag++;
						}
					}
					else if((tag6Flag == 0 || medium < 2) && quesType.contains("MEDIUM")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							medium++;
							tag6Flag++;
						}

					}
					else if((tag6Flag == 0 || difficult < 2) && quesType.contains("DIFFICULT")) {
						if(!selectedData.contains(quesNumber)) {
							selectedData.add(quesNumber);
							difficult++;
							tag6Flag++;
						}
					}
				}
				if((tag1Flag > 0) && (tag2Flag > 0) && (tag3Flag > 0) && (tag4Flag > 0) && (tag5Flag > 0) && (tag6Flag > 0) && easy >= 2 && difficult >= 2 && medium >= 2) {
					if(selectedData.size()==10) {
						quizzesCount++;
						cleanUpData();
						System.out.println("Combination "+ quizzesCount + " = "+selectedData);
						for (String deleteValue : selectedData) {
							tempquizAndDifficultyHm.remove(deleteValue);
						}
						selectedData = new ArrayList<String>();
						break;
					}else if(selectedData.size()<10){
						if(!selectedData.contains(quesNumber))
							selectedData.add(quesNumber);

					}

				}else if(selectedData.size()>10) {
					cleanUpData();
					break;

				}

			}
			if(tempquizAndDifficultyHm.size() == quizAndDifficultyHm.size()) {
				cleanUpData();
				tempquizAndDifficultyHm = new HashMap<String, String>();
				quizAndDifficultyHm = new HashMap<String, String>();
			}
		}
		return quizzesCount;
	}

	/**
	 * Method to fetch data from Text file and storing data in HashMap with key as question number and value as Tag and Difficulty Level.
	 * @param file - File from Desktop
	 * @param quizAndDifficultyHm - HashMap to be fetched from file
	 * @return - HashMap
	 * @throws IOException
	 */
	private static HashMap<String, String> fetchDataFromTxtFile(File file, HashMap<String, String> quizAndDifficultyHm ) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String quizData;
		while((quizData = br.readLine()) != null) {
			if(quizData.contains("|")) {
				String[] splitData = quizData.split("\\|");
				String hmKey = splitData[0];
				String hmValue = splitData[1]+splitData[2];
				quizAndDifficultyHm.put(hmKey, hmValue);
			}
		}
		return quizAndDifficultyHm;		
	}

	/**
	 * Method to cleanup variables used in code
	 */
	private static void cleanUpData() {
		tag1Flag = 0;
		tag2Flag = 0;
		tag3Flag = 0;
		tag4Flag = 0;
		tag5Flag = 0;
		tag6Flag = 0;
		easy = 0;
		medium = 0;
		difficult = 0;
	}
}
