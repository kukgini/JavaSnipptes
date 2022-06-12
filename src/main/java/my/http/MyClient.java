package my.http;

import com.google.gson.Gson;
import my.dto.Person;
import my.helper.ExecutorMonitor;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.InputStreamResponseListener;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

public class MyClient {
    private static Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        Charset charset = Charset.forName( "UTF-8" );
        CharsetEncoder encoder = charset.newEncoder( );

        HttpClient client = new HttpClient();
        client.start();

        ExecutorService threadpool = Executors.newCachedThreadPool();
        ExecutorMonitor threadmon = new ExecutorMonitor();

        threadmon.add(threadpool.submit(() -> callHelloService(client)));
        threadmon.add(threadpool.submit(() -> callEchoService(client)));
        threadmon.add(threadpool.submit(() -> callHomeService(client)));
        threadmon.allGone().get();

        threadpool.shutdown();
        client.stop();
    }

    private static void callHomeService(HttpClient client) {
        try {
            ContentResponse response = client
                .newRequest("http://127.0.0.1:7070/")
                .method(HttpMethod.GET)
                .send();
            System.out.printf("/ => %n", response.getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void callEchoService(HttpClient client) {
        try {
            ContentResponse response = client
                .newRequest("http://127.0.0.1:7070/echo")
                .method(HttpMethod.POST).content(new StringContentProvider("yahoo~"))
                .header("x-api-key", "--api-token-here--")
                .send();
            System.out.printf("/echo => %s%n", response.getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void callHelloService(HttpClient client) {
        try {
            Person person = new Person();
            person.setName("Alice");
            String payload = gson.toJson(person);
            ContentResponse response = client
                .newRequest("http://127.0.0.1:7070/hello")
                .method(HttpMethod.POST).content(new StringContentProvider(payload))
                .header("x-api-key", "--api-token-here--")
                .send();
            System.out.printf("/hello => %s%n", response.getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
