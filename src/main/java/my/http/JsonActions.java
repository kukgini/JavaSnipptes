package my.http;

public class JsonActions extends JsonServlet {

    private static final long serialVersionUID = 1L;

    public Person hello(Person input) {
        Person output = new Person();
        output.setName(((Person)input).getName());
        return output;
    }

    public String echo(String input) {
        return input;
    }

    public String home() {
        return "Welcome !";
    }
}
