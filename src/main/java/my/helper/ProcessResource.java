package my.helper;
import java.io.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class ProcessResource {
//    // METHOD 1
//    static public void main(String[] args) {
//        new ProcessResourceBuilder().build("windows").use("ls", System.out::println);
//    }
    // METHOD 2
    static public void main(String[] args) {
        new ProcessResourceBuilder().build("posix").use("ls", (writer, reader) -> {
            reader.lines().forEach(System.out::println);
        });
    }
    public abstract ProcessBuilder command(String cmd);
    public abstract String getEncoding();
    public void use(String cmd, BiConsumer<PrintWriter,BufferedReader> consumer) {
        try {
            Process process = command(cmd).start();
            try (BufferedReader reader = getReader(process.getInputStream());
                 PrintWriter writer = getWriter(process.getOutputStream());)
            {
                consumer.accept(writer,reader);
            }
            process.waitFor();
        }
        catch (Exception e) { e.printStackTrace();  }
    }
    public String use(String cmd, Consumer<String> c) {
        String result = null;
        try {
            Process process = command(cmd).start();
            process.waitFor();
            try (InputStream stream = process.getInputStream()) {
            	BufferedReader reader = getReader(stream);
                reader.lines().forEach(c::accept);
            }
        } 
        catch (IOException e) { e.printStackTrace();  }
        catch (InterruptedException e) { e.printStackTrace();  } 
        return result;
    }
    public PrintWriter getWriter(OutputStream stream) {
        return new PrintWriter(new OutputStreamWriter(stream));
    }
    public BufferedReader getReader(InputStream input) throws IOException {
        return new BufferedReader(new InputStreamReader(input, getEncoding()));
    }		
    public static class WindowsProcessResource extends ProcessResource {
        @Override
        public ProcessBuilder command(String cmd) {
            ProcessBuilder pb = new ProcessBuilder();
            pb.command("cmd", "/c", cmd);
            return pb;
        }
        @Override
        public String getEncoding() {
            return "ms949";
        }
    }
    public static class PosixProcessResource extends ProcessResource {
        @Override
        public ProcessBuilder command(String cmd) {
            ProcessBuilder pb = new ProcessBuilder();
            pb.command("bash","-c",cmd);
            return pb;
        }
        @Override
        public String getEncoding() {
            return "UTF-8";
        }
    }
}
