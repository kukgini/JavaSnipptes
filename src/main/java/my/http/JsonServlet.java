package my.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

public class JsonServlet extends HttpServlet {

    private Gson gson = new Gson();

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("api called");
        System.err.printf("x-api-key: %s%n", req.getHeader("x-api-key"));
        try {
            String path = req.getRequestURI();
            if (path.startsWith("/")) { path = path.substring(1); }
            System.err.printf("path=%s%n", path);
            String content = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            Method[] methods = this.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals(path)) {
                    Class inputType = method.getParameterTypes()[0];
                    Object input = null;
                    if (inputType.equals(String.class)) {
                        input = content;
                    } else{
                        input = gson.fromJson(content, inputType);
                    }
                    Object output = method.invoke(this, input);

                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    resp.setStatus(200);

                    Class outputType = method.getReturnType();
                    if (outputType.equals(String.class)) {
                        resp.getWriter().write((String)output);
                    } else {
                        gson.toJson(output, resp.getWriter());
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
            resp.sendError(500);
        }
    }
}
