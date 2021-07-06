package my.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleSocketServer 
{
    public static void main(String[] args)
    {
        SimpleSocketServer server = new SimpleSocketServer(2003);
        server.start();
    }  
    
    private final int port;
    private boolean running;
    private final ExecutorService threadPool;
    private Thread serverThread;
    
    public SimpleSocketServer(int port)
    {
        this.port = port;
        threadPool = Executors.newFixedThreadPool(10,new ThreadFactory()
        {
            private final AtomicInteger instanceCount = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r)
            {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                t.setName("HANDLER_" + instanceCount.getAndIncrement());
                return t;
            }
        });        
    }
    
    public void start()
    {
        running = true;
        System.out.println("------------- Starting Server Up -------------");
        serverThread = new Thread(() ->
        {
            try
            {
                ServerSocket server = new ServerSocket(port);
                while ( running)
                {
                    final Socket client = server.accept();
                    threadPool.submit(() ->
                    {
                        try
                        {
                            try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream())))
                            {
                                String line;
                                while((line = in.readLine()) != null)
                                {
                                    System.out.println(Thread.currentThread().getName() + ": " + line);
                                }
                            }
                            client.close();
                        }
                        catch (IOException ex)
                        {
                            Logger.getLogger(SimpleSocketServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }
            }
            catch (IOException ex)
            {
                Logger.getLogger(SimpleSocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        serverThread.setName("LISTENER");
        serverThread.start();
    }
    
    public void stop()
    {
        running = false;
        if ( serverThread != null)
        {
            serverThread.interrupt();
        }
        threadPool.shutdown();
        serverThread = null;
        
    }
  
}
