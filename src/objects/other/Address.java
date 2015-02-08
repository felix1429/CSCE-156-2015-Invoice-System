package objects.other;


import objects.BaseObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Address extends BaseObject{

    private static final ArrayList<String> addressFormat = new ArrayList<String>() {
        {
            add("street");
            add("city");
            add("state");
            add("zip");
            add("country");
        }
    };

    public Address(String addressStr) {
        JSONObject address = this.parseAddress(addressStr);
    }

    private JSONObject parseAddress(String input) {
        String values[] = splitToTokens(input);
        JSONObject obj = new JSONObject();
        String output;
        for (int i = 0; i < values.length; i++) {
            output = values[i];
            if (!addressFormat.get(i).equals("street")) {
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
