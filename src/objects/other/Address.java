package objects.other;

import utils.ObjectUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Address {

    public JSONObject address = new JSONObject();
    public static final String ADDRESS_STRING = "address";
    public static final String CITY_STRING = "city";
    public static final String STREET_STRING = "street";
    public static final String STATE_STRING = "state";
    public static final String ZIP_STRING = "zip";
    public static final String COUNTRY_STRING = "country";
    private static final ArrayList<String> addressFormat = new ArrayList<String>() {
        {
            add(Address.STREET_STRING);
            add(Address.CITY_STRING);
            add(Address.STATE_STRING);
            add(Address.ZIP_STRING);
            add(Address.COUNTRY_STRING);
        }
    };

    public Address(String addressStr) {
        this.address = this.parseAddress(addressStr);
    }

    private JSONObject parseAddress(String input) {
        String values[] = ObjectUtil.splitToTokens(input);
        JSONObject obj = new JSONObject();
        String output;
        for (int i = 0; i < values.length; i++) {
            output = values[i];
            if (!addressFormat.get(i).equals(Address.STREET_STRING)) {
                output = output.trim();
            }
            try {
                obj.put(addressFormat.get(i), output);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static ArrayList<String> getAddressFormat() {
        return addressFormat;
    }
}
