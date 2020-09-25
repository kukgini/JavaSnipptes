package my;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GeneralFunctions {
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
	static public void sleep(int millisec) {
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			// ignore
		}
	}
}
