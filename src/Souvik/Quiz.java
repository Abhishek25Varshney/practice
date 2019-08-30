
package Souvik;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class Quiz {
    static int i=0;
    public static void main(String[] args) throws IOException {
    // variable declaration 
        Quiz_count qc=new Quiz_count();
        
        int ch;
        int a=0;
        char b = 0,c = 0,d=0,e=0;
        
        // check if File exists or not 
        FileReader fr=null; 
        try
        { 
            fr = new FileReader("TestCase1.txt"); 
        } 
        catch (FileNotFoundException fe) 
        { 
            System.out.println("File not found"); 
        } 
  
        // read from FileReader till the end of file 
        
        while ((ch=fr.read())!=-1){ 
            System.out.print((char)ch); 
             
            if((char)ch=='Q'){
             a=1;   
            }
             
            if(a==2){
              i=i+1;
              b=(char)ch;
            }
            
            if(a==3){
              c=(char)ch;
            }
            
            if(a==6){
            d=(char)ch;
            
            }
            
            if(a==14){
            e=(char)ch;
            qc.count(b,c,d,e);
            
            }
            
            a=a+1;
            
        }
        qc.total_quiz(i);
        i=0;
        // close the file 
        fr.close(); 
    }
  
    
    
    
    
    
    
    
}
