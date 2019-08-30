package assign;

public class QuizGeneration {

	public static void main(String[] args) throws Exception {
		QuizGenerationMethodClass obj = new QuizGenerationMethodClass();
		String fileName = null;

		int result = -2;
		if (args.length > 0)
			fileName = args[0];
		
		fileName="C://e-GMAT//8.txt";
		if (fileName != null)
			result = obj.generateQuiz(fileName);
		if (result == -1) {
			System.out.println("File not found");
		} else if (result == -2) {
			System.out.println("Please provide full filepath in first argument");
		} else {
			System.out.println("Max. Number of quizes: " + result);
		}
	}

}
