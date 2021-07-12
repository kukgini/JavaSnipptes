package my.http;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
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

            setHeadersForJson(resp);
            invoke(method, payload, resp.getWriter());
        } catch (Throwable t) {
            t.printStackTrace();
            print(resp.getWriter(), new JsonError(t), JsonError.class);
            resp.setStatus(500);
        }
    }

    private void invoke(Method method, String payload, PrintWriter writer) throws InvocationTargetException, IllegalAccessException, IOException {
        Object param = null;
        Object result = null;
        if (method.getParameterCount() > 0) {
            param = map2obj(payload, method.getParameterTypes()[0]);
            result = method.invoke(this, param);
        } else {
            result = method.invoke(this);
        }
        if (result != null) {
            Class outputType = method.getReturnType();
            print(writer, result, outputType);
        }
    }

    private Method getMethod(String path) {
        if ("".equals(path)) { path = "home"; }
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

    private void print(PrintWriter writer, Object output, Class outputType) throws IOException {
        if (output == null) return;
        if (outputType.equals(String.class)) {
            writer.write((String) output);
        } else {
            gson.toJson(output, writer);
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
