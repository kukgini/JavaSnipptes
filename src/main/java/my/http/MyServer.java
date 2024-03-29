package my.http;

import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class MyServer {

    // https://java-design-patterns.com/patterns/
    // https://java-design-patterns.com/patterns/api-gateway/
    public static void main(String[] args) throws Exception {
        new MyServer().start();
    }

    public void start() throws Exception {
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server();
        ServerConnector http = new ServerConnector(server);
        http.setHost("127.0.0.1");
        http.setPort(8080);

        server.addConnector(http);

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(MyServerServlet.class, "/");
        server.setHandler(servletHandler);

        server.start();
        server.join();
    }
}
