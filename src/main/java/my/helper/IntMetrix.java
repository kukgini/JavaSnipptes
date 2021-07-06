package my.helper;

public class IntMetrix {
	
	private int[][] metrix;
	
	public IntMetrix(int[][] init) {
		this.metrix = init;
	}
	public void print(int n) {
		for (int y = 0; y < n; y++) {
			if (y == 0) {
				for (int x = 0; x < n; x++) {
					if (x == 0) {
						System.out.print("  ");
					}
					System.out.print(" " + x);
				}
				System.out.println();
				for (int x = 0; x < n; x++) {
					if (x == 0) {
						System.out.print("  ");
					}
					System.out.print("---");
				}
				System.out.println();
			}
			System.out.print(y + ":");
			for (int x = 0; x < n; x++) {
				System.out.print(" " + metrix[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}
	public int get(int x, int y) {
		return metrix[y][x];
	}
	public void spinCounterClockwise(int n) {
		for (int y = 0; y < n / 2; y++) {
			for (int x = y; x < n - y - 1; x++) {
				int temp = metrix[y][x];

				fromRightToTop(n,y,x);
				fromBottomToRight(n,y,x);
				fromLeftToBottom(n,y,x);

				metrix[n - 1 - x][y] = temp;
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
	public boolean isSurroundedBy(int n, int x, int y) {
		boolean b = true;
		b &= westIs(n, x, y);
		b &= eastIs(n, x, y);
		b &= northIs(n, x, y);
		b &= southIs(n, x, y);
		return b;
	}
	private boolean westIs(int n, int x, int y) {
		if (x < 1) return false;
		return (metrix[x - 1][y] == n) ? true : westIs(n, x - 1, y);
	}
	private boolean eastIs(int n, int x, int y) {
		if (x >= metrix[y].length - 1) return false;
		return (metrix[x + 1][y] == n) ? true : eastIs(n, x + 1, y);
	}
	private boolean northIs(int n, int x, int y) {
		if (y < 1) return false;
		return (metrix[x][y - 1] == n) ? true : northIs(n, x, y - 1);
	}
	private boolean southIs(int n, int x, int y) {
		if (y >= metrix.length - 1) return false;
		return (metrix[x][y + 1] == n) ? true : southIs(n, x, y + 1);
	}
}
