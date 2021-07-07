package my.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class HelloServlet extends APIServlet {

    private static final long serialVersionUID = 1L;

    public Person execute(Person input) {
        Person output = new Person();
        output.setName(((Person)input).getName());
        return output;
    }
}
