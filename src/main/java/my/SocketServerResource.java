package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

public class SocketServerResource 
{
	// netcat client usage: echo Again | nc 127.0.0.1 8080
	public static void main(String[] args) {
		SocketServerResource.use(8080, (r,w) -> {
			System.out.println("read input.");
			String s = readLine(r);
			System.out.println("hello %s." + s);
			w.printf("hello %s", s);
			w.flush();
		});
	}
	public static String readLine(BufferedReader r) {
		try {
			return r.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
    public static void use(int port, BiConsumer<BufferedReader,PrintWriter> consumer) 
    { 
    	ExecutorService executor = Executors.newFixedThreadPool(4);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.printf("Server listening on port %s%n", port);
            while (true) {
            	Socket socket = serverSocket.accept();
            	executor.execute(() -> {
	                try (
	                	InputStream input = socket.getInputStream();
	                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	                    OutputStream output = socket.getOutputStream();
	                    PrintWriter writer = new PrintWriter(output, true);
	                ){
	                	System.out.println("new connection established.");
	                	consumer.accept(reader,writer);
	                	System.out.println("consumer accepted.");
	                	socket.close();
	                	System.out.println("socket closed.");
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
            	});
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
