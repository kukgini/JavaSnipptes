package my.helper;
import java.io.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class ProcessResourceUsage {
//    // METHOD 1 windows
//    static public void main(String[] args) {
//        new ProcessResourceBuilder().build("windows").use("dir", System.out::println);
//    }
    // METHOD 2
    static public void main(String[] args) {
        new ProcessResourceBuilder().build("posix").use("ls", (writer, reader) -> {
            reader.lines().forEach(System.out::println);
        });
    }
}
