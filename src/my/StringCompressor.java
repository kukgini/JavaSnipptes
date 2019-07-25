package my;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class StringCompressor {

	public static void main(String[] args) {
		BiFunction<Character, Integer, String> charactorFormatter = (c, n) -> {
			if (n > 2) {
				return String.format("%d%s", n, c);
			} else {
				return String.format("%0" + n + "d", 0).replace("0", Character.toString(c));
			}
		};
		compress("ABBCCCDDDDDEEEEEFFFFFFGG".chars(), charactorFormatter, x -> {
			System.out.print(x);
		});
	}

	public static void compress(IntStream s, BiFunction<Character, Integer, String> formatter, Consumer<String> block) {
		final Pair<Character, Integer> p = new Pair<Character, Integer>();
		s.boxed().forEach(x -> {
			char c = (char)x.intValue();
			if (p.v1 == null) {
				p.v1 = c;
				p.v2 = 1;
			} else if (p.v1.equals(c)) {
				p.v2 = (p.v2 + 1);
			} else {
				block.accept(formatter.apply(p.v1, p.v2));
				p.v1 = c;
				p.v2 = 1;
			}
		});
		if (p.v1 != null) {
			block.accept(formatter.apply(p.v1, p.v2));
		}
	}

	public static String repeate(int i, String s) {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < i; j++)
			sb.append(s);
		return sb.toString();
	}
}
