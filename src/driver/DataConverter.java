package driver;

import objects.customer.Customer;
import objects.invoice.Invoice;
import objects.person.Person;
import objects.products.product.Product;
import objects.venue.Venue;

import org.json.JSONException;
import output.InvoiceSummary;

import java.io.IOException;

public class DataConverter {

    public static void main(String[] args) throws IOException, JSONException {
        Person person = new Person("data/input/Persons.dat");
        Customer customer = new Customer("data/input/Customers.dat");
        Venue venue = new Venue("data/input/Venues.dat");
        Product product = new Product("data/input/Products.dat");
        Invoice invoice = new Invoice("data/input/Invoices.dat");
        InvoiceSummary invoiceSummary = new InvoiceSummary(invoice.getFinalJSON());
        System.out.println(invoiceSummary.getFullReport());
    }
}
