package my.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ConsoleHelper {
    public static void prompt(String inquiaryMessage, Consumer<String> consumer) {
        System.out.print(inquiaryMessage);
        String answer = null;
        try (Scanner scanIn = new Scanner(System.in)) {
            answer = scanIn.nextLine();
        }
        if (answer != null) {
            consumer.accept(answer);
        }
    }
    public static Stream<String> lines() {
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "MS949"));
            return br.lines();
        } catch (UnsupportedEncodingException e) {
        	return Stream.empty();
        }
    }
    public static void lines(Consumer<String> consumer) {
        lines().forEach(consumer::accept);
    }
}
