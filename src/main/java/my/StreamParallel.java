package my;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class StreamParallel {
	
	private static ExecutorService executor = Executors.newFixedThreadPool(4);
	
	public static void main(String[] args) {
		Stream<String> s1 = FileReader.lines(".project");
		Stream<String> s2 = FileReader.lines(".classpath");
		
		executor.submit(() -> s1.forEach(System.out::println));
		executor.submit(() -> s2.forEach(System.out::println));
	}
	
}
