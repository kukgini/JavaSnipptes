package my.helper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.function.Consumer;

public class WindowsProcessResource {
    static public void main(String[] args) {
        WindowsProcessResource.use("dir", System.out::println);  
    }
    static public String use(String cmd, Consumer<String> c) {
        String result = null;
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("cmd", "/c", cmd);
        try {
            Process process = builder.start();
            process.waitFor();
            try (InputStream stream = process.getInputStream()) {
            	BufferedReader reader = inputStreamToBufferedReader(stream, "ms949");
                reader.lines().forEach(c::accept);
            }
        } 
        catch (IOException e) { e.printStackTrace();  }
        catch (InterruptedException e) { e.printStackTrace();  } 
        return result;
    }
    static public BufferedReader inputStreamToBufferedReader(InputStream input, String charset) throws UnsupportedEncodingException, IOException {		
        return new BufferedReader(new InputStreamReader(input, charset));	
    }		

}
