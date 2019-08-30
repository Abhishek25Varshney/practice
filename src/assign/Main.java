package assign;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.JFileChooser;



public class Main {
	// internal class to help in counting
    static class Count {
        int[] byDifficulty = {0, 0, 0};
        int[] byTag = {0, 0, 0, 0, 0, 0};
        int[][] bydiftag = new int[3][6];
        int total = 0;

        void insertQuestion(int dif, int tag) {
            bydiftag[dif][tag] ++;
            byDifficulty[dif] ++;
            byTag[tag] ++;
            total ++;
        }

        void removeQuestion(int dif, int tag) {
            bydiftag[dif][tag] --;
            byDifficulty[dif] --;
            byTag[tag] --;
            total --;
        }

        Integer[] frequentDifculties() {
            Integer[] nums = {0, 1, 2};
            Arrays.sort(nums, ((a, b) -> byDifficulty[b] - byDifficulty[a]));
            return nums;
        }

        Integer[] frequentTags() {
            Integer[] nums = {0, 1, 2, 3, 4, 5};
            Arrays.sort(nums, ((a, b) -> byTag[b] - byTag[a]));
//            System.out.println("bigTags :"+Arrays.toString(nums));
            return nums;
        }
    }

    public static int generateQuiz(Count questionPool) {
        Count totalQuestionsInQuiz = new Count();
        int numQuestions = 0;
        // 1st step. Fill all numbers
        for (int i = 0; i < 6; i++) {
            // choose the question with tag i and most common difficulty level.
            boolean found = false;
            for (int dif: questionPool.frequentDifculties()) {
                if (questionPool.bydiftag[dif][i] > 0) {
                    questionPool.removeQuestion(dif, i);
                    totalQuestionsInQuiz.insertQuestion(dif, i);
                    numQuestions ++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return 0;
            }
        }
        // 2nd step. Fill all difficulties.
        for (int i = 0; i < 3; i++) {
            while (totalQuestionsInQuiz.byDifficulty[i] < 2) {
                // choose the question with difficulty i and most common tag.
                boolean found = false;
                for (int tag : questionPool.frequentTags()) {
                    if (questionPool.bydiftag[i][tag] > 0) {
                        questionPool.removeQuestion(i, tag);
                        totalQuestionsInQuiz.insertQuestion(i, tag);
                        numQuestions ++;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return 0;
                }
            }
        }
        return numQuestions;
    }

    public static void main (String[] argv) throws IOException {
        Count questionSet = new Count();

        // file input
        JFileChooser jfFileChooser = new JFileChooser();
        jfFileChooser.setDialogTitle("choose input");
        jfFileChooser.setCurrentDirectory(new File("C:\\e-GMAT\1.txt"));
        jfFileChooser.showOpenDialog(null);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(jfFileChooser.getSelectedFile()));
       // console input
       // BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line =null;
        while ((line = bufferedReader.readLine())!=null){
        	//To avoid case conflicts
        	line = line.toLowerCase();
            if (!line.contains("|")) continue;
            if (line.startsWith("question#")) continue; // just in case.
            String[] tempStrArray = line.split("\\|");
            int tag = (tempStrArray[1].trim().charAt(3) - '1');
            int difficultyLevel;
            if (tempStrArray[2].trim().charAt(0) == 'E') {
                difficultyLevel = 0;
            } else if (tempStrArray[2].trim().charAt(0) == 'M') {
                difficultyLevel = 1;
            } else {
                difficultyLevel = 2;
            }

            questionSet.insertQuestion(difficultyLevel, tag);
        }
        bufferedReader.close();
        // Process.
        int remainingQuestionsToCompleteQuiz = 0;
        int quizzesSoFar = 0;

        // this while condition checks if there's enough questions left to make a quiz.
        while (remainingQuestionsToCompleteQuiz + 10 <= questionSet.total) {
            int questionsInQuiz = generateQuiz(questionSet);
            if (questionsInQuiz == 0) {
                // unable to find a new valid quiz
                break;
            }
            
            remainingQuestionsToCompleteQuiz += (10 - questionsInQuiz);
            quizzesSoFar += 1;
        }

        System.out.println("Qizzes possible: "+quizzesSoFar);
    }
}
