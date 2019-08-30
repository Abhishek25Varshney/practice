public class HackerLand {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] track = { { 2, 2, 3 }, { 3, 1, 4 }, { 4, 4, 4 } };
		System.out.println(gridlandMetro(4, 4, 3, track));

	}

	static int gridlandMetro(int n, int m, int k, int[][] track) {
		int grid[][] = new int[n][m];
		int trackCells=0;
		for (int i = 0; i < k; i++) {
			int c1=track[i][1];
			int c2=track[i][2];
			int d=(c2-c1)+1;
			trackCells+=d;
		
		}
		return (n*m)-trackCells;

	}
	
	static private void fillTrack(int grid[][],int r,int c1,int c2) {
		for (int i = c1-1; i < c2; i++) {
			System.out.println(c1+" "+c2);
			grid[r-1][i]=1;
		}
	}
}
