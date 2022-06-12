package my.json;

import com.google.gson.Gson;
import my.dto.Person;

public class MyJson {

    public static void main(String[] args) {
        Gson gson = new Gson();

        String json = "{\"name\":\"alice\"}";
        Person person = gson.fromJson(json, Person.class);

        System.out.printf("name=%s%n", person.getName());
        System.out.printf("%s%n", gson.toJson(person));
    }
}
