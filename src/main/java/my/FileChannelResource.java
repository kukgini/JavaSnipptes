package my;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
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
	static public ByteBuffer stob(String s) {
		byte[] strBytes = s.getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
		buffer.put(strBytes);
		buffer.flip();
		return buffer;
	}
	static public void write(FileChannel channel, String s) {
		try {
			channel.write(stob(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
