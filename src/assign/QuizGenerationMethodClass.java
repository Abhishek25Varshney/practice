package assign;

import java.util.ArrayList;
import java.util.HashMap;

public class QuizGenerationMethodClass {
	public QuizGenerationMethodClass() {}
	  
	  public int generateQuiz(String fileName)
	  {
	    ArrayList[][] Tag = new ArrayList[3][6];
	    
	    java.io.FileInputStream fis = null;
	    java.io.BufferedReader reader = null;
	    HashMap<String, Integer> diff = new HashMap();
	    diff.put("easy", Integer.valueOf(0));
	    diff.put("medium", Integer.valueOf(1));
	    diff.put("hard", Integer.valueOf(2));
	    
	    try
	    {
	      fis = new java.io.FileInputStream(fileName);
	      reader = new java.io.BufferedReader(new java.io.InputStreamReader(fis));
	      
	      String line = reader.readLine();
	      while (line != null) {
	        if (!line.equalsIgnoreCase("")) {
	          String difficulty = line.split("\\|")[1];
	          int diffIndex = ((Integer)diff.get(difficulty.trim().toLowerCase())).intValue();
	          
	          String tagId = line.split("\\|")[2];
	          int i = Integer.parseInt(tagId.trim().toLowerCase().split("tag")[1]) - 1;
	          int questionId = Integer.parseInt(line.split("\\|")[0].trim().toLowerCase().split("q")[1]);
	          if (Tag[diffIndex][(i % 6)] == null)
	            Tag[diffIndex][(i % 6)] = new ArrayList();
	          Tag[diffIndex][(i % 6)].add(Integer.valueOf(questionId));
	        }
	        line = reader.readLine();
	      }
	    }
	    catch (Exception e) {
	      return -1;
	    }
	    ArrayList<ArrayList<Integer>> quizFormed = new ArrayList();
	    for (;;) {
	      int[] dc = new int[3];
	      int k = 0;
	      ArrayList<Integer> quizForming = new ArrayList();
	      boolean addition = false;
	      
	      int check = Tag[0][0].size();
	      int remQ = 0;
	      for (int p = 0; p < 3; p++)
	      {
	        for (int q = 0; q < 6; q++)
	        {
	          if (Tag[p][q].size() > check)
	          {
	            remQ = q;
	            check = Tag[p][q].size();
	          }
	        }
	      }
	      
	      for (int i = remQ; i < 10 + remQ + k; i++)
	      {
	        int a = Tag[(i % 3)][(i % 6)] != null ? Tag[(i % 3)][(i % 6)].size() : 0;
	        int b = Tag[((i + 1) % 3)][(i % 6)] != null ? Tag[((i + 1) % 3)][(i % 6)].size() : 0;
	        int c = Tag[((i + 2) % 3)][(i % 6)] != null ? Tag[((i + 2) % 3)][(i % 6)].size() : 0;
	        if ((a >= b) && (a >= c) && (dc[(i % 3)] < 4) && (Tag[(i % 3)][(i % 6)] != null) && (!Tag[(i % 3)][(i % 6)].isEmpty()))
	        {
	          Integer qId = (Integer)Tag[(i % 3)][(i % 6)].remove(0);
	          quizForming.add(qId);
	          addition = true;
	          dc[(i % 3)] += 1;
	        }
	        else if ((b >= a) && (b >= c) && (dc[((i + 1) % 3)] < 4) && (Tag[((i + 1) % 3)][(i % 6)] != null) && (!Tag[((i + 1) % 3)][(i % 6)].isEmpty()))
	        {
	          Integer qId = (Integer)Tag[((i + 1) % 3)][(i % 6)].remove(0);
	          quizForming.add(qId);
	          addition = true;
	          dc[((i + 1) % 3)] += 1;
	        }
	        else if ((c >= b) && (c >= a) && (dc[((i + 2) % 3)] < 4) && (Tag[((i + 2) % 3)][(i % 6)] != null) && (!Tag[((i + 2) % 3)][(i % 6)].isEmpty()))
	        {
	          Integer qId = (Integer)Tag[((i + 2) % 3)][(i % 6)].remove(0);
	          quizForming.add(qId);
	          addition = true;
	          dc[((i + 2) % 3)] += 1;
	        }
	        else if ((dc[((i + 1) % 3)] < 4) && (Tag[((i + 1) % 3)][(i % 6)] != null) && (!Tag[((i + 1) % 3)][(i % 6)].isEmpty()))
	        {
	          Integer qId = (Integer)Tag[((i + 1) % 3)][(i % 6)].remove(0);
	          quizForming.add(qId);
	          addition = true;
	          dc[((i + 1) % 3)] += 1;
	        }
	        else if ((dc[(i % 3)] < 4) && (Tag[(i % 3)][(i % 6)] != null) && (!Tag[(i % 3)][(i % 6)].isEmpty()))
	        {

	          Integer qId = (Integer)Tag[(i % 3)][(i % 6)].remove(0);
	          quizForming.add(qId);
	          addition = true;
	          dc[(i % 3)] += 1;
	        }
	        else if ((dc[((i + 2) % 3)] < 4) && (Tag[((i + 2) % 3)][(i % 6)] != null) && (!Tag[((i + 2) % 3)][(i % 6)].isEmpty()))
	        {
	          Integer qId = (Integer)Tag[((i + 2) % 3)][(i % 6)].remove(0);
	          quizForming.add(qId);
	          addition = true;
	          dc[((i + 2) % 3)] += 1;
	        }
	        if ((!addition) && (k < 3))
	        {
	          k++;
	        }
	      }
	      if (quizForming.size() != 10)
	        break;
	      quizFormed.add(quizForming);
	    }
	    return quizFormed.size();
	  }
}
