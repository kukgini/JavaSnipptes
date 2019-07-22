package my;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCompressor {

	public static void main(String[] args) {
		compress("ABBCCCDDDDDEEEEEFFFFFFG", x -> {
			System.out.print(x);
		});
	}
	public static void compress(String str, Consumer<String> block) {
		final Pair<Integer,Integer> p = new Pair<Integer,Integer>(null, null);	
		IntStream s = str.chars();		
		s.boxed().forEach(x -> {
			if (p.getKey() == null) {
				p.setKey(x);
				p.setVal(1);
			} else 
			if (x.equals(p.getKey())){
				p.setVal(p.getVal() + 1);
			} else {
				block.accept(String.format("%s%s", new Character((char)p.getKey().intValue()), p.getVal()));
				p.setKey(x);
				p.setVal(1);
			}
		});
		block.accept(String.format("%s%s", new Character((char)p.getKey().intValue()), p.getVal()));	
	}
}
