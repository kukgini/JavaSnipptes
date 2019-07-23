package my;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWriter {
    // http://www.baeldung.com/java-write-to-file
    public static void write(String fileName, Consumer<String> consumer) {
        RandomAccessFile stream = new RandomAccessFile(fileName, "rw");
        FileChannel channel = stream.getChannel();
        consumer.accept(channel);
        byte[] strBytes = value.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
        buffer.put(strBytes);
        buffer.flip();
        channel.write(buffer);
        stream.close();
        channel.close();

        // verify
        //RandomAccessFile reader = new RandomAccessFile(fileName, "r");
        //assertEquals(value, reader.readLine());
        //reader.close();
    }
}
