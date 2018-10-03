import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// Java program to print all combination of size r in an array of size n

class Combination {

	public static void main(String[] args) {
		List<Date> eligiblePackages = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 7);
		eligiblePackages.add(cal.getTime());
		cal.add(Calendar.DAY_OF_YEAR, 4);
		eligiblePackages.add(cal.getTime());
		eligiblePackages.add(new Date());
		
		Collections.sort(eligiblePackages);
		for (Date date : eligiblePackages) {
			System.out.println(date);
		}
	}
}
/* This code is contributed by Devesh Agrawal */
