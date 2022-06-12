package my.http;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.stream.Collectors;

public class MyGateway extends HttpServlet {

    public static void main(String[] args) throws Exception {
        new MyGateway().start();
    }

    public void start() throws Exception {
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server();
        ServerConnector http = new ServerConnector(server);
        http.setHost("127.0.0.1");
        http.setPort(7070);

        server.addConnector(http);

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(this.getClass(), "/");
        server.setHandler(servletHandler);

        server.start();
        server.join();
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            authorizeApiKey(req);

            URI uri = new URI(req.getRequestURI());
            System.out.printf("uri=%s%n", uri);

            HttpClient client = new HttpClient();
            client.start();
            Request backendRequest = client.newRequest(String.format("http://127.0.0.1:8080%s", uri));
            for (String key : req.getParameterMap().keySet()) {
                String[] vals = req.getParameterValues(key);
                for (String val : vals) {
                    backendRequest.param(key,val);
                }
            }
            backendRequest.method(req.getMethod());
            backendRequest.content(new StringContentProvider(req.getReader().lines().collect(Collectors.joining(System.lineSeparator()))));
            ContentResponse backendResponse = backendRequest.send();

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            PrintWriter frontendWriter = resp.getWriter();
            frontendWriter.write(backendResponse.getContentAsString());

        } catch (Throwable t) {
            t.printStackTrace();
            resp.setStatus(500);
        }
    }

    private void authorizeApiKey(HttpServletRequest req) {
        System.err.printf("x-api-key: %s%n", req.getHeader("x-api-key"));
    }
}
