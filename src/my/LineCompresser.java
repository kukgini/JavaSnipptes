package my;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class LineCompresser {
	
    public static void main(String[] args) {
        BiFunction<String,Integer,String>lineFormatter = (line,count) -> {
            if (count > 1) {
                return String.format("%d#%s%n", count,line);
            } else {
                return String.format("%s%n", line);
            }
        };
		BiFunction<Character,Integer,String> charactorFormatter = (c,count) -> {
			return String.format("%s%s", c, count);
		};
        String filename = "data/TEXTFILE.TXT";
        LineCompresser.compress(FileReader.lines(filename), lineFormatter, x -> {
    		StringCompressor.compress(x.chars(), charactorFormatter, y -> {
    			System.out.print(y);
    		});
        });      
    }
    
	public static void compress(Stream<String> s, BiFunction<String,Integer,String> formatter, Consumer<String> block) {
		final Pair<String,Integer> p = new Pair<String,Integer>(null, null);			
		s.forEach(x -> {
			System.out.println("compress line:" + x);
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
