package my;
import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.function.Consumer;

public class FileChannelWriter implements Closeable {
	
	private FileChannel channel;
	
	@Override
	public void close() throws IOException {
		if (channel != null) channel.close();
	}
	
	public void write(String s) {
		byte[] strBytes = s.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
        buffer.put(strBytes);
        buffer.flip();
        try {
			channel.write(buffer);
		} catch (IOException e) {
			new RuntimeException(e);
		}
	}
	
    public static void write(String fileName, Consumer<FileChannelWriter> consumer) {
    	try (RandomAccessFile stream = new RandomAccessFile(fileName, "rw");
             FileChannel channel = stream.getChannel();
    		 FileChannelWriter instance = new FileChannelWriter();) 
    	{
	    	instance.channel = channel;
	        consumer.accept(instance);
    	} catch (Exception e) {
    		new RuntimeException(e);
    	}
    }
}
