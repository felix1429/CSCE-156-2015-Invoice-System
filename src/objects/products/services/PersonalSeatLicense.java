package objects.products.services;

import org.json.JSONException;
import org.json.JSONObject;
import utils.ObjectUtil;

import java.util.ArrayList;

public class PersonalSeatLicense {

    public JSONObject psl = new JSONObject();
    public JSONObject productsJsonObject = new JSONObject();
    public static final String LICENSE_FEE_STRING = "licenseFee";
    public static final String TICKET = "ticket";
    private static final ArrayList<String> pslFormat = new ArrayList<String>() {
        {
            add(PersonalSeatLicense.TICKET);
            add(PersonalSeatLicense.LICENSE_FEE_STRING);
        }
    };

    public PersonalSeatLicense(String[] pslArray, JSONObject productsJsonObject) throws JSONException {
        this.productsJsonObject = productsJsonObject;
        this.psl = this.parsePsl(pslArray);
    }

    private JSONObject parsePsl(String[] input) throws JSONException {
        for(int i = 0; i < input.length - 2; i++) {
            Object object = pslFormat.get(i);
            String token = input[i + 2];
            if(object.toString().equals(PersonalSeatLicense.TICKET)) {
                this.productsJsonObject.put(object.toString(), ObjectUtil.getTicketDataFromCode(token));
            } else {
                this.productsJsonObject.put(object.toString(), token);
            }
        }
        return this.productsJsonObject;
    }
}
