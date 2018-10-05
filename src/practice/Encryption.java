package practice;

import java.io.IOException;

public class Encryption {
	public static void main(String[] args) throws IOException {
		encryption("feed the dog");
	}

	static String encryption(String s) {
		String string = s.replace(" ", "");
		String output = "";
		int length = string.length();
		int row = (int) Math.sqrt(length);
		int column = row + 1;
		int cond = row * column;
		while (cond < length) {
			if (row < column) {
				row += 1;
			} else {
				column += 1;
			}
			cond = row * column;

		}
		int index = 0;
		char[][] matrix = new char[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (index < length) {
					matrix[i][j] = string.charAt(index);
					index += 1;

				} else {
					matrix[i][j] = ' ';
				}
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				if (matrix[j][i] != ' ') {
					output = output.concat(Character.toString(matrix[j][i]));
				}
			}
			output = output.concat(" ");
		}

		System.out.println(output);
		return output;

	}
}
