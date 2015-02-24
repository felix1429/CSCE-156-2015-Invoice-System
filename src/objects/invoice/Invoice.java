package objects.invoice;

import objects.base.BaseObject;
import objects.customer.Customer;
import objects.person.Person;
import objects.products.procuct_list.ProductList;
import utils.ObjectUtil;

import org.json.*;
import java.io.IOException;
import java.util.ArrayList;

public class Invoice extends BaseObject{

    private static final String JSON_NAME_INVOICES = "invoices";
    private static final String INVOICE_CODE_STRING = "invoiceCode";
    private static final String INVOICE_DATE_STRING = "invoiceDate";
    private static final String CUSTOMER_STRING = "customer";
    private static final String PERSON_STRING = "salesperson";
    private static  ArrayList<Object> customer = Customer.getCustomerFormat();
    private static  ArrayList<Object> salesperson = Person.getPersonFormat();
    private static  JSONArray products = new JSONArray();
    private static  ArrayList<Object> invoiceFormat = new ArrayList<Object>() {
        {
            add(Invoice.INVOICE_CODE_STRING);
            add(customer);
            add(salesperson);
            add(Invoice.INVOICE_DATE_STRING);
            add(products);
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
        for(int counter = 1; counter <= this.numberOfRecords; counter ++) {
            lineTokenArray = fileArray.get(counter);
            JSONObject jsonObject = new JSONObject();
            for(int count = 0; count < lineTokenArray.length; count++) {
                Object object = invoiceFormat.get(count);
                value = lineTokenArray[count];
                if(!(object instanceof ArrayList) && object != products) {
                    jsonObject.put(object.toString(), value);
                } else {
                    if(object == Customer.getCustomerFormat()) {
                        jsonObject.put(Invoice.CUSTOMER_STRING, ObjectUtil.getCustomerDataFromCode(value));
                    } else if(object == Person.getPersonFormat()) {
                        jsonObject.put(Invoice.PERSON_STRING, ObjectUtil.getPersonDataFromCode(value));
                    } else if(object == products) {
                        ProductList pl = new ProductList(value);
                        jsonObject.put(ProductList.PRODUCT_LIST_STRING, pl.getProducts());
                    }
                }
            }
            JSONArrayList.put(jsonObject);
        }
        return JSONArrayList;
    }
}
