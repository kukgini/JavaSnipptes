package my.helper;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

/**
 * https://howtodoinjava.com/java8/java-8-list-all-files-example/
 * http://forum.falinux.com/zbxe/?document_srl=588577&mid=lecture_tip&comment_srl=517498&sort_index=readed_count&order_type=asc&l=ru
 * https://www.programcreek.com/java-api-examples/?class=java.nio.file.DirectoryStream&method=close
 */
public class DirResource {
	public static void ls(String path, String glob, Consumer<Path> block) {
		try (DirectoryStream<Path> resource = Files.newDirectoryStream(Paths.get(path), glob)){
			resource.forEach(block);
		} catch (IOException e) {
			new RuntimeException(e);
		}
	}
}
