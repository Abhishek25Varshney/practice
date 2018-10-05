package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lexicographical {
	static String org = "";

	public static void main(String[] args) {
		String s = "fedcbabcd";
		org = s;
		System.out.println(biggerIsGreater(s));
	}

	public static Set<String> permutationFinder(String str) {
		Set<String> perm = new HashSet<String>();
		// Handling error scenarios
		if (str == null) {
			return null;
		} else if (str.length() == 0) {
			perm.add("");
			return perm;
		}
		char initial = str.charAt(0); // first character
		String rem = str.substring(1); // Full string without first character
		Set<String> words = permutationFinder(rem);
		for (String strNew : words) {
			for (int i = 0; i <= strNew.length(); i++) {
				if (charInsert(strNew, initial, i).compareTo(org) < 0) {
					perm.add(charInsert(strNew, initial, i));
				}
			}
		}
		return perm;
	}

	public static String charInsert(String str, char c, int j) {
		String begin = str.substring(0, j);
		String end = str.substring(j);
		return begin + c + end;
	}

	static String biggerIsGreater(String w) {

		Set<String> set = permutationFinder(w);
		List<String> list = new ArrayList<String>(set);
		Collections.sort(list);
		System.out.println(list);
		for (String string : list) {
			int diff = w.compareTo(string);
			if (diff < 0) {
				return string;
			}
		}
		return "no answer";

	}
}
