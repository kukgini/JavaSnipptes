package my;

import static my.FileChannelFunctions.write;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.function.Consumer;

public class FileChannelResource {
	
	public static void main(String[] args) {
		FileChannelResource.use("test.txt", x -> {
			write(x, "test");
		});
	}
	
    public static void use(String fileName, Consumer<FileChannel> consumer) {
    	try (RandomAccessFile stream = new RandomAccessFile(fileName, "rw");
             FileChannel channel = stream.getChannel();
        ){
	        consumer.accept(channel);
    	} catch (Exception e) {
    		new RuntimeException(e);
    	}
    }
}
