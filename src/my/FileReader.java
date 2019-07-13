package my;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.function.Consumer;

public class FileReader {
    public static void read(String filename, Consumer<String> consumer) {
        Charset charset = Charset.defaultCharset();
        try (FileInputStream inputStream = new FileInputStream(filename);
             Scanner sc = new Scanner(inputStream, charset.name());
            ) 
        {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                consumer.accept(line);
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        read(".project", System.out::println);
    }
}
