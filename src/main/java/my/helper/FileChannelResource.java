package my.helper;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.function.BiConsumer;

public class FileChannelResource {
    public void use(String fileName, BiConsumer<FileChannelResource,FileChannel> consumer) {
    	try (RandomAccessFile stream = new RandomAccessFile(fileName, "rw");
             FileChannel channel = stream.getChannel();
        ){
	        consumer.accept(this,channel);
    	} catch (Exception e) {
    		new RuntimeException(e);
    	}
    }
    public ByteBuffer stob(String contents, String encoding) throws UnsupportedEncodingException {
		byte[] strBytes = contents.getBytes(encoding);
		ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
		buffer.put(strBytes);
		buffer.flip();
		return buffer;
	}
    public void writeOn(FileChannel channel, String contents) {
        writeOn(channel, contents, "UTF-8");
    }
    public void writeOn(FileChannel channel, String contents, String encoding) {
		try {
			channel.write(stob(contents, encoding));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
