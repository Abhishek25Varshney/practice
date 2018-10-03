package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NonDivisibleSubset {
	public static void main(String[] args) {
		System.out.println(nonDivisibleSubset(4, new int[] { 19, 10, 12, 10, 24, 25, 22 }));
	}

	// static int nonDivisibleSubset(int k, int[] S) {
	// Set<Integer> set = new HashSet<>();
	// for (int i = 0; i < S.length - 1; i++) {
	// int first = S[i];
	// for (int j = i + 1; j < S.length; j++) {
	// int second = S[j];
	// int count = first + second;
	// if (count % k != 0) {
	// set.add(first);
	// set.add(second);
	// }
	// }
	// }
	// return set.size();
	// }
	static int nonDivisibleSubset(int k, int[] S) {
		List<List<Integer>> subsets = subsets(S);
		for (List<Integer> list : subsets) {
			for (Integer integer : list) {

			}
		}
		return k;
	}

	static List<List<Integer>> subsets(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());
		for (int n : nums) {
			int size = result.size();
			for (int i = 0; i < size; i++) {
				List<Integer> l = new ArrayList<Integer>(result.get(i));
				l.add(n);
				result.add(l);
			}
		}
		return result;
	}

	static int t(int k, List<Integer> list) {
		Set<Integer> set = new HashSet<>();
		for (Integer integer : set) {

		}
		for (int i = 0; i < list.size() - 1; i++) {
			int first = list.get(i);
			for (int j = i + 1; j < list.size(); j++) {
				int second = list.get(j);
				int count = first + second;
				if (count % k == 0) {
					set.add(first);
					set.add(second);
				}
			}
		}
		return set.size();
	}
}
