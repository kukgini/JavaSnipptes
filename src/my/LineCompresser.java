package my;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class LineCompresser {
	
    public static void main(String[] args) {
        BiFunction<Integer,String,String>lineFormatter = (c,s) -> {
            if (c > 1) {
                return String.format("%d#%s%n", c,s);
            } else {
                return String.format("%s%n", s);
            }
        };
        Stream.Builder<String> builder = Stream.builder();
        my.FileReader.read("data/TEXTFILE.TXT", builder::add);
        compress(builder.build(), lineFormatter, System.out::print);      
    }
    
	public static void compress(Stream<String> s, BiFunction<Integer,String,String> formatter, Consumer<String> block) {
		final Pair<String,Integer> p = new Pair<String,Integer>(null, null);			
		s.forEach(x -> {
			if (p.getKey() == null) {
				p.setKey(x);
				p.setVal(1);
			} else 
			if (x.equals(p.getKey())){
				p.setVal(p.getVal() + 1);
			} else {
				block.accept(formatter.apply(p.getVal(), p.getKey()));
				p.setKey(x);
				p.setVal(1);
			}
		});
		if (p.getKey() != null) {
			block.accept(formatter.apply(p.getVal(), p.getKey()));
		}
	}
}
