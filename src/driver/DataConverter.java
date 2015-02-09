package driver;

import objects.customer.Customer;
import objects.person.Person;
import org.json.JSONException;

import java.io.IOException;

public class DataConverter {

    public static void main(String[] args) throws IOException, JSONException {
        Person person = new Person("data/input/Persons.dat");
        System.out.println(person.getJSONShell());
        Customer customer = new Customer("data/input/Customers.dat");
        System.out.println(customer.getJSONShell());
    }
}
