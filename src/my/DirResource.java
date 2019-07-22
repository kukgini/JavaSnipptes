package my;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class DirResource {
	public static void main(String[] args) {
		DirResource.ls(System.getProperty("java.io.tmpdir"), "*.txt", System.out::println);
	}
	public static void ls(String path, String glob, Consumer<Path> block) {
		try (DirectoryStream<Path> resource = Files.newDirectoryStream(Paths.get(path), glob)){
			resource.forEach(block);
		} catch (IOException e) {
			new RuntimeException(e);
		}
	}
}
