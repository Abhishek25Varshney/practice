package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SherlockAndValidString {

	public static void main(String[] args) {
		System.out.println(isValid("aabbcd"));
	}

	static String isValid(String s) {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(Character.toString(s.charAt(i)))) {
				int count = map.get(Character.toString(s.charAt(i))) + 1;
				map.put(Character.toString(s.charAt(i)), count);
			} else {
				map.put(Character.toString(s.charAt(i)), 1);
			}
		}
		List<Integer> list = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			list.add(entry.getValue());
		}

		Collections.sort(list);
		Map<Integer, Integer> map2 = new HashMap<>();
		for (Integer integer : list) {
			if (map2.containsKey(integer)) {
				int count1 = map2.get(integer) + 1;
				map2.put(integer, count1);
			} else {
				map2.put(integer, 1);
			}
		}

		if (map2.size() == 1) {
			return "YES";
		} else if (map2.size() == 2) {
			List<Integer> list2 = new LinkedList<>();
			List<Integer> list3 = new LinkedList<>();

			for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {

				list2.add(entry.getKey());
				list3.add(entry.getValue());
			}
			if (list3.contains(1)) {
				int diff2 = Math.abs(list2.get(0) - list2.get(1));
				if (diff2 == 1) {
					return "YES";
				} else {
					return "NO";
				}
			}
		} else {
			return "NO";
		}
		return "NO";

	}
}
