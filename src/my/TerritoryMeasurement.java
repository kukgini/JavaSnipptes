package my;

class TerritoryMeasurement {
	public static void main(String[] args) {
		int n = 4;
		int matrix[][] = { 
				{ 1, 1, 1, 0 }, 
				{ 1, 0, 1, 1 }, 
				{ 1, 0, 0, 1 }, 
				{ 1, 1, 1, 1 } 
		};
		IntMetrix m = new IntMetrix(matrix);
		m.print(n);
		//System.out.printf("x=%s, y=%s is %s and surrouned by 1 should be false : %s%n", 0, 0, m.get(0, 0), m.isSurroundedBy(1, 0, 0));
		//System.out.printf("x=%s, y=%s is %s and surrouned by 1 should be false : %s%n", 3, 0, m.get(3, 0), m.isSurroundedBy(1, 3, 0));
		System.out.printf("x=%s, y=%s is %s and surrouned by 1 should be false : %s%n", 2, 2, m.get(2, 2), m.isSurroundedBy(1, 2, 2));
	}
}