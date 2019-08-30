package practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SuperComputer {
	// Complete the twoPluses function below.
	static int twoPluses(String[] grid) {
		List<Integer> pluses = new ArrayList<>();
		Map<List<Integer>, Integer> nodesToArea = new HashMap<>();
		for (int i = 0; i < grid.length; i++) {
			String sequence = grid[i];
			for (int j = 0; j < sequence.length(); j++) {
				if (sequence.charAt(j) == 'G') {
					int area = 1;
					List<Integer> key = new ArrayList<>();
					key.add((i*10)+j);
					int leftL = left(grid, i, j);
					int rightL = right(grid, i, j);
					int upL = up(grid, i, j);
					int downL = down(grid, i, j);
					int min = 0;
					if (leftL > 0 && rightL > 0 && upL > 0 && downL > 0) {
						min = findMinCommonLengtj(leftL, rightL, upL, downL);
						area = (min * 4) + 1;
					}
					addLeftKey(i, j, key, min);
					addRightKey(i, j, key, min);
					addUpKey(i, j, key, min);
					addDownKey(i, j, key, min);
					nodesToArea.put(key, area);
				}
			}
		}

		List<List<Integer>> keySet = new ArrayList<>(nodesToArea.keySet());
		List<List<String>> validPluse = new ArrayList<>();
		for (int i = 0; i < nodesToArea.size(); i++) {
			List<Integer> plus = keySet.get(i);
			System.out.println("=="+plus);
			for (Map.Entry<List<Integer>, Integer> entry : nodesToArea.entrySet()) {
				System.out.println("MAP " + entry.getKey() + " " + entry.getValue());
			}
			for (int j = i + 1; j < nodesToArea.size(); j++) {
				
				List<Integer> anotherPlus = keySet.get(j);
				List<Integer> common = new ArrayList<>(anotherPlus);
				common.retainAll(plus);
				if(!common.isEmpty()) {
					if(nodesToArea.containsKey(plus)&&nodesToArea.containsKey(anotherPlus)) {
						int currentArea=nodesToArea.get(plus);
						int overlappedArea=nodesToArea.get(anotherPlus);
						if(currentArea>=overlappedArea) {
							System.out.println("REMOVED "+anotherPlus);
							nodesToArea.remove(anotherPlus);
						}else {
							System.out.println("removed "+plus);
							nodesToArea.remove(plus);
						}	
					}
					
				}
				
			}
		}

		for (Map.Entry<List<Integer>, Integer> entry : nodesToArea.entrySet()) {
			System.out.println("MAP " + entry.getKey() + " " + entry.getValue());
			 pluses.add(entry.getValue());
		}
		if (pluses.size() < 2) {
			return 0;
		} else {
			Collections.sort(pluses);
			return pluses.get(pluses.size() - 1) * pluses.get(pluses.size() - 2);
		}
	}

	static int findMinCommonLengtj(int l, int r, int u, int d) {
		int min = l;
		if (min > r) {
			min = r;
		}
		if (min > u) {
			min = u;
		}
		if (min > d) {
			min = d;
		}
		return min;
	}

	static int left(String[] grid, int i, int j) {
		String sequence = grid[i];
		int count = 0;
		for (int k = j - 1; k >= 0; k--) {
			if (sequence.charAt(k) == 'G') {
				count += 1;
			} else {
				break;
			}
		}

		return count;
	}

	static int right(String[] grid, int i, int j) {
		String sequence = grid[i];
		int count = 0;
		for (int k = j + 1; k < sequence.length(); k++) {
			if (sequence.charAt(k) == 'G') {
				count += 1;
			} else {
				break;
			}
		}

		return count;
	}

	static int up(String[] grid, int i, int j) {
		int count = 0;
		for (int j2 = i - 1; j2 >= 0; j2--) {
			String sequence = grid[j2];
			if (sequence.charAt(j) == 'G') {
				count += 1;
			} else {
				break;
			}
		}

		return count;
	}

	static int down(String[] grid, int i, int j) {
		int count = 0;
		for (int j2 = i + 1; j2 < grid.length; j2++) {
			String sequence = grid[j2];
			if (sequence.charAt(j) == 'G') {
				count += 1;
			} else {
				break;
			}
		}

		return count;
	}

	static void addLeftKey(int i, int j, List<Integer> key, int minL) {
		int counter = 0;
		int k = j - 1;
		while (counter != minL && k >= 0) {
			key.add((i*10)+k);
			k = k - 1;
			counter += 1;
		}

	}

	static void addRightKey(int i, int j, List<Integer> key, int minL) {
		int counter = 0;
		int k = j + 1;
		while (counter != minL) {
			key.add((i*10)+k);
			k = k + 1;
			counter += 1;
		}

	}

	static void addUpKey(int i, int j, List<Integer> key, int minL) {
		int counter = 0;
		int j2 = i - 1;
		while (counter != minL && j2 >= 0) {
			key.add((j2*10)+j);
			j2 = j2 - 1;
			counter += 1;
		}

	}

	static void addDownKey(int i, int j, List<Integer> key, int minL) {
		int counter = 0;
		int j2 = i + 1;
		while (counter != minL) {
			key.add((j2*10)+j);
			j2 = j2 + 1;
			counter += 1;
		}
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		String[] grid = { "BBBGBGBBB", "BBBGBGBBB", "BBBGBGBBB", "GGGGGGGGG", "BBBGBGBBB", "BBBGBGBBB","GGGGGGGGG","BBBGBGBBB","BBBGBGBBB","BBBGBGBBB" };

		System.out.println(twoPluses(grid));
	}

}
