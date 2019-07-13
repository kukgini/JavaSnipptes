package my;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirCleaner {
	public static void run(String path) throws IOException {
		Files.walk(Paths.get(path))
		    .map(Path::toFile)
		    .filter(File::isFile)
		    .forEach(File::delete);
	}
}
