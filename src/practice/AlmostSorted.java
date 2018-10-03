package practice;

import java.util.Arrays;

public class AlmostSorted {

	public static void main(String[] args) {
		int[] arr = { 3, 1, 2 };
		almostSorted(arr);

	}

	static void almostSorted(int[] arr) {
		int[] copy = Arrays.copyOf(arr, arr.length);
		Arrays.sort(copy);
		int swapCount = 0;
		for (int i = 0; i < copy.length; i++) {
			if (arr[i] != copy[i]) {
				swapCount += 1;
			}
		}
		if (swapCount == 2) {
			System.out.println("yes");
			System.out.print("swap");
			for (int i = 0; i < copy.length; i++) {
				if (arr[i] != copy[i]) {
					System.out.print(" " + (i + 1));
				}
			}
		} else if (swapCount > 2) {
			int[] reverse = Arrays.copyOf(arr, arr.length);
			int first = -1;
			for (int i = 0; i < copy.length; i++) {
				if (arr[i] != copy[i]) {
					first = i;
					break;
				}
			}
			int last = -1;
			for (int i = copy.length - 1; i >= 0; i--) {
				if (arr[i] != copy[i]) {
					last = i;
					break;
				}
			}
			if (last != -1 && first != -1) {
				first += 1;
				last += 1;
			}
			int j = last - 1;
			for (int i = first - 1; i < (last + first - 1) / 2; i++) {
				int temp = reverse[i];
				reverse[i] = reverse[j];
				reverse[j] = temp;
				j -= 1;

			}
			// for (int i = 0; i < reverse.length; i++) {
			// if (reverse[i] != arr[i]) {
			// System.out.println("no");
			// break;
			// }
			// }
			if (Arrays.equals(reverse, copy)) {
				System.out.println("yes");
				System.out.println("reverse " + first + " " + last);
			} else {
				System.out.println("no");
			}
		}
		// System.out.println();
		// for (int i = 0; i < copy.length; i++) {
		// System.out.println(copy[i]);
		// }

	}

}
