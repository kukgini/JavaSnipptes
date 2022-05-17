package my.http;

import com.google.gson.Gson;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class MyGatewayActions {

    Charset charset = Charset.forName( "UTF-8" );
    CharsetEncoder encoder = charset.newEncoder( );

    public Person hello(Person input) throws Exception {
        Gson gson = new Gson();
        String payload = gson.toJson(input);
        ByteBuffer buffer = encoder.encode(CharBuffer.wrap(payload));

        HttpClient client = new HttpClient();
        client.start();
        ContentResponse response = client
                .newRequest("http://127.0.0.1:8080/hello")
                .method(HttpMethod.POST).content(new StringContentProvider(payload))
                .send();
        return gson.fromJson(response.getContentAsString(), Person.class);
    }

    public String echo(String input) throws Exception {
        Gson gson = new Gson();
        String payload = gson.toJson(input);
        ByteBuffer buffer = encoder.encode(CharBuffer.wrap(input));

        HttpClient client = new HttpClient();
        client.start();
        ContentResponse response = client
                .newRequest("http://127.0.0.1:8080/echo")
                .method(HttpMethod.POST).content(new StringContentProvider(payload))
                .send();
        return response.getContentAsString();
    }

    public String home() {
        return "Welcome!";
    }
}
