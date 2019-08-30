package assign;

import java.io.BufferedReader;

public class QuizCount
{
  public QuizCount() {}
  
  public static void main(String[] args) throws java.io.IOException
  {
    java.io.File file = new java.io.File("C:\\e-GMAT\\QUESTIONS.txt");
    BufferedReader br = new BufferedReader(new java.io.FileReader(file));
    StringBuffer stringBuffer = new StringBuffer();
	String line = null;

	while ((line = br.readLine()) != null) {
		stringBuffer.append(line).append("\n");
	}

	String entireFileText = stringBuffer.toString();
	String[] arr = entireFileText.split("\\r?\\n");
	int N = arr.length;
    int[] type = new int[9];
    
    for (int i = 0; i < N; i++)
    {
      String[] ques = arr[i].split("\\|");
      if (ques[1].equals("HARD"))
        type[0] += 1;
      if (ques[1].equals("MEDIUM"))
        type[1] += 1;
      if (ques[1].equals("EASY")) {
        type[2] += 1;
      }
      if (ques[2].equals("Tag1"))
        type[3] += 1;
      if (ques[2].equals("Tag2"))
        type[4] += 1;
      if (ques[2].equals("Tag3"))
        type[5] += 1;
      if (ques[2].equals("Tag4"))
        type[6] += 1;
      if (ques[2].equals("Tag5"))
        type[7] += 1;
      if (ques[2].equals("Tag6"))
        type[8] += 1;
    }
    br.close();
    int min = 1000;
    
    type[0] /= 2;
    type[1] /= 2;
    type[2] /= 2;
    
    for (int i = 0; i < 9; i++)
    {
      if (min > type[i])
        min = type[i];
    }
    if (min > 60)
      min = 60;
    System.out.print(min);
  }
}