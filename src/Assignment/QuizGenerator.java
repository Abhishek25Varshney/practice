package Assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QuizGenerator {
	public static void main(String args[]) throws IOException {

		// input classification
		BufferedReader bufferedReader = new BufferedReader(new FileReader("TestCase1.txt"));
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line).append("\n");
		}

		String entireFileText = stringBuffer.toString();
		String[] arr = entireFileText.split("\\r?\\n");
		int N = arr.length;

		// all Questions
		List<Question> questionList = new ArrayList<>();
		// difficultyMap
		Map<Difficulty, Integer> diffultyWiseMap = new HashMap<>();
		// tagMap
		Map<Tag, Integer> tagWiseMap = new HashMap<>();
		// Difficulty|Tag--->Question Map
		Map<String, List<Question>> questionMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String input = arr[i];
			String temp[] = input.split("\\|");
			Question question = new Question(temp[0], Difficulty.valueOf(Difficulty.class, temp[1]),
					Tag.valueOf(Tag.class, temp[2]));
			questionList.add(question);

			if (!diffultyWiseMap.containsKey(question.getDifficulty())) {
				diffultyWiseMap.put(question.getDifficulty(), 0);
			}
			int value = diffultyWiseMap.get(question.getDifficulty());
			diffultyWiseMap.put(question.getDifficulty(), value + 1);

			if (!tagWiseMap.containsKey(question.getTag())) {
				tagWiseMap.put(question.getTag(), 0);
			}
			value = tagWiseMap.get(question.getTag());
			tagWiseMap.put(question.getTag(), value + 1);

			String key = question.getDifficulty() + "|" + question.getTag();
			if (!questionMap.containsKey(key)) {
				questionMap.put(key, new ArrayList<>());
			}
			questionMap.get(key).add(question);
		}
		List<List<Question>> quizzes = new ArrayList<>();
		int quizNo = 1;
		while (checkQuizPossible(diffultyWiseMap, tagWiseMap, questionMap)) {
			List<Question> quiz = createQuiz(diffultyWiseMap, tagWiseMap, questionMap, questionList);
			System.out.println("*********QUIZ NO " + quizNo + "********");
			for (Question question : quiz) {
				System.out.println(question.toString());
			}
			quizzes.add(quiz);
			System.out.println("*********QUIZ ENDS********");
			quizNo += 1;
			System.out.println("==========QUESTION LEFT " + questionList.size() + "==============");
		}
		System.out.println("Output: " + quizzes.size());

	}

	private static boolean checkQuizPossible(Map<Difficulty, Integer> diffultyWiseMap, Map<Tag, Integer> tagWiseMap,
			Map<String, List<Question>> questionMap) {
		boolean isDiffiultyConstraintPass = false;
		if (diffultyWiseMap.get(Difficulty.EASY) != null && diffultyWiseMap.get(Difficulty.MEDIUM) != null
				&& diffultyWiseMap.get(Difficulty.HARD) != null && diffultyWiseMap.get(Difficulty.EASY) >= 2
				&& diffultyWiseMap.get(Difficulty.MEDIUM) >= 2 && diffultyWiseMap.get(Difficulty.HARD) >= 2) {
			isDiffiultyConstraintPass = true;
		}

		boolean isTagConstraintPass = false;
		if (tagWiseMap.get(Tag.Tag1) != null && tagWiseMap.get(Tag.Tag2) != null && tagWiseMap.get(Tag.Tag3) != null
				&& tagWiseMap.get(Tag.Tag4) != null && tagWiseMap.get(Tag.Tag5) != null
				&& tagWiseMap.get(Tag.Tag6) != null && tagWiseMap.get(Tag.Tag1) >= 1 && tagWiseMap.get(Tag.Tag2) >= 1
				&& tagWiseMap.get(Tag.Tag3) >= 1 && tagWiseMap.get(Tag.Tag4) >= 1 && tagWiseMap.get(Tag.Tag5) >= 1
				&& tagWiseMap.get(Tag.Tag6) >= 1) {
			isTagConstraintPass = true;
		}

		boolean isTotalQuestionConstraintPass = false;
		int count = 0;
		for (Map.Entry<String, List<Question>> entry : questionMap.entrySet()) {
			count += entry.getValue().size();
		}

		if (count >= 10) {
			isTotalQuestionConstraintPass = true;
		}

		return isDiffiultyConstraintPass && isTagConstraintPass && isTotalQuestionConstraintPass;

	}

	private static List<Question> createQuiz(Map<Difficulty, Integer> diffultyWiseMap, Map<Tag, Integer> tagWiseMap,
			Map<String, List<Question>> questionMap, List<Question> questionList) {

		Map<String, Integer> constraintMap = new HashMap<String, Integer>() {
			{
				put("Tag1", 0);
				put("Tag2", 0);
				put("Tag3", 0);
				put("Tag4", 0);
				put("Tag5", 0);
				put("Tag6", 0);
				put("EASY", 0);
				put("MEDIUM", 0);
				put("HARD", 0);
			}
		};

		List<List<Question>> allQuestion = new ArrayList<>();

		List<Question> quiz = new LinkedList<>();
		for (Map.Entry<String, List<Question>> entry : questionMap.entrySet()) {
			allQuestion.add(entry.getValue());

		}

		quiz=pickQuestion(allQuestion, constraintMap, diffultyWiseMap, tagWiseMap, questionMap, questionList);

		return quiz;

	}

	private static List<Question> pickQuestion(List<List<Question>> allQuestion, Map<String, Integer> constraintMap,
			Map<Difficulty, Integer> diffultyWiseMap, Map<Tag, Integer> tagWiseMap,
			Map<String, List<Question>> questionMap, List<Question> questionList) {
		List<Question> quiz = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			Collections.sort(allQuestion, new Comparator<List<Question>>() {
				public int compare(List<Question> a1, List<Question> a2) {
					return a2.size() - a1.size();
				}
			});

			for (List<Question> list : allQuestion) {
				if (list.size() == 0) {
					break;
				}
				Question q = list.get(0);
				boolean maxConstraintAchieved = checkIfMaxConstraintSatisfied(constraintMap, q);
				if (maxConstraintAchieved) {
					quiz.add(q);
					updateConstraintMap(constraintMap, q);
					updateQuestionDataAndMap(diffultyWiseMap, tagWiseMap, questionMap, q, questionList);
				} else {
					continue;
				}
			}
		}

		for (Map.Entry<String, Integer> entry : constraintMap.entrySet()) {
			if (entry.getKey().contains("Tag")) {
				if (entry.getValue() < 1) {

					List<List<Question>> questionListPerTag = new ArrayList<>();
					for (Map.Entry<String, List<Question>> e : questionMap.entrySet()) {
						if (e.getKey().contains(entry.getKey())) {
							questionListPerTag.add(e.getValue());
						}
					}
					Collections.sort(questionListPerTag, new Comparator<List<Question>>() {
						public int compare(List<Question> a1, List<Question> a2) {
							return a2.size() - a1.size();
						}
					});
					List<Question> l = questionListPerTag.get(0);
					if (l.size() > 0) {
						quiz.add(l.get(0));
						updateConstraintMap(constraintMap, l.get(0));
						updateQuestionDataAndMap(diffultyWiseMap, tagWiseMap, questionMap, l.get(0), questionList);
					}

				}
			} else {
				if (entry.getValue() < 2) {
					List<List<Question>> questionListPerTag = new ArrayList<>();
					for (Map.Entry<String, List<Question>> e : questionMap.entrySet()) {
						if (e.getKey().contains(entry.getKey())) {
							questionListPerTag.add(e.getValue());
						}
					}
					Collections.sort(questionListPerTag, new Comparator<List<Question>>() {
						public int compare(List<Question> a1, List<Question> a2) {
							return a2.size() - a1.size();
						}
					});
					List<Question> l = questionListPerTag.get(0);
					if (l.size() > 0) {
						quiz.add(l.get(0));
						updateConstraintMap(constraintMap, l.get(0));
						updateQuestionDataAndMap(diffultyWiseMap, tagWiseMap, questionMap, l.get(0), questionList);
					}
				}
			}
		}

		int questionSelectedToFulFillConstraint = quiz.size();
		for (int i = 0; i < (10 - questionSelectedToFulFillConstraint); i++) {
			Collections.sort(allQuestion, new Comparator<List<Question>>() {
				public int compare(List<Question> a1, List<Question> a2) {
					return a2.size() - a1.size();
				}
			});
			Question question = allQuestion.get(0).get(0);
			quiz.add(question);
			updateQuestionDataAndMap(diffultyWiseMap, tagWiseMap, questionMap, question, questionList);
			for (List<Question> list : allQuestion) {
				list.remove(question);
			}
		}
		

		return quiz;
	}

	private static boolean checkIfMaxConstraintSatisfied(Map<String, Integer> constraintMap, Question question) {
		Difficulty difficultyLevel = question.getDifficulty();
		Tag tag = question.getTag();

		int difficultyCount = constraintMap.get(difficultyLevel.toString());
		int tagCount = constraintMap.get(tag.toString());

		boolean difficultyConstraintPass = false;
		if (difficultyCount < 2) {
			difficultyConstraintPass = true;
		}

		boolean tagConstraintPass = false;
		if (tagCount < 1) {
			tagConstraintPass = true;
		}

		return difficultyConstraintPass && tagConstraintPass;

	}

	private static void updateConstraintMap(Map<String, Integer> constraintMap, Question q) {
		if (!constraintMap.containsKey(q.getDifficulty().toString())) {
			constraintMap.put(q.getDifficulty().toString(), 0);
		}
		int value = constraintMap.get(q.getDifficulty().toString());
		constraintMap.put(q.getDifficulty().toString(), value + 1);

		if (!constraintMap.containsKey(q.getTag().toString())) {
			constraintMap.put(q.getTag().toString(), 0);
		}
		value = constraintMap.get(q.getTag().toString());
		constraintMap.put(q.getTag().toString(), value + 1);
	}

	private static void updateQuestionDataAndMap(Map<Difficulty, Integer> diffultyWiseMap, Map<Tag, Integer> tagWiseMap,
			Map<String, List<Question>> questionMap,Question question, List<Question> questionList) {
			int value = diffultyWiseMap.get(question.getDifficulty());
			diffultyWiseMap.put(question.getDifficulty(), value - 1);
			value = tagWiseMap.get(question.getTag());
			tagWiseMap.put(question.getTag(), value - 1);
			for (Map.Entry<String, List<Question>> entry : questionMap.entrySet()) {
				entry.getValue().remove(question);
			}

		questionList.remove(question);
	}

}
