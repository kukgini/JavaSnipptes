package my.helper;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.function.Consumer;

public class PrintWriterResourceUsage {
	public static void main(String[] args) {
		PrintWriterResource.use("test.txt", x -> {
			x.println("test");
		});
	}
}
