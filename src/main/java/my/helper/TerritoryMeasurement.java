package my.helper;

import my.helper.IntMetrix;

class TerritoryMeasurement {
	public static void main(String[] args) {
		int n = 4;
		int matrix[][] = { 
				{ 1, 1, 1, 0 }, 
				{ 1, 0, 1, 1 }, 
				{ 1, 0, 0, 1 }, 
				{ 1, 0, 1, 1 } 
		};
		IntMetrix m = new IntMetrix(matrix);
		m.print(n);
		System.out.printf("[%s,%s]=%s surrouned : %s%n", 0, 0, m.get(0, 0), m.isSurroundedBy(1, 0, 0));
		System.out.printf("[%s,%s]=%s surrouned : %s%n", 1, 0, m.get(1, 0), m.isSurroundedBy(1, 1, 0));
		System.out.printf("[%s,%s]=%s surrouned : %s%n", 2, 0, m.get(2, 0), m.isSurroundedBy(1, 2, 0));
		System.out.printf("[%s,%s]=%s surrouned : %s%n", 3, 0, m.get(3, 0), m.isSurroundedBy(1, 3, 0));
		System.out.println();
		System.out.printf("[%s,%s]=%s surrouned : %s%n", 0, 1, m.get(0, 1), m.isSurroundedBy(1, 0, 1));
		System.out.printf("[%s,%s]=%s surrouned : %s%n", 1, 1, m.get(1, 1), m.isSurroundedBy(1, 1, 1));
		System.out.printf("[%s,%s]=%s surrouned : %s%n", 2, 1, m.get(2, 1), m.isSurroundedBy(1, 2, 1));
		System.out.printf("[%s,%s]=%s surrouned : %s%n", 3, 1, m.get(3, 1), m.isSurroundedBy(1, 3, 1));
		System.out.println();
		System.out.printf("[%s,%s]=%s surrouned : %s%n", 0, 2, m.get(0, 2), m.isSurroundedBy(1, 0, 2));
		System.out.printf("[%s,%s]=%s surrouned : %s%n", 1, 2, m.get(1, 2), m.isSurroundedBy(1, 1, 2));
		System.out.printf("[%s,%s]=%s surrouned : %s%n", 2, 2, m.get(2, 2), m.isSurroundedBy(1, 2, 2));
		System.out.printf("[%s,%s]=%s surrouned : %s%n", 3, 2, m.get(3, 2), m.isSurroundedBy(1, 3, 2));
	}
}