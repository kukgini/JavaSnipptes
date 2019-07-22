package my;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCompressor {

	public static void main(String[] args) {
		Stream<String> s = compress("ABBCCCDDDDDEEEEEFFFFFFG");
		String result = s.collect(Collectors.joining());
		System.out.println(result);
	}
	public static Stream<String> compress(String str) {
		Stream.Builder<String> b =  Stream.<String>builder();
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
				b.add(String.format("%s%s", new Character((char)p.getKey().intValue()), p.getVal()));
				p.setKey(x);
				p.setVal(1);
			}
		});
		b.add(String.format("%s%s", new Character((char)p.getKey().intValue()), p.getVal()));
		return b.build();	
	}
}
