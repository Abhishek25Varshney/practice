package yash;
public class Quiz {

	private String question;

	private String difficultylevel;

	private String tag;

	static int count = 0;
	{
		count++;
		
	}
	public boolean equals(Object obj) 
	    { 
	//	System.out.println("I m equals");
	    if(this == obj) 
	            return true; 
	          
	       
	     if(obj == null || obj.getClass()!= this.getClass()) 
	            return false; 
	          
	        // type casting of the argument.  
	        Quiz quiz = (Quiz) obj; 
	          
	        // comparing the state of argument with  
	        // the state of 'this' Object. 
	        return ((quiz.getTag().equals(this.tag)) && (quiz.getDifficultylevel().equals(this.difficultylevel))); 
	    } 
	      
	    @Override
	    public int hashCode() 
	    { 
	         // System.out.println("I m hashcode"+ (this.tag.hashCode()+this.difficultylevel.hashCode()));
	        // assuming each question no. is unique
	        return (this.tag.hashCode()+this.difficultylevel.hashCode()); 
	    } 	
	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Quiz.count = count;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDifficultylevel() {
		return difficultylevel;
	}

	public void setDifficultylevel(String difficultylevel) {
		this.difficultylevel = difficultylevel;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	
	
}
