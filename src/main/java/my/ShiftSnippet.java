package my;

import java.math.BigInteger;

public class ShiftSnippet {
//	public static void main(String[] args) {
//		int a = 0x00000010; // 10진수 '16' 을 16진수로 표현
//		System.out.format("0x%08x%n", a);
//		System.out.format("%016d%n", new BigInteger(Integer.toBinaryString(a)));
//		a = a << 2;
//		System.out.format("0x%08x%n", a);
//		System.out.format("%016d%n", new BigInteger(Integer.toBinaryString(a)));
//	}
	public static void main(String[] args) {
		StringBuffer s = new StringBuffer("ABCDEF");
		s.insert(0, s.charAt(s.length()-1));
		s.deleteCharAt(s.length()-1);
		System.out.println(s);
	}
}
