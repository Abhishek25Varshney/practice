package practice;

import java.util.HashMap;
import java.util.Map;

public class QueenAttack2 {

	public static void main(String[] args) {
		int[][] obstacles = new int[3][2];
		obstacles[0][0] = 5;
		obstacles[0][1] = 5;
		obstacles[1][0] = 4;
		obstacles[1][1] = 2;
		obstacles[2][0] = 2;
		obstacles[2][1] = 3;

		queensAttack(100000, 0,4187, 5068, obstacles);
	}

	static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
		Map<String, Integer> chess = new HashMap<>();
		// queen
		chess.put(Integer.toString(n - r_q).concat(Integer.toString(c_q - 1)), 1);
//		if (k == n * n - 1) {
//			return 0;
//		}
		// place obstacles
		for (int i = 0; i < k; i++) {
			chess.put(Integer.toString(n - obstacles[i][0]).concat(Integer.toString(obstacles[i][1] - 1)),-1);
		}

		int count = 0;
		// attack up
		for (int i = n - r_q - 1; i >= 0; i--) {
			// if (chess[i][c_q - 1] != -1) {
			if (chess.get(Integer.toString(i).concat(Integer.toString(c_q - 1))) ==null) {
				count++;
			} else if (chess.get(Integer.toString(i).concat(Integer.toString(c_q - 1))) == -1) {

				break;
			}
		}
		// attack down
		for (int i = n - r_q + 1; i < n; i++) {
			if (chess.get(Integer.toString(i).concat(Integer.toString(c_q - 1))) ==null) {
				count++;
			} else if (chess.get(Integer.toString(i).concat(Integer.toString(c_q - 1))) == -1) {
				break;
			}
		}
		// attack right
		for (int i = c_q; i < n; i++) {
				if (chess.get(Integer.toString(n-r_q).concat(Integer.toString(i)))==null) {
				count++;
			} else if (chess.get(Integer.toString(n-r_q).concat(Integer.toString(i))) == -1) {
				break;
			}
		}
		// attack left
		for (int i = c_q - 2; i >= 0; i--) {
				if (chess.get(Integer.toString(n-r_q).concat(Integer.toString(i))) ==null) {
				count++;
			} else if (chess.get(Integer.toString(n-r_q).concat(Integer.toString(i))) == -1) {
				break;
			}
		}
		// attack diag down
		int j = n - r_q + 1;
		for (int i = c_q; i < n; i++) {
			if (j + i <= n + n) {
//				if (chess[j][i] != -1) {
				if (chess.get(Integer.toString(j).concat(Integer.toString(i))) ==null) {
					count++;
					j++;
				} else if (chess.get(Integer.toString(j).concat(Integer.toString(i))) == -1) {
					break;
				}
			}
		}
		// attack diag up
		j = n - r_q - 1;
		for (int i = c_q - 2; i >= 0; i--) {
			if (j >= 0) {
//				if (chess[j][i] != -1) {
					if (chess.get(Integer.toString(j).concat(Integer.toString(i))) ==null) {
					count++;
					j--;
				} else if (chess.get(Integer.toString(j).concat(Integer.toString(i))) == -1) {
					break;
				}
			}
		}
		// attack opp diag down
		j = n - r_q + 1;
		for (int i = c_q - 2; i >= 0; i--) {
			if (j + i < n + n) {
				if (chess.get(Integer.toString(j).concat(Integer.toString(i))) ==null) {
					count++;
					j++;
				} else if (chess.get(Integer.toString(j).concat(Integer.toString(i))) == -1) {
					break;
				}
			}
		}
		// attack opp diag up
		j = n - r_q - 1;
		for (int i = c_q; i < n; i++) {
			if (j >= 0) {
				if (chess.get(Integer.toString(j).concat(Integer.toString(i))) ==null) {
					count++;
					j--;
				} else if (chess.get(Integer.toString(j).concat(Integer.toString(i))) == -1) {
					break;
				}
			}
		}
		System.out.println(count);
		// board
//		for (int i = 0; i < chess.length; i++) {
//			for (j = 0; j < chess.length; j++) {
//				System.out.print(chess[i][j] + "	");
//			}
//			System.out.println();
//		}

		return count;

	}
}
