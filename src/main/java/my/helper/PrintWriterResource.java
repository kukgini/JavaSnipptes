package my.helper;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.function.Consumer;

public class PrintWriterResource  {
    public static void use(String fileName, Consumer<PrintWriter> consumer) {
    	try (FileWriter f = new FileWriter(fileName);
             PrintWriter w = new PrintWriter(f);
    	){
	        consumer.accept(w);
    	} catch (Exception e) {
    		new RuntimeException(e);
    	}
    }
}
