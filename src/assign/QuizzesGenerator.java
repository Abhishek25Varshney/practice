package assign;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuizzesGenerator
{
  private ArrayList<ArrayList<Integer>> matrix;
  private ArrayList<Map<String, Integer>> finalSet;
  private final ArrayList<String> tags = new ArrayList(java.util.Arrays.asList(new String[] { "Tag1", "Tag2", "Tag3", "Tag4", "Tag5", "Tag6" }));
  private final ArrayList<String> diffLevels = new ArrayList(java.util.Arrays.asList(new String[] { "EASY", "MEDIUM", "HARD" }));
  
  public QuizzesGenerator() {
    matrix = new ArrayList();
    finalSet = new ArrayList();
    for (int i = 0; i < 6; i++) {
      ArrayList<Integer> innerList = new ArrayList(java.util.Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0) }));
      matrix.add(innerList);
    }
  }
  
  public static void main(String[] args) {
    QuizzesGenerator generator = new QuizzesGenerator();
    String errMsg = "";
//    if (args.length != 0) {
      File file = new File("C:\\e-GMAT\\QUESTIONS.txt");
      if (!file.exists()) {
        errMsg = "Please enter a valid file name";
      } else {
        generator.parseFileContent(file);
        generator.createQuiz();
      }
//    } else {
//      errMsg = "Please provide filename as 1st argument";
//    }
    if (!errMsg.isEmpty())
      System.out.println(errMsg);
  }
  
  private void createQuiz() {
    int value;
    while (swapTags()) {
      Map<String, Integer> innerMap = new HashMap();
      for (String diff : diffLevels) {
        innerMap.put(diff, Integer.valueOf(0));
      }
      int i = 0; for (int j = 0; i < 6; j++)
      {
        if (i == j) {
          value = ((Integer)((ArrayList)matrix.get(i)).get(j % 3)).intValue();
          
          addToMatrix(matrix, i, j % 3, --value);
          value = ((Integer)innerMap.get(diffLevels.get(j % 3))).intValue();
          innerMap.put((String)diffLevels.get(j % 3), Integer.valueOf(++value));
        }
        i++;
      }
      







      finalSet.add(innerMap);
    }
    while ((hasTagElements()) && (hasDiffElements())) {
      boolean flag = false;
      Object categoryMap = new HashMap();
      Map<String, Integer> innerMap = new HashMap();
      for (String diff : diffLevels) {
        innerMap.put(diff, Integer.valueOf(0));
        ((Map)categoryMap).put(diff, new ArrayList());
      }
      int i = 0; for (int j = 0; i < 6; j++) {
        if (((Integer)((ArrayList)matrix.get(i)).get(j % 3)).intValue() > 0) {
          value = ((Integer)innerMap.get(diffLevels.get(j % 3))).intValue();
          innerMap.put((String)diffLevels.get(j % 3), Integer.valueOf(++value));
          value = ((Integer)((ArrayList)matrix.get(i)).get(j % 3)).intValue();
          
          addToMatrix(matrix, i, j % 3, --value);
          i++;
          ((ArrayList)((Map)categoryMap).get(diffLevels.get(j % 3))).add(Integer.valueOf(i));
        }
      }
      for (i = 0; i < 3;) {
        if (((Integer)innerMap.get(diffLevels.get(i))).intValue() >= 2) {
          i++;
        } else { if (!hasDiffElements()) {
            flag = true;
            break;
          }
          int prevCount = ((Integer)innerMap.get(diffLevels.get(i))).intValue();
          ArrayList<Integer> tagList = (ArrayList)((Map)categoryMap).get(diffLevels.get(i));
          for (int row = 0; row < 6; row++) {
            if ((!tagList.contains(Integer.valueOf(row))) && (((Integer)((ArrayList)matrix.get(row)).get(i)).intValue() > 0)) {
              value = ((Integer)((ArrayList)matrix.get(row)).get(i)).intValue();
              
              addToMatrix(matrix, row, i, --value);
              value = ((Integer)innerMap.get(diffLevels.get(i))).intValue();
              innerMap.put((String)diffLevels.get(i), Integer.valueOf(++value));
              tagList.add(Integer.valueOf(row));
              break;
            }
          }
          int currentCount = ((Integer)innerMap.get(diffLevels.get(i))).intValue();
          if (prevCount == currentCount) {
            int row = 0;
            while ((((Integer)innerMap.get(diffLevels.get(i))).intValue() < 2) && (row < tagList.size())) {
              if (((Integer)((ArrayList)matrix.get(((Integer)tagList.get(row)).intValue())).get(i)).intValue() > 0) {
                value = ((Integer)((ArrayList)matrix.get(((Integer)tagList.get(row)).intValue())).get(i)).intValue();
                
                addToMatrix(matrix, ((Integer)tagList.get(row)).intValue(), i, --value);
                value = ((Integer)innerMap.get(diffLevels.get(i))).intValue();
                innerMap.put((String)diffLevels.get(i), Integer.valueOf(++value));
                break;
              }
              row++;
            }
          }
        }
      }
      if (!flag)
        finalSet.add(innerMap);
    }
    if (finalSet.size() > 60)
      System.out.println(60); else
      System.out.println(finalSet.size());
  }
  
  private boolean hasDiffElements() {
    for (int i = 0; i < 3; i++) {
      int sum = 0;
      for (int j = 0; j < 6; j++) {
        sum += ((Integer)((ArrayList)matrix.get(j)).get(i)).intValue();
      }
      if (sum < 2) {
        return false;
      }
    }
    return true;
  }
  
  private boolean hasTagElements() {
    for (int i = 0; i < 6; i++) {
      int sum = 0;
      for (int j = 0; j < 3; j++) {
        sum += ((Integer)((ArrayList)matrix.get(i)).get(j)).intValue();
      }
      if (sum == 0) {
        return false;
      }
    }
    return true;
  }
  
  private boolean swapTags() {
    if (hasDiagonalElements()) {
      return true;
    }
    int count = 0;
    while ((!hasDiagonalElements()) && (count != 6)) {
      int row = 0;int column = 0;
      int i = 0; for (int j = 0; i < 6; j++) {
        if ((i == j) && (((Integer)((ArrayList)matrix.get(i)).get(j % 3)).intValue() == 0)) {
          row = i;
          column = j % 3;
          break;
        }
        i++;
      }
      





      for (i = 0; i < 6; i++) {
        if ((i != row + 3) && (row != i + 3) && (i != row) && (((Integer)((ArrayList)matrix.get(i)).get(column)).intValue() > 0) && (((Integer)((ArrayList)matrix.get(row)).get(i % 3)).intValue() > 0)) {
          ArrayList<Integer> temp1 = (ArrayList)matrix.get(i);
          ArrayList<Integer> temp2 = (ArrayList)matrix.get(row);
          if (i > row) {
            matrix.remove(row);
            matrix.add(i - 1, temp2);
            matrix.remove(temp1);
            matrix.add(row, temp1);
          } else {
            matrix.remove(i);
            matrix.add(row - 1, temp1);
            matrix.remove(temp2);
            matrix.add(i, temp2);
          }
        }
      }
      count++;
    }
    
    return hasDiagonalElements();
  }
  
  private boolean hasDiagonalElements() {
    int i = 0; for (int j = 0; i < 6; j++)
    {
      if ((i == j) && (((Integer)((ArrayList)matrix.get(i)).get(j % 3)).intValue() == 0)) {
        return false;
      }
      i++;
    }
    


    return true;
  }
  
  private void parseFileContent(File file) {
    try {
      java.io.FileReader fileReader = new java.io.FileReader(file);
      java.io.BufferedReader reader = new java.io.BufferedReader(fileReader);
      String s;
      while ((s = reader.readLine()) != null) {
        String[] content = s.split("\\|");
        String tag = content[2];
        String diff = content[1];
        int value = ((Integer)((ArrayList)matrix.get(tags.indexOf(tag))).get(diffLevels.indexOf(diff))).intValue();
        addToMatrix(matrix, tags.indexOf(tag), diffLevels.indexOf(diff), value + 1);
      }
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }
  
  private void addToMatrix(ArrayList<ArrayList<Integer>> matrix, int i, int j, int value) {
    ((ArrayList)matrix.get(i)).remove(j);
    ((ArrayList)matrix.get(i)).add(j, Integer.valueOf(value));
  }
}