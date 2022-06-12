package my.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CapturingGroupExample {
    public static void main( String args[] ) {
        String input = "This order was placed for QT3000! OK?";

        String expression = "(.*)\\d+(.*)";
        Pattern pattern = Pattern.compile(expression);
        Matcher m = pattern.matcher(input);

        if (m.find( )) {
            for (int i = 0; i <= m.groupCount(); i++) {
                System.out.printf("capturing group %s = %s%n", i, m.group(i));
            }
        } else {
            System.out.println("NO MATCH");
        }
    }
}
