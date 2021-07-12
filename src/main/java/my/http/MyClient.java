package my.http;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;

public class MyClient {
    public static void main(String[] args) throws Exception {
        HttpClient httpClient = new HttpClient();
        httpClient.start();
        ContentResponse response = httpClient
            .newRequest("http://127.0.0.1:8080/hello")
            .method(HttpMethod.GET)
            .header("x-api-key", "abcd")
            .send();
        System.out.println(response.getContentAsString());
        response = httpClient
            .newRequest("http://127.0.0.1:8080/")
            .method(HttpMethod.GET)
            .send();
        System.out.println(response.getContentAsString());
    }
}
