package my;

public class IntMetrix {
	
	private int[][] metrix;
	
	public IntMetrix(int[][] init) {
		this.metrix = init;
	}
	public void print(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(" " + metrix[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	public void spinCounterClockwise(int n) {
		for (int x = 0; x < n / 2; x++) {
			for (int y = x; y < n - x - 1; y++) {
				int temp = metrix[x][y];

				fromRightToTop(n,x,y);
				fromBottomToRight(n,x,y);
				fromLeftToBottom(n,x,y);

				metrix[n - 1 - y][x] = temp;
			}
		}
	}
	private void fromRightToTop(int n, int x, int y) {
		metrix[x][y] = metrix[y][n - 1 - x];
	}
	private void fromBottomToRight(int n, int x, int y) {
		metrix[y][n - 1 - x] = metrix[n - 1 - x][n - 1 - y];
	}
	private void fromLeftToBottom(int n, int x, int y) {
		metrix[n - 1 - x][n - 1 - y] = metrix[n - 1 - y][x];
	}
}
