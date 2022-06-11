package my.json;

import com.google.gson.Gson;
import my.dto.Person;

public class MyJson {

    public static void main(String[] args) {
        Gson gson = new Gson();

        String json = "{\"name\":\"noname\"}";
        Person person = gson.fromJson(json, Person.class);

        System.out.println(person);
        System.out.println(gson.toJson(person));
    }
}
