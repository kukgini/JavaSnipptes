package my.helper;
// https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
// Java program to rotate a matrix by 90 degrees 

class MetrixSpinner {
	public static void main(String[] args) {
		int size = 4;
		int mat[][] = { 
				{ 1, 2, 3, 4 }, 
				{ 5, 6, 7, 8 }, 
				{ 9, 10, 11, 12 }, 
				{ 13, 14, 15, 16 } 
		};
		IntMetrix m = new IntMetrix(mat);
		m.print(size);
		m.spinCounterClockwise(size);
		m.print(size);
	}
}