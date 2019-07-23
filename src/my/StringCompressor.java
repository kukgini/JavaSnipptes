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
		final Pair<Character, Integer> p = new Pair<Character, Integer>(null, null);
		s.boxed().forEach(x -> {
			char c = (char)x.intValue();
			if (p.getKey() == null) {
				p.setKey(c);
				p.setVal(1);
			} else if (p.getKey().equals(c)) {
				p.setVal(p.getVal() + 1);
			} else {
				block.accept(formatter.apply(p.getKey(), p.getVal()));
				p.setKey(c);
				p.setVal(1);
			}
		});
		if (p.getKey() != null) {
			block.accept(formatter.apply(p.getKey(), p.getVal()));
		}
	}

	public static String repeate(int i, String s) {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < i; j++)
			sb.append(s);
		return sb.toString();
	}
}
