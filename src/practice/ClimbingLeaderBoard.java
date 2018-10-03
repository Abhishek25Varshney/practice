package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ClimbingLeaderBoard {

	public static void main(String[] args) {
		int[] scores = { 100, 90, 90, 80, 75, 60 };
		int[] alice = { 50, 65, 77, 90, 102 };
		for (int j =0; j <alice.length ; j++) {
			System.out.println(climbingLeaderboard(scores, alice)[j]);	
		}
		
	}

	static int[] climbingLeaderboard(int[] scores, int[] alice) {
		int[] ranking = new int[alice.length];
		int rank=0;
		for (int i = 0; i < alice.length; i++) {
			scores = Arrays.copyOf(scores, scores.length + 1);
			scores[scores.length - 1] = alice[i];
			scores = sort(scores);
			Map<Integer, Integer> board = createBoard(scores);
			ranking[rank]=board.get(alice[i]);
			rank++;
		}

		return ranking;

	}

	static int[] bubbleSort(int arr[]) {
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] < arr[j + 1]) {
					// swap temp and arr[i]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

		return arr;
	}

	static Map<Integer, Integer> createBoard(int arr[]) {
		Map<Integer, Integer> board = new HashMap<>();
		int rank = 1;
		for (int i = arr.length-1; i >=0 ; i--) {
			if (!board.containsKey(arr[i])) {

				board.put(arr[i], rank);
				rank += 1;
			}
		}
		return board;
	}
	
	static int[] sort(int arr[])
    {
        int n = arr.length;
 
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
 
        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
 
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
        
        return arr;
    }
	static void heapify(int arr[], int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
 
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
 
        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
}
