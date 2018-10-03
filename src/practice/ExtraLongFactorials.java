package practice;

import java.math.BigInteger;

public class ExtraLongFactorials {
	public static void main(String[] args) {
		extraLongFactorials(25);
	}

	static void extraLongFactorials(int n) {
		// Initialize result
		BigInteger f = new BigInteger("1");

		// Multiply f with 2, 3, ...N
		for (int i = 2; i <= n; i++)
			f = f.multiply(BigInteger.valueOf(i));

		System.out.println(f);
	}
}
