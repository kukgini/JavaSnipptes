package my;

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
		return metrix[x][y];
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
	// 이걸 어떻게 구현하지...
	public boolean isSurroundedBy(int n, int x, int y) {
		if ((x - 1) < 0 || (x + 1) >= metrix[y].length - 1) return false;
		if ((y - 1) < 0 || (y + 1) >= metrix.length - 1) return false;
		boolean b = true;
		b &= (metrix[x - 1][y] == n) ? true : isSurroundedBy(n, x - 1, y);
		b &= (metrix[x + 1][y] == n) ? true : isSurroundedBy(n, x + 1, y);
		b &= (metrix[x][y - 1] == n) ? true : isSurroundedBy(n, x, y - 1);
		b &= (metrix[x][y + 1] == n) ? true : isSurroundedBy(n, x, y + 1);
		System.out.println();
		System.out.printf("%s, %s surrounded by %s", x, y, b);
		return b;
	}
}
