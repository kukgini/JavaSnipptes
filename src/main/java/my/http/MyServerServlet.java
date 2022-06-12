package my.http;

import com.google.gson.Gson;
import my.dto.ServiceError;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Collectors;

public class MyServerServlet extends HttpServlet {

    private Gson gson = new Gson();
    private Object actions = new MyServerActions();

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String[]> params = req.getParameterMap();
            String payload = getPayload(req);
            Method method = getMethod(req, payload);

            setResponseHeaders(resp);
            invoke(method, payload, resp.getWriter());
        } catch (Throwable t) {
            t.printStackTrace();
            print(resp.getWriter(), new ServiceError(t), ServiceError.class);
            resp.setStatus(500);
        }
    }

    private void invoke(Method method, String payload, PrintWriter writer) throws InvocationTargetException, IllegalAccessException, IOException {
        Object param = null;
        Object result = null;
        if (method.getParameterCount() > 0) {
            param = str2obj(payload, method.getParameterTypes()[0]);
            result = method.invoke(actions, param);
        } else {
            result = method.invoke(actions);
        }
        if (result != null) {
            Class outputType = method.getReturnType();
            print(writer, result, outputType);
        }
    }

    private Method getMethod(HttpServletRequest req, String payload) {
        String uri = req.getRequestURI();
        System.out.printf("uri=%s%n", uri);

        if (uri.startsWith("/")) { uri = uri.substring(1); }
        if ("".equals(uri)) { uri = "home"; }

        int paramCount = req.getParameterMap().size();
        System.out.printf("param count=%s%n", paramCount);
        if (payload != null && payload.trim().length() > 0) {
            paramCount++;
        }
        System.out.printf("name=%s%n", uri);
        System.out.printf("payload=%s%n", payload);
        System.out.printf("param count=%s%n", paramCount);
        Method[] methods = actions.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(uri)) {
                if (method.getParameterCount() == paramCount){
                    return method;
                }
            }
        }
        throw new RuntimeException("action not found=" + uri);
    }

    private String getPayload(HttpServletRequest req) throws IOException {
        return req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    }

    private void setResponseHeaders(HttpServletResponse resp) {
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

    private Object str2obj(String content, Class type) {
        Object result = null;
        if ((content == null) || type.equals(String.class)) {
            result = content;
        } else{
            result = gson.fromJson(content, type);
        }
        return result;
    }
}
