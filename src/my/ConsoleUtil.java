package my;

import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleUtil {
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
}
