package my.helper;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.function.Consumer;

public class FileChannelResource {
	
	public static void main(String[] args) {
		FileChannelResource.use("test.txt", x -> {
            FileChannelResource.write(x, "연습입니다.");
		});
	}
    static public void use(String fileName, Consumer<FileChannel> consumer) {
    	try (RandomAccessFile stream = new RandomAccessFile(fileName, "rw");
             FileChannel channel = stream.getChannel();
        ){
	        consumer.accept(channel);
    	} catch (Exception e) {
    		new RuntimeException(e);
    	}
    }
    static public ByteBuffer stob(String contents, String encoding) throws UnsupportedEncodingException {
		byte[] strBytes = contents.getBytes(encoding);
		ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
		buffer.put(strBytes);
		buffer.flip();
		return buffer;
	}
    static public void write(FileChannel channel, String contents) {
        write(channel, contents, "UTF-8");
    }
    static public void write(FileChannel channel, String contents, String encoding) {
		try {
			channel.write(stob(contents, encoding));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
