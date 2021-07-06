package my.helper;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;

public class ReadFromEndOfFile {
	static public void read(String inputFilename) {
	    try
	    {
	        RandomAccessFile file = new RandomAccessFile(inputFilename, "r");
	        file.seek(file.length()); // move to end of file.
	        InputStream is = Channels.newInputStream(file.getChannel());
	        //readStream(is);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
