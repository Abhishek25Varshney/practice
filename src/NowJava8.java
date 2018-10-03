import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class NowJava8 {

    public static void main(String[] args) throws ParseException, UnsupportedEncodingException {

        List<String> lines = Arrays.asList("spring", "node", "mkyong");

/*        List<String> result = lines.stream()                // convert list to stream
                .filter(line -> !"mkyong".equals(line))     // we dont like mkyong
                .collect(Collectors.toList());              // collect the output and convert streams to a List
*/
        List<String> result=lines.stream().filter(line->"mkyong".equals(line)).collect(Collectors.toList());
        result.forEach(System.out::println);                //output : spring, node

        
        List<Staff> staff = Arrays.asList(
                new Staff("mkyong", 30, new BigDecimal(10000)),
                new Staff("jack", 27, new BigDecimal(20000)),
                new Staff("lawrence", 33, new BigDecimal(30000)),
                new Staff("lawrence", 34, new BigDecimal(434530000))
        );
        
        List<String> names=staff.stream().map(Staff::getName).collect(Collectors.toList());
        names.forEach(System.out::println);
        Set<String> names2=staff.stream().map(a->a.getName()).collect(Collectors.toSet());
        names2.forEach(System.out::println);
        
    
        	String s1="image/png|image/jpeg";
        	String[] words=s1.split("\\|");//splits the string based on string
//        	String words=s1.split("http://google.co/")[1];
//        	String directoryName=words.split("/")[0];
        	//using java foreach loop to print elements of string array
        	List<String> word=new ArrayList<String>();
        	for(String w:words){
        	System.out.println(w);
        	}
        	
        	String freeTrial=String.valueOf(true);
        	System.out.println(freeTrial);
        	System.out.println(Boolean.valueOf(freeTrial));
//        	Boolean.valueOf(decrypted);
        	
        	
        	   ArrayList<Integer> role=new ArrayList<Integer>();
        	   role.add(4);
        	   role.add(5);
        	   System.out.println(role);
        	   role.remove(new Integer(5));
        	   System.out.println(role);
        	   
        	   String s2="https://cdn.e-gmat.com/LMS2-CDNConcepts/FREE_TRIAL/Algebra_AbsoluteEquations/story_html5.html?actor=%7B%22name%22%3A%20%5B%22%7Cname%7C%22%5D%2C%20%22mbox%22%3A%20%5B%22mailto%3A%7Cemail%7C%22%5D%7D&endpoint=https%3A%2F%2Fblitzkrieg.e-gmat.com&activity_id=http://ALG_217&tincan=true&custom_source=free_trial&custom_medium=regular&custom_campaign=concept&custom_content=App1";
        	   String utmParameters=s2.split("&custom_source=")[1];
   			String customSource=utmParameters.split("&custom_medium=")[0];
   			System.out.println(customSource);
   			String customMedium=utmParameters.split("&custom_medium=")[1].split("&custom_campaign=")[0];
   			System.out.println(customMedium);
   			String customCampaign=utmParameters.split("&custom_medium=")[1].split("&custom_campaign=")[1].split("&custom_content=")[0];
   			String customContent=utmParameters.split("&custom_medium=")[1].split("&custom_campaign=")[1].split("&custom_content=")[1];
        	
        	
        	System.out.println(customCampaign);
        	System.out.println(customContent);
        	
        	Date date=new Date();
        	DateFormat dateFormatYMD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	String date1=dateFormatYMD.format(date);
        	java.sql.Timestamp date2=java.sql.Timestamp.valueOf(date1);
        	System.out.println(date.getHours()+"============"+date2.getHours());
        	String conceptTag="PS-ALG";
        	String shortName=conceptTag.split("-")[1];
        	System.out.println(shortName);
        	
        	
        	
        	
        	
        	
        	String username="joey\'\"\"";
        	System.out.println(username);
        	System.out.println(username.contains("\'"));
        	System.out.println(username.contains("\""));
        	username=username.replaceAll("\'", "\\\\\'");
        	username=username.replaceAll("\"", "\\\\\"");
        	System.out.println(username);
        	username=username.replaceAll("\\\\", "");
        	System.out.println(username);
        	
        	
        	List<String> list = Arrays.asList("foo", "bar", "baz");
        	String joined="'";
        	System.out.println(joined);
        	 joined= joined.concat(String.join("','", list));
        	 joined=joined.concat("'");
        	 String lastname=" told me to post this picture beacause he is out of station. may be it was his mistake . he thought ";
        	System.out.println(lastname.substring(1, 51));
    
        	String name="vijaycoumarsethuraman@yahoo.co.in\\";
        	System.out.println(name);
        	
        	
        	String t="аптека.орг";
        	
        	
        	
        	Map<Integer,String> map=new LinkedHashMap<Integer,String>();
        	map.put(1, "A");
        	map.put(2, "B");
        	map.put(1, "C");
        	System.out.println(map);
        	
        	

        		Calendar cal = Calendar.getInstance();
        		cal.add(Calendar.DAY_OF_YEAR, 7);
        		System.out.println(cal.getTime());
        		
        		Date dateBefore = new Date((new Date().getTime() + 7 * 24 * 3600 * 1000));

        		System.out.println(dateBefore);
        		
        		String s4="79|89|99,149";
            	String[] word3=s4.split(",");
            	System.out.println(s4.split(",")[0]);
            	System.out.println(s4.split(",")[1]);
            	for(String w:word3){
            	System.out.println(w);
            	}
            	
            	Calendar cal1=Calendar.getInstance();
            	cal1.add(Calendar.DAY_OF_YEAR, 60);
            	System.out.println(cal1.getTime());
            	
            	
            	String asdlajsd="as sad";
            	URLEncoder.encode(asdlajsd, "UTF-8");
            	System.out.println(asdlajsd.replaceAll(" ", "+"));
    
    }
 
    
    
    
    

}
