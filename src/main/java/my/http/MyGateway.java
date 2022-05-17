package my.http;

import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class MyGateway {

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
        servletHandler.addServletWithMapping(MyGatewayServlet.class, "/");
        server.setHandler(servletHandler);

        server.start();
        server.join();
    }
}
