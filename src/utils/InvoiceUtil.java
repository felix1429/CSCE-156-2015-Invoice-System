package utils;

import org.json.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public static int getDaysBetweenDates(String day1, String day2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long difference = 0;
        try {
            Date date1 = dateFormat.parse(day1);
            Date date2 = dateFormat.parse(day2);
            difference = date2.getTime() - date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int)TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS) + 1;
    }
}
