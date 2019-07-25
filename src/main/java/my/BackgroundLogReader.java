package my;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public class BackgroundLogReader {

    public static void main(String[] args) {
        try
        {
            // clear output directory
            Files.walk(Paths.get("OUTPUT"))
                .map(Path::toFile)
                .filter(File::isFile)
                .forEach(File::delete);
            
            new BackgroundLogReader().run();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private static final String errFilenamePattern = "OUTPUT/ERROUT-%d.txt";
    private static final String oppFilenamePattern = "OUTPUT/OPPOUT-%d.txt";

    private static final String inputFilename = "INPUT/INPUT.txt";

    private static final String errPrefix = "ERR#";
    private static final String oppPrefix = "OPP#";

    // LinkedBlockingQueue is effective when privicer:consumer = n:1
    // https://sungjk.github.io/2016/11/02/Queue.html
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public void run() throws Exception {
        OutputWriter err = new OutputWriter(errFilenamePattern, errPrefix).build();
        OutputWriter opp = new OutputWriter(oppFilenamePattern, oppPrefix).build();
        OutputWriter[] writers = new OutputWriter[]{err, opp};

        Thread t1 = new Thread(this::readStdinInBackground); t1.start();
        Thread t2 = new Thread(this::readFileInBackground);  t2.start();

        // generate stream from the queue. Optional is not required because only filter null.
        Stream.generate(() -> queue.poll())
            .filter(x -> x != null)
            .filter(x -> exitWhenQuitSignalReceived(x, writers))
            .filter(err::write)
            .filter(opp::write)
            .forEach(x -> System.err.format("? %s%n", x));
/*        
        Stream.generate(() -> Optional.ofNullable(queue.poll()))
            .filter(x -> x.isPresent())
            .map(x -> x.get())
            .map(String::toString)
            .filter(x -> exitWhenQuitSignalReceived(x, writers))
            .filter(err::write)
            .filter(opp::write)
            .forEach(x -> System.err.format("? %s%n", x));
*/
    }

    public boolean exitWhenQuitSignalReceived(String s, OutputWriter[] ws) {
        if ("Q".equals(s)) {
            System.out.print("Exiting...");
            for(OutputWriter w : ws) {
                w.close();
            }
            System.out.println("OK");
            System.exit(0);
        }
        return true;
    }


    public void readStdinInBackground() {
        readStreamInBackground(System.in);
    }

    public void readFileInBackground() {
        try
        {
            RandomAccessFile file = new RandomAccessFile(inputFilename, "r");
            file.seek(file.length()); // move to end of file.
            InputStream is = Channels.newInputStream(file.getChannel());
            readStreamInBackground(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readStreamInBackground(InputStream stream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        while (true) {
            try
            {
                for(String s = reader.readLine(); s != null; s = reader.readLine())
                {
                    queue.offer(s);
                }
                Thread.sleep(10);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public class OutputWriter {
        private int fileIndex = 0;
        private int count = 0;
        private String filenamePattern;
        private String prefix;
        private PrintWriter writer;

        public OutputWriter(String filenamePattern, String prefix) {
            this.filenamePattern = filenamePattern;
            this.prefix = prefix;
        }

        public OutputWriter build() throws IOException
        {
            openNewWriter();
            return this;
        }

        private void openNewWriter() throws IOException {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
            writer = new PrintWriter(Files.newBufferedWriter(Paths.get(String.format(filenamePattern, fileIndex))));
        }

        public boolean write(String s) {
            try
            {
                if (s != null && s.startsWith(prefix))
                {
                    count++;
                    if (count > 20) {
                        count = 1;
                        fileIndex++;
                        openNewWriter();
                    }
                    writer.println(s.substring(prefix.length()));
                    return false;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return true;
        }

        public void close() {
            if (writer != null) {
                writer.close();
            }
        }
    }
}