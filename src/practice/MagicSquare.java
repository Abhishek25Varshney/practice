package practice;

public class MagicSquare {

	public static void main(String[] args) {

	}

	static int formingMagicSquare(int[][] s) {
		int mindidd = Integer.MAX_VALUE;
		int[] diff = new int[8];
		int[][] s1 = { { 8, 1, 6 }, { 3, 5, 7 }, { 4, 9, 2 } };
		int[][] s2 = { { 6, 1, 8 }, { 7, 5, 3 }, { 2, 9, 4 } };
		int[][] s3 = { { 8, 3, 4 }, { 1, 5, 9 }, { 6, 7, 2 } };
		int[][] s4 = { { 4, 3, 8 }, { 9, 5, 1 }, { 2, 7, 6 } };
		int[][] s5 = { { 6, 7, 2 }, { 1, 5, 9 }, { 8, 3, 4 } };
		int[][] s6 = { { 2, 7, 6 }, { 9, 5, 1 }, { 4, 3, 8 } };
		int[][] s7 = { { 4, 9, 2 }, { 3, 5, 7 }, { 8, 1, 6 } };
		int[][] s8 = { { 2, 9, 4 }, { 7, 5, 3 }, { 6, 1, 8 } };

		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s.length; j++) {

				diff[0] += Math.abs(s[i][j] - s1[i][j]);
				diff[1] += Math.abs(s[i][j] - s2[i][j]);
				diff[2] += Math.abs(s[i][j] - s3[i][j]);
				diff[3] += Math.abs(s[i][j] - s4[i][j]);
				diff[4] += Math.abs(s[i][j] - s5[i][j]);
				diff[5] += Math.abs(s[i][j] - s6[i][j]);
				diff[6] += Math.abs(s[i][j] - s7[i][j]);
				diff[7] += Math.abs(s[i][j] - s8[i][j]);
			}

		}
		for (int i = 0; i < diff.length; i++) {
			if (diff[i] < mindidd) {
				mindidd = diff[i];
			}
		}
		return mindidd;

	}

}
