package practice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GameArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> arr=new LinkedList<>();
		arr.add(1);
		arr.add(3);
		arr.add(5);
		arr.add(7);
		arr.add(9);
		
		System.out.println(gamingArray(arr));
	}

	public static String gamingArray(List<Integer> arr) {
		String[] players= {"BOB","ANDY"};
		
		boolean activePlayer=false;
		while(!arr.isEmpty()) {
			if(activePlayer) {
				System.out.println("Player Move : "+players[1]);
			}else {
				System.out.println("Player Move : "+players[0]);
			}
			playerMove(arr);
			activePlayer=!activePlayer;
		}
		
		if(!activePlayer) {
			return players[1];
		}else {
			return players[0];
		}
	}
	
	public static void playerMove(List<Integer> arr) {
		int largetIndex=0;
		int largestNumber=arr.get(0);
		for (int i = 0; i < arr.size(); i++) {
			if(largestNumber<arr.get(i)) {
				largestNumber=arr.get(i);
				largetIndex=i;
			}
		}
		for (int i = (arr.size()-1); i >=largetIndex; i--) {
			System.out.println("Removed "+arr.get(i));
			arr.remove(i);
		}
		
	}

}
