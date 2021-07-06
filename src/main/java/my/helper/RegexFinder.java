package my.helper;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFinder {
    public static void find(String input, String pattern, Consumer<String> c) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        while(m.find()){
            String b =  m.group();
            c.accept(b.substring(2, b.length()-2));
        }
    }
    public static void main(String[] args) {
    	String from = "abc$[A]$def$[B]$ghi";
    	String pattern = "\\$\\[.*?\\]\\$";
    	find(from, pattern, (s) -> System.out.println(">> " + s));
    }
}
