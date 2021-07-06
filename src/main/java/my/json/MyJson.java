package my.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class MyJson {

    public static void main(String[] args) {
        Gson gson = new Gson();

        String json = "{\"name\":\"noname\"}";
        Person person = gson.fromJson(json, Person.class);

        System.out.println(person);
        System.out.println(gson.toJson(person));
    }
}
