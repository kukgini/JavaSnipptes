package my;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class StringCompressor {

	public static void main(String[] args) {
		BiFunction<Integer,Integer,String> charactorFormatter = (c,count) -> {
			return String.format("%s%s", new Character((char)c.intValue()), count);
		};
		compress("ABBCCCDDDDDEEEEEFFFFFFG".chars(), charactorFormatter, x -> {
			System.out.print(x);
		});
	}
	public static void compress(IntStream s, BiFunction<Integer,Integer,String> formatter, Consumer<String> block) {
		final Pair<Integer,Integer> p = new Pair<Integer,Integer>(null, null);			
		s.boxed().forEach(x -> {
			if (p.getKey() == null) {
				p.setKey(x);
				p.setVal(1);
			} else 
			if (x.equals(p.getKey())){
				p.setVal(p.getVal() + 1);
			} else {
				block.accept(formatter.apply(p.getKey(), p.getVal()));
				p.setKey(x);
				p.setVal(1);
			}
		});
		if (p.getKey() != null) {
			block.accept(formatter.apply(p.getKey(), p.getVal()));	
		}
	}
}
