import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TestCase {

	static String[] levels = { "EASY", "MEDIUM", "HARD" };

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		List<Tag> tags = new ArrayList<>();
		System.out.println("	T1	T2	T3	T4	T5	T6");
		System.out.print("EASY	");

		tags.add(new Tag(1, sc.nextInt(), levels[0]));
		tags.add(new Tag(2, sc.nextInt(), levels[0]));
		tags.add(new Tag(3, sc.nextInt(), levels[0]));
		tags.add(new Tag(4, sc.nextInt(), levels[0]));
		tags.add(new Tag(5, sc.nextInt(), levels[0]));
		tags.add(new Tag(6, sc.nextInt(), levels[0]));
		System.out.println();
		System.out.print("MEDIUM	");

		tags.add(new Tag(1, sc.nextInt(), levels[1]));
		tags.add(new Tag(2, sc.nextInt(), levels[1]));
		tags.add(new Tag(3, sc.nextInt(), levels[1]));
		tags.add(new Tag(4, sc.nextInt(), levels[1]));
		tags.add(new Tag(5, sc.nextInt(), levels[1]));
		tags.add(new Tag(6, sc.nextInt(), levels[1]));
		System.out.println();
		System.out.print("HARD	");
		
		tags.add(new Tag(1, sc.nextInt(), levels[2]));
		tags.add(new Tag(2, sc.nextInt(), levels[2]));
		tags.add(new Tag(3, sc.nextInt(), levels[2]));
		tags.add(new Tag(4, sc.nextInt(), levels[2]));
		tags.add(new Tag(5,sc.nextInt(), levels[2]));
		tags.add(new Tag(6, sc.nextInt(), levels[2]));

		generateTestCaseFile("TestCase1.txt", generateQuestionList(tags));

	}

	private static void generateTestCaseFile(String filename, List<String> questionList) throws Exception {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filename, "UTF-8");
			for (String string : questionList) {
				writer.println(string);
			}

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println("Error while generating file " + filename);
		} finally {
			writer.close();
		}
	}

	private static List<String> generateQuestionList(List<Tag> tags) {
		List<String> questionList = new ArrayList<>();
		int qNo=1;
		for (Tag tag : tags) {
			int q = 1;
			while (q <= tag.getTotalQuestion()) {
				String question = "Q" + qNo + "|" + tag.getDifficultyLevel() + "|Tag" + tag.getName();
				questionList.add(question);
				q++;
				qNo+=1;
			}
		}

		Collections.shuffle(questionList);
		return questionList;
	}
}

class Tag {
	private int name;
	private int totalQuestion;
	private String difficultyLevel;

	public Tag(int name, int totalQuestion, String difficultyLevel) {
		super();
		this.name = name;
		this.totalQuestion = totalQuestion;
		this.difficultyLevel = difficultyLevel;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public int getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	private int easy;
	private int medium;
	private int hard;
	
	Tag(){
		super();
	}
	public int getEasy() {
		return easy;
	}
	public void setEasy(int easy) {
		this.easy = easy;
	}
	public int getMedium() {
		return medium;
	}
	public void setMedium(int medium) {
		this.medium = medium;
	}
	public int getHard() {
		return hard;
	}
	public void setHard(int hard) {
		this.hard = hard;
	}
	
	public int sum() {
		return this.easy + this.medium + this.hard;
	}
}
