package driver;

import objects.customer.Customer;
import objects.invoice.Invoice;
import objects.person.Person;
import objects.products.product.Product;
import objects.venue.Venue;

import org.json.JSONException;
import java.io.IOException;

public class DataConverter {

    public static void main(String[] args) throws IOException, JSONException {
        Person person = new Person("data/input/Persons.dat");
        System.out.println(person.getJSONShell());
        Customer customer = new Customer("data/input/Customers.dat");
        System.out.println(customer.getJSONShell());
        Venue venue = new Venue("data/input/Venues.dat");
        System.out.println(venue.getJSONShell());
        Product product = new Product("data/input/Products.dat");
        System.out.println(product.getJSONShell());
        Invoice invoice = new Invoice("data/input/Invoices.dat");
        System.out.println(invoice.getJSONShell());
    }
}
