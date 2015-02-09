package driver;

import objects.person.Person;
import org.json.JSONException;

import java.io.IOException;

public class DataConverter {

    public static void main(String[] args) throws IOException, JSONException {
        Person person = new Person("data/Persons.dat");
        System.out.println(person.getFinalJSONString());
    }
}
