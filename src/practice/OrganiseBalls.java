package practice;

import java.util.Arrays;

public class OrganiseBalls {
	public static void main(String[] args) {
		int[][] container = new int[2][2];
		container[0][0] = 0;
		container[0][1] = 2;
		container[1][0] = 1;
		container[1][1] = 1;
		System.out.println(organizingContainers(container));
	}

	static String organizingContainers(int[][] container) {
		int n = container.length;

		long[] rowSums = new long[n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				rowSums[r] += container[r][c];
			}
		}

		long[] colSums = new long[n];
		for (int c = 0; c < n; c++) {
			for (int r = 0; r < n; r++) {
				colSums[c] += container[r][c];
			}
		}

		return isSame(rowSums, colSums);
	}

	static String isSame(long[] a, long[] b) {
		Arrays.sort(a);
		Arrays.sort(b);

		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return "Impossible";
			}
		}
		return "Possible";
	}
}
