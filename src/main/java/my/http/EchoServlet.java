package my.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class EchoServlet extends APIServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String content = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        response.setStatus(200);
        response.getWriter().write(content);
    }
}
