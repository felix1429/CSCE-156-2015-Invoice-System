package utils;

import org.json.*;

public class InvoiceUtil {

    public static String generateString(String toRepeat, int number) {
        return new String(new char[number]).replace("\0", toRepeat);
    }

    public static String getNestedJSON(JSONObject object, String ... keys) throws JSONException {
        int length = keys.length;
        String value = "";
        for(String key : keys) {
            if(key.equals(keys[length - 1])) {
                value = object.getString(key);
            } else {
                object = object.getJSONObject(key);
            }
        }
        return value;
    }

    public static String getFullCustomerType(String letter) {
        String type = "";
        switch (letter) {
            case "M":
                type = "Member";
                break;
            case "A":
                type = "Agent";
                break;
            case "N":
                type = "Non-Member";
                break;
        }
        return type;
    }
}
