package my.json;

import com.google.gson.Gson;
import my.dto.Person;

import java.util.HashMap;
import java.util.Map;

public class MyJson {

    public static void main(String[] args) {
        Gson gson = new Gson();
        //object2json(gson);
        map2json(gson);
    }

    private static void object2json(Gson gson) {
        String json = "{\"name\":\"alice\"}";
        Person person = gson.fromJson(json, Person.class);
        person.setAddress("home", "My Home is ...");
        System.out.printf("name=%s%n", person.getName());
        System.out.printf("%s%n", gson.toJson(person));
    }

    private static void map2json(Gson gson) {
        Map<String, Object> root = new HashMap<>();
        root.put("rootKey", "rootValue");
        Map<String, String> sub1 = new HashMap<>();
        sub1.put("subKey1", "subVal1");
        root.put("sub1", sub1);
        System.out.println(gson.toJson(root).toString());
    }
}
