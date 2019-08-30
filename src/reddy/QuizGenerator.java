package reddy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class QuizGenerator {
	private String[] difficultyLevels = { "EASY", "MEDIUM", "HARD" };
	private String[] tags = { "tag1", "tag2", "tag3", "tag4", "tag5", "tag6" };
	private Map<String, Integer> tagWiseGrouping = new java.util.HashMap();
	private Map<String, Integer> difficultyWiseGrouping = new java.util.HashMap();
	private int tagWithLeastQuestions;

	public QuizGenerator() {
	}

	public static void main(String[] args) {
		QuizGenerator generator = new QuizGenerator();
		String errMsg = "";
//		if (args.length != 0) {
			File file = new File("TestCase1.txt");
			if (!file.exists()) {
				errMsg = "Please enter a valid file name";
			} else {
				generator.parseFileContent(file);
				generator.createQuiz();
			}
//		} else {
//			errMsg = "Please provide filename as 1st argument";
//		}
		if (!errMsg.isEmpty())
			System.out.println(errMsg);
	}

	private int diffWithLeastQuestions;
	private int totalQuestions;

	private void createQuiz() {
		int minSetPossible = 0;
		if (tagWithLeastQuestions < diffWithLeastQuestions / 2) {
			minSetPossible = tagWithLeastQuestions;
		} else
			minSetPossible = diffWithLeastQuestions / 2;
		int maxSetPossible = totalQuestions / 10;
		System.out.println(minSetPossible > maxSetPossible ? maxSetPossible : minSetPossible);
	}

	private void getDifficultyWithLeastQuestions() {
		String min = "EASY";
		for (String difficulty : difficultyWiseGrouping.keySet()) {
			if ((!difficulty.equalsIgnoreCase("EASY")) && (((Integer) difficultyWiseGrouping.get(difficulty))
					.intValue() < ((Integer) difficultyWiseGrouping.get(min)).intValue())) {
				min = difficulty;
			}
		}

		diffWithLeastQuestions = ((Integer) difficultyWiseGrouping.get(min)).intValue();
	}

	private void getTagWithLeastQuestions() {
		String min = "tag1";
		for (String tag : tagWiseGrouping.keySet()) {
			if ((!tag.equalsIgnoreCase("tag1")) && (((Integer) tagWiseGrouping.get(tag))
					.intValue() < ((Integer) tagWiseGrouping.get(min)).intValue())) {
				min = tag;
			}
		}

		tagWithLeastQuestions = ((Integer) tagWiseGrouping.get(min)).intValue();
	}

	private void parseFileContent(File file) {
		String[] arrayOfString1 = tags;
		int i = arrayOfString1.length;
		for (int localObject1 = 0; localObject1 < i; localObject1++) {
			String tag = arrayOfString1[localObject1];
			tagWiseGrouping.put(tag, Integer.valueOf(0));
		}

		int diffQuesCount = 0;
		String[] arrayOfString2 = difficultyLevels;
		int localObject1 = arrayOfString2.length;
		for (int tag = 0; tag < localObject1; tag++) {
			String difficulty = arrayOfString2[tag];
			difficultyWiseGrouping.put(difficulty, Integer.valueOf(diffQuesCount));
		}
		try {
			FileReader reader = new FileReader(file);
			String localObject2 = null;
			try {
				BufferedReader bufferedReader = new BufferedReader(reader);
				String s;
				while ((s = bufferedReader.readLine()) != null) {
					String[] lineContent = s.split("\\|");
					String difficulty = lineContent[1];
					String tag = lineContent[2];
					int quesCount = ((Integer) tagWiseGrouping.get(tag.toLowerCase())).intValue();
					tagWiseGrouping.put(tag.toLowerCase(), Integer.valueOf(quesCount + 1));
					quesCount = ((Integer) difficultyWiseGrouping.get(difficulty.toUpperCase())).intValue();
					difficultyWiseGrouping.put(difficulty.toUpperCase(), Integer.valueOf(quesCount + 1));
					totalQuestions += 1;
				}
			} catch (Throwable localThrowable1) {
				localObject2 = localThrowable1.toString();
				throw localThrowable1;

			} finally {

//				if (localObject2 != null)
//					try {
//						reader.close();
//					} catch (Throwable localThrowable2) {
//						((Throwable) localObject2).addSuppressed(localThrowable2);
//					}
//				else
//					reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		getTagWithLeastQuestions();
		getDifficultyWithLeastQuestions();
	}
}
