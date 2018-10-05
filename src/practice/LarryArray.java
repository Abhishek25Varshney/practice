package practice;

public class LarryArray {

	public static void main(String[] args) {
		int[] arr = { 1,6,5,2,4,3 };
		larrysArray(arr);
	}

	static String larrysArray(int[] arrayNumbs) {
		int numbers = arrayNumbs.length;
		int matches = 1;
		while (matches > 0) {
			int action = 0;
			for (int y = 0; y < (numbers - 2); y++) {
				int smallestNumber = 0;
				if (arrayNumbs[y] < arrayNumbs[y + 1]) {
					smallestNumber = arrayNumbs[y];
				} else {
					smallestNumber = arrayNumbs[y + 1];
				}

				if (smallestNumber > arrayNumbs[y + 2]) {
					smallestNumber = arrayNumbs[y + 2];
				}

				int firstN = arrayNumbs[y];
				int secondN = arrayNumbs[y + 1];
				int thirdN = arrayNumbs[y + 2];

				if (smallestNumber == arrayNumbs[y + 1]) {
					action++;
					arrayNumbs[y] = secondN;
					arrayNumbs[y + 1] = thirdN;
					arrayNumbs[y + 2] = firstN;

				} else if (smallestNumber == arrayNumbs[y + 2]) {
					action++;
					arrayNumbs[y] = thirdN;
					arrayNumbs[y + 1] = firstN;
					arrayNumbs[y + 2] = secondN;
				}
			}
			matches = action;
		}

		if (arrayNumbs[arrayNumbs.length - 1] > arrayNumbs[arrayNumbs.length - 2]) {
			return "YES";
		} else {
			return "NO";
		}

	}

}
