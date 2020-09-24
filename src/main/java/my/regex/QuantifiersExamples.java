package my.regex;

import java.util.regex.*;

public class QuantifiersExamples {
    public static void main(String args[]){
        System.out.println("? quantifier ....");
        eval("[amn]?", "a");//true (a or m or n comes one time)
        eval("[amn]?", "aaa");//false (a comes more than one time)
        eval("[amn]?", "aammmnn");//false (a m and n comes more than one time)
        eval("[amn]?", "aazzta");//false (a comes more than one time)
        eval("[amn]?", "am");//false (a or m or n must come one time)

        System.out.println("+ quantifier ....");
        eval("[amn]+", "a");//true (a or m or n once or more times)
        eval("[amn]+", "aaa");//true (a comes more than one time)
        eval("[amn]+", "aammmnn");//true (a or m or n comes more than once)
        eval("[amn]+", "aazzta");//false (z and t are not matching pattern)

        System.out.println("* quantifier ....");
        eval("[amn]*", "ammmna");//true (a or m or n may come zero or more times)
    }
    public static void eval(String pattern, String input) {
        System.out.format("pattern=%s\tinput=%s\t\t=>%s%n", pattern, input, Pattern.matches(pattern, input));
    }
}
