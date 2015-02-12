package utils;

import org.json.JSONObject;
import java.util.HashMap;

public class ObjectUtil {

    private static HashMap<String, JSONObject> personCodeMap = new HashMap<>();
    private static HashMap<String, JSONObject> venueCodeMap = new HashMap<>();
    private static HashMap<String, JSONObject> ticketCodeMap = new HashMap<>();

    //convert an element line of a data file into an array
    public static String[] splitToTokens(String list) {
        return list.split(",");
    }

    //split a line of a data file into an array
    public static String[] parseLineToTokens(String line) {
        return line.split(";");
    }

    public static JSONObject getPersonDataFromCode(String code) {
        return personCodeMap.get(code);
    }

    public static void addToPersonCodeMap(String key, JSONObject value) {
        personCodeMap.put(key, value);
    }

    public static JSONObject getVenueDataFromCode(String code) {
        return venueCodeMap.get(code);
    }

    public static void addToVenueCodeMap(String key, JSONObject value) {
        venueCodeMap.put(key, value);
    }

    public static void addToTicketCodeMap(String key, JSONObject value) {
        ticketCodeMap.put(key, value);
    }

    public static JSONObject getTicketDataFromCode(String code) {
        return ticketCodeMap.get(code);
    }
}
