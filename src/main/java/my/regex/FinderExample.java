package my.regex;

import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.regex.Matcher;

public class FinderExample {
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        while (true) {
            System.out.println("Enter regex pattern:");
            Pattern pattern = Pattern.compile(stdin.nextLine());
            System.out.println("Enter text:");
            Matcher matcher = pattern.matcher(stdin.nextLine());
            boolean found = false;
            while (matcher.find()) {
                System.out.println("I found the text "+matcher.group()+" starting at index "+
                    matcher.start()+" and ending at index "+matcher.end());
                found = true;
            }
            if(!found){
                System.out.println("No match found.");
            }
        }
    }
}
