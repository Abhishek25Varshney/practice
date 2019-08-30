package shivali;

public class Question {
	private String questionNum;
	private String difficultyLevel;
	private String tag;

	public Question(String questionNum, String difficultyLevel, String tag) throws Exception{
		super();
		this.questionNum = questionNum;
		this.difficultyLevel = difficultyLevel;
		this.tag = tag;
	}

	public String getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(String questionNum) {
		this.questionNum = questionNum;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
