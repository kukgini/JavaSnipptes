package my.regex;

import java.util.regex.*;

public class BasicThreeWays {

    public static void main(String args[]){
        //1st way
        Pattern p = Pattern.compile(".s");//. represents single character
        Matcher m = p.matcher("as");
        boolean b1 = m.matches();

        //2nd way
        boolean b2 = Pattern.compile(".s").matcher("as").matches();

        //3rd way
        boolean b3 = Pattern.matches(".s", "as");

        System.out.format(" first:%s%n second:%s%n third:%s%n", b1, b2, b3);
    }
}
