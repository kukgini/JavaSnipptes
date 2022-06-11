package my.http;

import com.google.gson.Gson;
import my.dto.Person;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class MyClient {
    private static Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        Charset charset = Charset.forName( "UTF-8" );
        CharsetEncoder encoder = charset.newEncoder( );

        HttpClient client = new HttpClient();
        client.start();

        Person person = new Person();
        person.setName("Alice");
        String payload = gson.toJson(person);
        ByteBuffer buffer = encoder.encode(CharBuffer.wrap(payload));

        {
            ContentResponse response = client
                    .newRequest("http://127.0.0.1:7070/hello")
                    .method(HttpMethod.POST).content(new StringContentProvider(payload))
                    .header("x-api-key", "--api-token-here--")
                    .send();
            System.out.printf("/hello => %s%n", response.getContentAsString());
        }
        {
            ContentResponse response = client
                    .newRequest("http://127.0.0.1:7070/echo")
                    .method(HttpMethod.POST).content(new StringContentProvider(payload))
                    .header("x-api-key", "--api-token-here--")
                    .send();
            System.out.printf("/echo => %s%n", response.getContentAsString());
        }
        {
            ContentResponse response = client
                    .newRequest("http://127.0.0.1:7070/")
                    .method(HttpMethod.GET)
                    .send();
            System.out.printf("/ => %n", response.getContentAsString());
        }
    }
}
