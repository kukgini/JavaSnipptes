package my;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


/**
 * https://howtodoinjava.com/java8/java-8-list-all-files-example/
 */
public class DirUtil {
	public static void clean(String path) throws IOException {
		Files.walk(Paths.get(path))
		    .map(Path::toFile)
		    .filter(File::isFile)
		    .forEach(File::delete);
	}
	public static Stream<Path> list(String path) {
		try {
			return Files.list(Paths.get(path));
		} catch (IOException e) {
			new RuntimeException(e);
		}
		return Stream.empty();
	}
	public static Stream<Path> listFiles(String path) {
		return list(path).filter(Files::isRegularFile);
	}

}
