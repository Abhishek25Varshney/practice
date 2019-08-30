package shivali;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Solution {

	public static void main(String[] args) throws Exception {

		List<Question> questionList = new ArrayList<>();

		File file = new File("C:\\e-GMAT\\QUESTIONS.txt");
		// change to give the file location
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] str = line.split(Pattern.quote("|"));
				Question question = new Question(str[0], str[1], str[2]);
				questionList.add(question);
			}

			int maxValue = questionList.size() / 10;
			int maxTagVal = currentMaxTagCount(questionList);
			int maxDiffLevelVal = currentMaxDiffLevelCount(questionList);

			if (maxTagVal < maxDiffLevelVal) {
				if (maxTagVal > maxValue) {
					System.out.println(maxValue);
				} else {
					System.out.println(maxTagVal);
				}
			} else {
				if (maxDiffLevelVal > maxValue) {
					System.out.println(maxValue);
				} else {
					System.out.println(maxDiffLevelVal);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static Integer currentMaxTagCount(List<Question> questions) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Tag1", 0);
		map.put("Tag2", 0);
		map.put("Tag3", 0);
		map.put("Tag4", 0);
		map.put("Tag5", 0);
		map.put("Tag6", 0);

		for (Question q : questions) {

			if (q.getTag().equalsIgnoreCase("Tag1")) {
				map.put("Tag1", map.get("Tag1") + 1);
			} else if (q.getTag().equalsIgnoreCase("Tag2")) {
				map.put("Tag2", map.get("Tag2") + 1);
			} else if (q.getTag().equalsIgnoreCase("Tag3")) {
				map.put("Tag3", map.get("Tag3") + 1);
			} else if (q.getTag().equalsIgnoreCase("Tag4")) {
				map.put("Tag4", map.get("Tag4") + 1);
			} else if (q.getTag().equalsIgnoreCase("Tag5")) {
				map.put("Tag5", map.get("Tag5") + 1);
			} else if (q.getTag().equalsIgnoreCase("Tag6")) {
				map.put("Tag6", map.get("Tag6") + 1);
			}

		}

		List<Integer> intlist = new ArrayList<Integer>(map.values());
		Collections.sort(intlist);
		return intlist.get(0);
	}

	static Integer currentMaxDiffLevelCount(List<Question> questions) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("EASY", 0);
		map.put("MEDIUM", 0);
		map.put("HARD", 0);

		for (Question q : questions) {

			if (q.getDifficultyLevel().equals("EASY")) {
				map.put("EASY", map.get("EASY") + 1);
			} else if (q.getDifficultyLevel().equals("MEDIUM")) {
				map.put("MEDIUM", map.get("MEDIUM") + 1);
			} else if (q.getDifficultyLevel().equals("HARD")) {
				map.put("HARD", map.get("HARD") + 1);
			}

		}

		List<Integer> intlist = new ArrayList<Integer>(map.values());
		Collections.sort(intlist);
		int minval = intlist.get(0);
		int retval = 0;
		if (minval % 2 == 0) {
			retval = minval / 2;
		} else {
			retval = (minval - 1) / 2;
		}
		return retval;
	}
}
