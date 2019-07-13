package my;
// https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
// Java program to rotate a matrix by 90 degrees 

class MetrixSpinner {
	static void spinMatrix(int n, int mat[][]) {
		// Consider all squares one by one
		for (int x = 0; x < n / 2; x++) {
			// Consider elements in group of 4 in
			// current square
			for (int y = x; y < n - x - 1; y++) {
				int temp = mat[x][y];

				fromRightToTop(n,x,y,mat);
				fromBottomToRight(n,x,y,mat);
				fromLeftToBottom(n,x,y,mat);

				mat[n - 1 - y][x] = temp;
			}
		}
	}

	static void fromRightToTop(int n, int x, int y, int[][] mat) {
		mat[x][y] = mat[y][n - 1 - x];
	}
	static void fromBottomToRight(int n, int x, int y, int[][] mat) {
		mat[y][n - 1 - x] = mat[n - 1 - x][n - 1 - y];
	}
	static void fromLeftToBottom(int n, int x, int y, int[][] mat) {
		mat[n - 1 - x][n - 1 - y] = mat[n - 1 - y][x];
	}
	
	static void printMatrix(int N, int mat[][]) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(" " + mat[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	public static void main(String[] args) {
		int N = 4;
		int mat[][] = { 
				{ 1, 2, 3, 4 }, 
				{ 5, 6, 7, 8 }, 
				{ 9, 10, 11, 12 }, 
				{ 13, 14, 15, 16 } 
		};
		spinMatrix(N, mat);
		printMatrix(N, mat);
	}
}