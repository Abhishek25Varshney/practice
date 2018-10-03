package practice;

public class NumberOfPeopleInBus {

	public static void main(String[] args) {
		System.out.println(birthdayCakeCandles(new int[] {18 ,90 ,90 ,13 ,90 ,75 ,90 ,8,90,43}));
	}

	static int birthdayCakeCandles(int[] ar) {
		int largest = 0;
		int largestCount = 0;
		for (int i : ar) {
				int temp=i;
				if(temp>=largest) {
					largest=temp;
					largestCount+=1;
				}
		}
		return largestCount;
	}
}
