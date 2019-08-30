package assign;
import java.io.*;
import java.util.*;
public class QuizGeneratorEGmat {
	public static int best_question_available(ArrayList<String> diff_arr,HashMap<String,String> q_diff_mapp,ArrayList<String> arr_of_tag,int pos,int e,int m,int h) {
				String q=arr_of_tag.get(pos);
				if(diff_arr.indexOf(q_diff_mapp.get(q))==0) {
					if(e>2) {
						pos+=1;
						best_question_available(diff_arr,q_diff_mapp,arr_of_tag,pos,e,m,h);
					}
					else
						{return pos;}
				}
				else if(diff_arr.indexOf(q_diff_mapp.get(q))==1) {
					if(m>2) {
						pos+=1;
						best_question_available(diff_arr,q_diff_mapp,arr_of_tag,pos,e,m,h);
					}
					else {
						return pos;
					}
				}
				else {
					return pos;
		}
				return pos;
	}
	
	public static String largest_difficulty_available(HashMap<String, ArrayList<String>> difficulty_question_mapp,ArrayList<String> difficulties_available) {
		String best_difficulty=difficulties_available.get(0);
		int max_question_count=0;
		for(int i=0;i<difficulties_available.size();i++) {
			String difficulty_selected=difficulties_available.get(i);
			if(difficulty_question_mapp.get(difficulty_selected).size()>max_question_count) {
				best_difficulty=difficulty_selected;
				max_question_count=difficulty_question_mapp.get(difficulty_selected).size();
			}
		}
		return best_difficulty;
	}

	public static String larget_tag_available(HashMap<String, ArrayList<String>> tags_ques_mapp,ArrayList<String> tags_available,ArrayList<String> diff_arr) {
		String best_tag=tags_available.get(0);
		int max_questions=0;
		for(int i=0;i<tags_available.size();i++) {
			String tag_selected=tags_available.get(i);
			ArrayList<String> list=duplicate_arraylist(tags_ques_mapp.get(tag_selected));
			list.retainAll(diff_arr);			
			if(list.size()>0){
				if((tags_ques_mapp.get(tag_selected).size()>max_questions) && (list.get(0)!=null)) {
					best_tag=tag_selected;
					max_questions=tags_ques_mapp.get(tag_selected).size();
				}
			}
		}
		return best_tag;
	}
	public static ArrayList<String> duplicate_arraylist(ArrayList<String> arr){
		ArrayList<String> new_arraylist=new ArrayList<>();
		for(int i=0;i<arr.size();i++){
			new_arraylist.add(arr.get(i));
		}
		return new_arraylist;
	}
	public static ArrayList<String> getQuestionsFromFile(String file_path) throws Exception{
		File file = new File(file_path); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		ArrayList<String> question_pool=new ArrayList<>();
		String line;
		while ((line = br.readLine()) != null) {
			question_pool.add(line); 
		}
		return question_pool;
	}
	
	public static void main(String[] args) throws Exception {
		String file_path="C:\\e-GMAT\\8.txt";
		ArrayList<String> question_list=getQuestionsFromFile(file_path); // ArrayList of Question|Difficulty|Tags combination
		
		HashMap<String, ArrayList<String>> tags_ques_mapp = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> difficulty_question_mapp = new HashMap<String, ArrayList<String>>();
		HashMap<String, String> question_difficulty_mapp = new HashMap<String, String>();
		ArrayList<String> tags_available = new ArrayList<String>();
		ArrayList<String> difficulties_available = new ArrayList<String>();
		int count_of_quiz=0;
		for(int i=0;i<question_list.size();i++) //Initializing the Data Structures Created 
	    {
	        String quest_structure=question_list.get(i);
	        String stuct_content[]=quest_structure.split("\\|");
	        if(tags_ques_mapp.get(stuct_content[2])==null) {
	        	ArrayList<String> ques_arr = new ArrayList<String>();
	        	ques_arr.add(stuct_content[0]);
	        	tags_ques_mapp.put(stuct_content[2], ques_arr);
	        	tags_available.add(stuct_content[2]);
	        }
			else
			{
				ArrayList<String> ques_arr=tags_ques_mapp.get(stuct_content[2]);
				ques_arr.add(stuct_content[0]);
				tags_ques_mapp.put(stuct_content[2], ques_arr);
			}
			if(difficulty_question_mapp.get(stuct_content[1])==null) {
				ArrayList<String> ques_arr = new ArrayList<String>();
	        	ques_arr.add(stuct_content[0]);
	        	difficulty_question_mapp.put(stuct_content[1], ques_arr);
	        	difficulties_available.add(stuct_content[1]);
			}
			else {
				ArrayList<String> ques_arr=difficulty_question_mapp.get(stuct_content[1]);
				ques_arr.add(stuct_content[0]);
				difficulty_question_mapp.put(stuct_content[1], ques_arr);
			}
			question_difficulty_mapp.put(stuct_content[0],stuct_content[1]);
	    }
				boolean terminate=false;
				while(!terminate) { // Executing Till when the criteria are met
					int diff1=0,diff2=0,diff3=0; //Index count flags
					for(int i=0;i<difficulties_available.size();i++) { //Min difficulties available check
						String diff=difficulties_available.get(i);
						if(difficulty_question_mapp.get(diff).size()<2) {
							terminate=true;
							break;
						}
					}
					if(!terminate) {
						for(int i=0;i<tags_available.size();i++) { //generating first 6 questions
							int pos=0;
							String x=tags_available.get(i);
							ArrayList<String> arr_of_tag=tags_ques_mapp.get(x);
							if(arr_of_tag.size()==0){
								terminate=true;
								break;
							}
							else {
								pos = best_question_available(difficulties_available,question_difficulty_mapp,arr_of_tag,pos,diff1,diff2,diff3);
								String q=arr_of_tag.get(pos);
								tags_ques_mapp.get(x).remove(pos);
								String difficulty=question_difficulty_mapp.get(q);
								if(difficulties_available.indexOf(question_difficulty_mapp.get(q))==0)
								{diff1+=1;}
								else if(difficulties_available.indexOf(question_difficulty_mapp.get(q))==1)
								{diff2+=1;}
								else
								{diff3+=1;}
								difficulty_question_mapp.get(difficulty).remove(difficulty_question_mapp.get(difficulty).indexOf(q));
							}
						}
						if(!terminate){
							for(int i=0;i<4;i++){// generating last 4 questions
								String largest_difficulty=largest_difficulty_available(difficulty_question_mapp,difficulties_available);
								String largest_tag=larget_tag_available(tags_ques_mapp,tags_available,difficulty_question_mapp.get(largest_difficulty));
								ArrayList<String> dup_arraylist=duplicate_arraylist(difficulty_question_mapp.get(largest_difficulty));
								dup_arraylist.retainAll(tags_ques_mapp.get(largest_tag)); 
								if(dup_arraylist.size()!=0)
								{
									String ques=dup_arraylist.get(0);
									difficulty_question_mapp.get(largest_difficulty).remove(difficulty_question_mapp.get(largest_difficulty).indexOf(ques));
									tags_ques_mapp.get(largest_tag).remove(tags_ques_mapp.get(largest_tag).indexOf(ques));
									
								}
							}
							count_of_quiz+=1;
						}
					}
				}
				System.out.println("Quiz Created: "+count_of_quiz);
	}
}
