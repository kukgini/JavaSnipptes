package my.http;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

public class JsonServlet extends HttpServlet {

    private Gson gson = new Gson();

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            validateAuthorized(req);

            String path = getPath(req);
            String payload = getPayload(req);
            Method method = getMethod(path);

            Class inputType = method.getParameterTypes()[0];
            Object output = method.invoke(this, map2obj(payload, inputType));

            setHeadersForJson(resp);
            resp.setStatus(200);

            Class outputType = method.getReturnType();
            map2json(resp, output, outputType);
        } catch (Throwable t) {
            t.printStackTrace();
            resp.sendError(500);
        }
    }

    private Method getMethod(String path) {
        Method[] methods = this.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(path)) {
                return method;
            }
        }
        return null;
    }

    private String getPayload(HttpServletRequest req) throws IOException {
        return req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    }
    
    private String getPath(HttpServletRequest req) {
        String path = req.getRequestURI();
        System.err.printf("path=%s%n", path);
        if (path.startsWith("/")) { path = path.substring(1); }
        return path;
    }

    private void validateAuthorized(HttpServletRequest req) {
        System.err.printf("x-api-key: %s%n", req.getHeader("x-api-key"));
    }

    private void setHeadersForJson(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    private void map2json(HttpServletResponse resp, Object output, Class outputType) throws IOException {
        if (outputType.equals(String.class)) {
            resp.getWriter().write((String) output);
        } else {
            gson.toJson(output, resp.getWriter());
        }
    }

    private Object map2obj(String content, Class inputType) {
        Object input = null;
        if (inputType.equals(String.class)) {
            input = content;
        } else{
            input = gson.fromJson(content, inputType);
        }
        return input;
    }
}
