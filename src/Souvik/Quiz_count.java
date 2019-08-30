
package Souvik;

import java.util.ArrayList;

public class Quiz_count {
    static int e=0;
    static int f,g,h=0;
    static int tag1,tag2,tag3,tag4,tag5,tag6;
    
 public void count(char a, char b, char c, char d){
 
 if(c=='e'){
 f=f+1;
 }
 if(c=='m'){
 g=g+1;
 }
 if(c=='H'){
 h=h+1;
 }
 
 if(d=='1'){
 tag1++;
 }
 
 if(d=='2'){
 tag2++;
 }
 if(d=='3'){
 tag3++;
 }
 if(d=='4'){
 tag4++;
 }
 if(d=='5'){
 tag5++;
 }
 if(d=='6'){
 tag6++;
 }
  
 if(f>=2 && g>=2 && h>=2 && tag1>=1 && tag2>=1 && tag3>=1 && tag4>=1 && tag5>=1 && tag6>=1){
 e++;
 f=0;g=0;h=0;tag1=0;tag2=0;tag3=0;tag4=0;tag5=0;tag6=0;
 }
 }
 
 
    
public void total_quiz(int d){
 System.out.println(" ");
 System.out.println("Total_Quizes="+e);
 e=0;
}
    


}






