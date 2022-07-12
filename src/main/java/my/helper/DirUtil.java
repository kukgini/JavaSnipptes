package my.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;


/**
 * https://howtodoinjava.com/java8/java-8-list-all-files-example/
 * http://forum.falinux.com/zbxe/?document_srl=588577&mid=lecture_tip&comment_srl=517498&sort_index=readed_count&order_type=asc&l=ru
 * https://www.programcreek.com/java-api-examples/?class=java.nio.file.DirectoryStream&method=close
 * https://howtodoinjava.com/java8/java-8-list-all-files-example/
 */
public class DirUtil {
	public static void clear(String path) throws IOException {
		clear(Paths.get(path));
	}
	public static void clear(Path path) throws IOException {
		Files.walk(path)
		    .map(Path::toFile)
		    .filter(File::isFile)
		    .forEach(File::delete);
	}
    public static void ls(String path, String glob, Consumer<Path> block) {
        try (DirectoryStream<Path> resource = Files.newDirectoryStream(Paths.get(path), glob)){
            resource.forEach(block);
        } catch (IOException e) {
            new RuntimeException(e);
        }
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
	public static Stream<Path> walk(String path) {
		return walk(Paths.get(path));
	}
	public static Stream<Path> walk(Path path) {
		try {
			return Files.walk(path);
		} catch (IOException e) {
			new RuntimeException(e);
		}
		return Stream.empty();
	}
	public static Stream<Path> walkFiles(String path) {
		return walkFiles(Paths.get(path));
	}
	public static Stream<Path> walkFiles(Path path) {
		return walk(path).filter(Files::isRegularFile);
	}
	public static Optional<Path> findFileFirst(String start, String filename) {
		try {
			return Files.walk(Paths.get(start))
					.filter(Files::isRegularFile)
					.filter(x -> {return x.getFileName().toString().equals(filename);})
					.findFirst();
		} catch (IOException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
}
