package my.helper;

public class CaesarCipher {
	public static void main(String[] args) {
		String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String expected = "VWXYZABCDEFGHIJKLMNOPQRSTU";
		String result = encript(input, 5);
		System.out.println(result.equals(expected));
		System.out.println(result);
	}
	public static String encript(String input, int distance) {
		StringBuffer s = new StringBuffer(input);
		int end = (s.length()); end = end < 0 ? 0 : end;
		int start = end - distance; start = (start < 0) ? 0 : start;
		String temp = s.substring(start, end);
		s.delete(start, end);
		s.insert(0, temp);
		return s.toString();
	}
}
