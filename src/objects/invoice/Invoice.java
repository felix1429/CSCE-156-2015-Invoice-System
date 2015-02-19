package objects.invoice;

import objects.base.BaseObject;
import objects.customer.Customer;
import objects.person.Person;
import objects.products.product.Product;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class Invoice extends BaseObject{

    private static final String JSON_NAME_INVOICES = "invoices";
    private static final String INVOICE_CODE_STRING = "invoiceCode";
    private static final String INVOICE_DATE_STRING = "invoiceDate";
    private static final ArrayList<Object> customer = Customer.getCustomerFormat();
    private static final ArrayList<Object> salesperson = Person.getPersonFormat();
    private static final ArrayList<Object> invoiceFormat = new ArrayList<Object>() {
        {
            add(Invoice.INVOICE_CODE_STRING);
            add(customer);
            add(salesperson);
            add(Invoice.INVOICE_DATE_STRING);
            //TODO: product list
        }
    };

    public Invoice(String filePath) throws IOException, JSONException {
        super(filePath);
        this.JSONname = Invoice.JSON_NAME_INVOICES;
        this.finalJSON = this.convertToJSON(fileArray);
        this.finalJSONString = this.finalJSON.toString(2);
        this.outerJSONObject = createJSONShell(this.JSONname, this.finalJSONString);
    }

    private JSONArray convertToJSON(ArrayList<String[]> fileArray) throws JSONException {

    }
}
