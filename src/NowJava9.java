import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class NowJava9 {

	public static void main(String[] args) throws IOException, ParseException {
		long d = TimeUnit.DAYS.convert(731, TimeUnit.HOURS);
		int s = (int) d;
		System.out.println(d + "  " + s);

		Date date = new Date(1546611062000L);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, -2768);
		System.out.println(c.getTime());
		String userTimeZone = "3a009331-daf0-47ef-a5f6-360d15d06ef8";
		String[] userTimeZoneArray = userTimeZone.split("\\-");
		userTimeZone = "";
		for (int i = 0; i < userTimeZoneArray.length; i++) {
			userTimeZone += userTimeZoneArray[i];
			if (i != userTimeZoneArray.length - 1) {
				userTimeZone += "/";
			}
		}
		System.out.println("==========>" + userTimeZone.length());

		String sDate1 = "2018-12-04 14:02:39";
		Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate1);
		System.out.println(sDate1 + "\t" + date1);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(new Date()));

		double index = 206.835 - 1.015 * ((double) 4 / (double) 1) - 84.6 * ((double) 5 / (double) 4);
		System.out.println("+++++++++" + index + " " + (double) 5 / (double) 4);

		String text = new String("My ");

		System.out.println(text + new String("String") + " " + text);
		System.out.println((int) Math.round(4.5));
		System.out.println(round(5));

		double totalScore = 38.1642823587221 + (7.75282122822414 * 6) + (7.78314616739263 * 6);
		System.out.println((double) 450 / 100);

		Long diff = Math.abs(new Date(1548959399000L).getTime() - new Date().getTime());
		Long diffDays = diff / (60 * 60 * 1000 * 24);
		int duration = diffDays.intValue();
		System.out.println(duration);

		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("animal", "quick brown fox");
		valuesMap.put("target", "lazy dog");
		String templateString = "The ${animal} jumped over the ${target}.";
		// StrSubstitutor sub = new StrSubstitutor(valuesMap);
		// String resolvedString = sub.replace(templateString);
		System.out.println("====" + computeAnother("0+0"));
		
		double verbalScore = 1.3957 + (0.272 * 51) + (0.3288 * 51) + (0.3566 * 51);
		System.out.println("======"+4.3*100);
		
		String str="deshnawork1@gmail.com|ranish.gmat@gmail.com|thangt90@gmail.com|Banele.Tshuma@hsrw.org|pablobernarfernandezroca@gmail.com";
		String [] names=str.split("\\|");
		for (String string : names) {
			System.out.println(string);
			System.out.println(string);
			System.out.println(string);
			System.out.println(string);
		}
		
		Map<String,String> valueMap=new HashMap<>();
		valueMap.put("user", "abhi");
		StrSubstitutor sub = new StrSubstitutor(valueMap);
		System.out.println(new Date(0L));
		
		System.out.println(isMaxSubscrtion("60"));
		
		String text2d="ab2@e-gmat.com|VO|30-04-2019|Y\r\n" + 
				"ab3@e-gmat.com|GO|30-04-2019|Y\r\n" + 
				"ab4@e-gmat.com|GLP|30-04-2019|Y\r\n" + 
				"ab4@e-gmat.com|GLP|30-04-2019|Y";
		String[] lines = text2d.split("\\r?\\n");
        for (String line : lines) {
        	String[] data=line.split("\\|");
        	String username=data[0];
        	String packageName=data[1];
        	String endDate=data[2];
        	String isMockAccess=data[3];
        	System.out.println("USERNAME "+username+" PACKAGE NAME "+packageName+" ENDDATE "+endDate+" ISMOCKACCESS "+isMockAccess);
            System.out.println("line : " + line);
        }
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, (-1*60));
		System.out.println(cal.getTime());;
		
		
	}

	private static boolean isMaxSubscrtion(String userPackageIds) {
		String glpCombis = "10|30|150|160^^10|30|170^^50|150|160^^50|170^^10|220^^60";
		StringTokenizer st = new StringTokenizer(glpCombis, "^^");
		if(userPackageIds == null) return false;
		while (st.hasMoreElements()) {
			String glpCombo =  (String) st.nextElement();
			if( userPackageIds.trim().equals(glpCombo.trim())) {
				return true;
			}
		}
		return false; 
	}
	private static int compute(String equation) {
		int result = 0;
		String[] byPluses = equation.split("\\+");
		for (String multipl : byPluses) {
			String[] bySubtraction = multipl.split("\\-");
			int subtractionResult = 0;
			int sub = -1;
			for (String operand : bySubtraction) {
				subtractionResult += Integer.parseInt(operand) * sub * -1;
				sub = 1;
			}
			result += subtractionResult;
		}
		return result;
	}

	private static int computeAnother(String equation) {
        int result = 0;
        String noMinus = equation.replace("-", "+-");
        String[] byPluses = noMinus.split("\\+");

        for (String multipl : byPluses) {
          
            result += Integer.parseInt(multipl);
        }
        return result;
	}

	private static String generateStringFromList(List<String> usernames) {
		String joined = "'";
		joined = joined.concat(String.join("','", usernames));
		joined = joined.concat("'");
		return joined;
	}

	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}

	static int round(int n) {
		// Smaller multiple
		int a = (n / 10) * 10;

		// Larger multiple
		int b = a + 10;

		// Return of closest of two
		return (n - a > b - n) ? b : a;
	}

}

