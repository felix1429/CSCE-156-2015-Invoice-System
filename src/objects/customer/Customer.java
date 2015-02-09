package objects.customer;


import objects.base.BaseObject;
import objects.other.Address;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ObjectUtil;

import java.io.IOException;
import java.util.ArrayList;

public class Customer extends BaseObject {

    private String customerCode;
    private static ArrayList<String> address = Address.getAddressFormat();
    private static final ArrayList<Object> customerFormat = new ArrayList<Object>() {
        {
            add("customerCode");
            add("type");
            add("primaryContact");
            add("name");
            add(address);
        }
    };
    private static ArrayList<Object> customer = customerFormat;

    public Customer(String filePath) throws IOException, JSONException {
        super(filePath);
        this.JSONname = "customers";
        this.finalJSON = this.convertToJSON(fileArray);
        this.finalJSONString = this.finalJSON.toString(2);
        this.outerJSONObject = createJSONShell(this.JSONname, this.finalJSONString);
    }

    private JSONArray convertToJSON(ArrayList<String[]> fileArray) throws JSONException {
        for(int counter = 1; counter <= this.numberOfRecords; counter ++) {
            lineTokenArray = fileArray.get(counter);
            JSONObject jsonObject = new JSONObject();
            for(int count = 0; count < lineTokenArray.length; count ++) {
                Object object = customer.get(count);
                value = lineTokenArray[count];
                if(count == 0) {
                    customerCode = value;
                }
                if(!(object instanceof ArrayList)) {
                    if(object.toString().equals("primaryContact")) {
                        jsonObject.put(object.toString(), ObjectUtil.getPersonDataFromCode(value));
                    } else {
                        jsonObject.put(object.toString(), value);
                    }
                } else {
                    Address address = new Address(value);
                    jsonObject.put(Address.ADDRESS_STRING, address.address);
                }
            }
            ObjectUtil.addToCustomerCodeMap(customerCode, jsonObject);
            JSONArrayList.put(jsonObject);
        }
        return JSONArrayList;
    }
}
