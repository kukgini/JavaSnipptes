package my;

import java.util.function.BiConsumer;

public class LineCompresser {
    private String previous = null;
    private int count = 0;

    public LineCompresser compress(String s, BiConsumer<Integer,String> consumer) {
        if (s != null && s.equals(previous)) {
            count++;
        } else {
            if (previous != null) {
                consumer.accept(count,previous);
            }
            previous = s;
            count = 1;
        }
        return this;
    }
    public void thenFinally(BiConsumer<Integer,String> consumer) {
        if (previous != null) {
            consumer.accept(count, previous);
        }
    }
    public static void main(String[] args) {
        BiConsumer<Integer,String>linePrinter = (c,s) -> {
            if (c > 1) {
                System.out.printf("%d#%s%n", c,s);
            } else {
                System.out.printf("%s%n", s);
            }
        };
        my.LineCompresser compresser = new my.LineCompresser();
        my.FileReader.read("data/TEXTFILE.TXT", line -> 
            compresser.compress(line, linePrinter));
        compresser.thenFinally(linePrinter);
    }
}
