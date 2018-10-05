package practice;

public class TimeInWords {

	public static void main(String[] args) {
		System.out.println(timeInWords(1, 1));
	}

	static String timeInWords(int h, int m) {
		String[] numberWords = new String[] { "", "one", "two", "three", "four", "five", "six", "seven", "eight",
				"nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
				"eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four",
				"twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine" };

		if (m == 0) {
			return numberWords[h] + " o' clock";
		} else if (m == 15) {
			return "quarter past " + numberWords[h];
		} else if (m > 0 && m < 30) {
			if (m == 1) {
				return numberWords[m] + " minute past " + numberWords[h];
			}
			return numberWords[m] + " minutes past " + numberWords[h];
		} else if (m == 30) {
			return "half past " + numberWords[h];
		} else if (m == 45) {
			if (h == 12)
				return "quarter to " + numberWords[1];
			else
				return "quarter to " + numberWords[h + 1];
		} else if (m > 30 && m < 60) {
			return numberWords[60 - m] + " minutes to " + numberWords[h + 1];
		}

		return null;

	}

}
