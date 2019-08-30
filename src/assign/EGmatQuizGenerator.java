package assign;
import java.io.*;
import java.util.*;
public class EGmatQuizGenerator {


	public static ArrayList<String> create_new_arraylist(ArrayList<String> arr){
		ArrayList<String> arr2=new ArrayList<>();
		for(int i=0;i<arr.size();i++){
			arr2.add(arr.get(i));
		}
		return arr2;
	}
	public static int find_question(ArrayList<String> diff_arr,HashMap<String,String> q_diff_mapp,ArrayList<String> arr_of_tag,int j,int e,int m,int h) {
				String q=arr_of_tag.get(j);
				if(diff_arr.indexOf(q_diff_mapp.get(q))==0) {
					if(e>2) {
						j+=1;
						find_question(diff_arr,q_diff_mapp,arr_of_tag,j,e,m,h);
					}
					else
						{return j;}
				}
				else if(diff_arr.indexOf(q_diff_mapp.get(q))==1) {
					if(m>2) {
						j+=1;
						find_question(diff_arr,q_diff_mapp,arr_of_tag,j,e,m,h);
					}
					else {
						return j;
					}
				}
				else {
					return j;
		}
				return j;
	}
	
	public static String best_difficulty(HashMap<String, ArrayList<String>> diff,ArrayList<String> diff_arr) {
		String bd=diff_arr.get(0);
		int q_count=0;
		for(int i=0;i<diff_arr.size();i++) {
			String x=diff_arr.get(i);
			if(diff.get(x).size()>q_count) {
				bd=x;
				q_count=diff.get(x).size();
			}
		}
		
		return bd;
	}

	public static String best_tag_available(HashMap<String, ArrayList<String>> tag,ArrayList<String> tag_arr,ArrayList<String> diff_arr) {
		String bd=tag_arr.get(0);
		int q_count=0;
		
		for(int i=0;i<tag_arr.size();i++) {
			String x=tag_arr.get(i);
			ArrayList<String> list=create_new_arraylist(tag.get(x));
			
			list.retainAll(diff_arr);
			
			
			if(list.size()>0){
				if((tag.get(x).size()>q_count) && (list.get(0)!=null)) {
					bd=x;
					q_count=tag.get(x).size();
				}
			}
		}
		
		return bd;
	}
	
	public static String file_text(String file_path) throws Exception{
		File file = new File(file_path); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st;
		 String a="";
		while ((st = br.readLine()) != null) {
			a=a+st+"\n"; 
		}
		return a;
	}
	
	public static void main(String[] args) throws Exception {
		String a=file_text("C:\\e-GMAT\\8.txt");
        String b[]=a.split("\n");
		HashMap<String, ArrayList<String>> tag = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> diff = new HashMap<String, ArrayList<String>>();
		HashMap<String, String> q_diff_mapp = new HashMap<String, String>();
		ArrayList<String> tag_arr = new ArrayList<String>();
		ArrayList<String> diff_arr = new ArrayList<String>();
		for(int i=0;i<b.length;i++)
	    {
	        String x=b[i];
	        String c[]=x.split("\\|");
	        if(tag.get(c[2])==null) {
	        	ArrayList<String> ques_arr = new ArrayList<String>();
	        	ques_arr.add(c[0]);
	        	tag.put(c[2], ques_arr);
	        	tag_arr.add(c[2]);
	        }
			else
			{
				ArrayList<String> ques_arr=tag.get(c[2]);
				ques_arr.add(c[0]);
				tag.put(c[2], ques_arr);
			}
			if(diff.get(c[1])==null) {
				ArrayList<String> ques_arr = new ArrayList<String>();
	        	ques_arr.add(c[0]);
	        	diff.put(c[1], ques_arr);
	        	diff_arr.add(c[1]);
			}
			else {
				ArrayList<String> ques_arr=diff.get(c[1]);
				ques_arr.add(c[0]);
				diff.put(c[1], ques_arr);
			}
			q_diff_mapp.put(c[0],c[1]);
	    }

				boolean stop=false;
				int quiz_count=0;
				while(!stop) {
					int e=0,m=0,h=0;
					for(int i=0;i<diff_arr.size();i++) {
						String y=diff_arr.get(i);
						if(diff.get(y).size()<2) {
							stop=true;
							break;
						}
					}
					if(!stop) {
						for(int i=0;i<tag_arr.size();i++) {
							int j=0;
							String x=tag_arr.get(i);
							ArrayList<String> arr_of_tag=tag.get(x);
							if(arr_of_tag.size()==0){
								stop=true;
								break;
							}
							else {
								j = find_question(diff_arr,q_diff_mapp,arr_of_tag,j,e,m,h);
								String q=arr_of_tag.get(j);
								tag.get(x).remove(j);
								String difficulty=q_diff_mapp.get(q);
								if(diff_arr.indexOf(q_diff_mapp.get(q))==0)
								{e+=1;}
								else if(diff_arr.indexOf(q_diff_mapp.get(q))==1)
								{m+=1;}
								else
								{h+=1;}
								diff.get(difficulty).remove(diff.get(difficulty).indexOf(q));
							}
						}
						if(!stop){
							
							for(int i=0;i<4;i++){
								String best_diff=best_difficulty(diff,diff_arr);
								String best_tag=best_tag_available(tag,tag_arr,diff.get(best_diff));
								ArrayList<String> common_questions=create_new_arraylist(diff.get(best_diff));
								common_questions.retainAll(tag.get(best_tag)); 
								
								if(common_questions.size()!=0)
								{
									String q=common_questions.get(0);
									diff.get(best_diff).remove(diff.get(best_diff).indexOf(q));
									tag.get(best_tag).remove(tag.get(best_tag).indexOf(q));
									
								}
							}
							quiz_count+=1;
						}
					}
				}
				System.out.println("Max Quiz Possible are "+quiz_count);
	}

}
