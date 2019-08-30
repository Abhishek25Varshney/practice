package reddy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import reddy.Difficulty;
import reddy.Question;
import reddy.Quiz;
import reddy.Tag;
import reddy.Tags;

class Test implements Comparator<List<Question>> {
    @Override
    public int compare(List<Question> o1, List<Question> o2) {
        return o2.size() - o1.size();
    }
}

public class Main {
    public static void main(String args[]) throws IOException {
        
        
        // input classification
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C://Users//sapallam//Desktop//e-GMAT_TESTCASE_5.txt"));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
      
        while((line =bufferedReader.readLine())!=null)
        {
        	stringBuffer.append(line).append("\n");
        }
         
        String entireFileText = stringBuffer.toString();
        String[] arr = entireFileText.split("\\r?\\n");
        int N=arr.length;
        Map<Tag, Tags> tagsMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String input = arr[i];
            String temp[] = input.split("\\|");
            Question question = new Question(temp[0],
                    Difficulty.valueOf(Difficulty.class, temp[1]), Tag.valueOf(Tag.class, temp[2]));
            if (tagsMap.containsKey(question.getTag())) {
                Tags tags = tagsMap.get(question.getTag());
       
                if (tags.getQuestions().containsKey(question.getDifficulty())) {
                	ArrayList<Question> list = tags.getQuestions().get(question.getDifficulty());
                    list.add(question);
                    tags.getQuestions().put(question.getDifficulty(), list);
                    tagsMap.put(question.getTag(), tags);
                } else {
                    ArrayList<Question> arrayList = new ArrayList<>();
                    arrayList.add(question);
                    tags.getQuestions().put(question.getDifficulty(), arrayList);
                    tagsMap.put(question.getTag(), tags);
                }
            } else {
                Tags tags = new Tags();
                ArrayList<Question> arrayList = new ArrayList<>();
                arrayList.add(question);
                tags.getQuestions().put(question.getDifficulty(), arrayList);
                tagsMap.put(question.getTag(), tags);
            }
        }

        // creating quiz
        Queue<ArrayList<Question>> allQueue = new PriorityQueue<>(new Test());
        tagsMap.forEach((tag, tags) -> {
            Arrays.stream(Difficulty.values()).forEach(difficulty -> {
            	if(tags.getQuestions().containsKey(difficulty))
            		allQueue.add(tags.getQuestions().get(difficulty));
            });
        });
        
        List<Quiz> quizzes = new ArrayList<>();

        boolean invalid = false;
        while (!invalid) {
            Quiz quiz = new Quiz();
            int easyValue = 0;
            int mediumValue = 0;
            int difficultValue = 0;
            Queue<ArrayList<Question>> easyQueue = new PriorityQueue<>(new Test());
            Queue<ArrayList<Question>> mediumQueue = new PriorityQueue<>(new Test());
            Queue<ArrayList<Question>> difficultQueue = new PriorityQueue<>(new Test());
            for (Tag tag : Tag.values()) {
            	ArrayList<Question> easy = null;
            	if(tagsMap.containsKey(tag) && tagsMap.get(tag).getQuestions().containsKey(Difficulty.EASY))
            			easy = tagsMap.get(tag).getQuestions().get(Difficulty.EASY);
                ArrayList<Question> medium = null;
                if(tagsMap.containsKey(tag) && tagsMap.get(tag).getQuestions().containsKey(Difficulty.MEDIUM))
        			medium = tagsMap.get(tag).getQuestions().get(Difficulty.MEDIUM);
                ArrayList<Question> difficult = null;
                if(tagsMap.containsKey(tag) && tagsMap.get(tag).getQuestions().containsKey(Difficulty.HARD))
        			difficult = tagsMap.get(tag).getQuestions().get(Difficulty.HARD);
                
                if(easy != null)
                	easyQueue.add(easy);
                if(medium != null)
                	mediumQueue.add(medium);
                if(difficult != null)
                	difficultQueue.add(difficult);
                int easySize = easy == null || easy.size() == 0 ? Integer.MAX_VALUE : easy.size();
                int mediumSize = medium == null || medium.size() == 0 ? Integer.MAX_VALUE : medium.size();
                int difficultSize = difficult == null || difficult.size() == 0 ? Integer.MAX_VALUE : difficult.size();
                //System.out.println(easySize + " " + mediumSize + " " + difficultSize);
                if (Integer.MAX_VALUE == mediumSize || mediumSize == Integer.MAX_VALUE || easySize == Integer.MAX_VALUE) {
                    invalid = true;
                    break;
                } else if (easySize <= mediumSize && easySize <= difficultSize) {
                    Question question = easy.remove(0);
                    quiz.questions.add(question);
                    easyValue++;
                } else if (mediumSize <= easySize && mediumSize <= difficultSize) {
                    Question question = medium.remove(0);
                    quiz.questions.add(question);
                    mediumValue++;
                } else if (difficultSize <= easySize && difficultSize <= mediumSize) {
                    Question question = difficult.remove(0);
                    quiz.questions.add(question);
                    difficultValue++;
                }

            }
            if (invalid) {
                continue;
            }
            while (easyValue < 2) {
                if (easyQueue.isEmpty()) {
                    invalid = true;
                    break;
                }
                ArrayList<Question> temp = easyQueue.poll();
                Tag tag = temp.get(0).getTag();
                quiz.questions.add(temp.remove(0));
                tagsMap.get(tag).getQuestions().put(Difficulty.EASY, temp);
                easyValue++;
            }
            if (invalid) {

                continue;
            }
            while (mediumValue < 2) {
                if (mediumQueue.isEmpty()) {
                    invalid = true;
                    break;
                }
                ArrayList<Question> temp = mediumQueue.poll();
                if(temp.isEmpty()) {
                    invalid = true;
                    break;
                }
                Tag tag = temp.get(0).getTag();
                quiz.questions.add(temp.remove(0));
                tagsMap.get(tag).getQuestions().put(Difficulty.MEDIUM, temp);
                mediumValue++;
            }
            if (invalid) {
                continue;
            }
            while (difficultValue < 2) {
                if (difficultQueue.isEmpty()) {
                    invalid = true;
                    break;
                }
                ArrayList<Question> temp = difficultQueue.poll();
                if(temp.isEmpty()) {
                    invalid = true;
                    break;
                }
                Tag tag = temp.get(0).getTag();
                quiz.questions.add(temp.remove(0));
                tagsMap.get(tag).getQuestions().put(Difficulty.HARD, temp);
                difficultValue++;
            }
            if (invalid) {
                continue;
            }
            int val = quiz.questions.size();
           
            while (val < 10) {
                if (allQueue.isEmpty()) {
                    invalid = true;
                    break;
                }
                ArrayList<Question> list = allQueue.poll();
                if(list.isEmpty()) {
                    invalid = true;
                    break;
                }
                Question question = list.remove(0);
                quiz.questions.add(question);
                Tags tags = tagsMap.get(question.getTag());
                tags.getQuestions().put(question.getDifficulty(), list);
                tagsMap.put(question.getTag(), tags);
                allQueue.add(list);
                val++;
            }
            if (invalid) {
                continue;
            }
            quizzes.add(quiz);

        }
        System.out.println(quizzes.size());
    }

}
