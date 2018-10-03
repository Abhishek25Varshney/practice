package practice;

public class multipleOf3And5 {

	public static void main(String[] args) {

	}

	public int solution(int number) {
		int sum = 0;
		for (int i = 1; i <= number; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				sum += i;
			} else if (i % 3 == 0 || i % 5 == 0) {
				sum += i;
			}
		}

		return sum;
	}
}
