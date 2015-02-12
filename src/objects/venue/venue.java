package objects.venue;

import objects.base.BaseObject;
import objects.other.Address;
import utils.ObjectUtil;

import org.json.*;
import java.io.IOException;
import java.util.ArrayList;

public class Venue extends BaseObject {

    private String venueCode;
    public static final String VENUE_STRING = "venue";
    private static final String VENUE_CODE_STRING = "venueCode";
    private static final String JSON_NAME_VENUES = "venues";
    private static final String VENUE_NAME = "name";
    private static final String VENUE_CAPACITY = "capacity";
    private static ArrayList<String> address = Address.getAddressFormat();
    private static final ArrayList<Object> venueFormat = new ArrayList<Object>() {
        {
            add(Venue.VENUE_CODE_STRING);
            add(Venue.VENUE_NAME);
            add(address);
            add(VENUE_CAPACITY);
        }
    };
    private static ArrayList<Object> venue = venueFormat;

    public Venue(String filePath) throws IOException, JSONException {
        super(filePath);
        this.JSONname = Venue.JSON_NAME_VENUES;
        this.finalJSON = this.convertToJSON(fileArray);
        this.finalJSONString = this.finalJSON.toString(2);
        this.outerJSONObject = createJSONShell(this.JSONname, this.finalJSONString);
    }

    private JSONArray convertToJSON(ArrayList<String[]> fileArray) throws JSONException {
        for (int counter = 1; counter <= this.numberOfRecords; counter++) {
            lineTokenArray = fileArray.get(counter);
            JSONObject jsonObject = new JSONObject();
            for (int count = 0; count < lineTokenArray.length; count++) {
                Object object = venue.get(count);
                value = lineTokenArray[count];
                if(object instanceof ArrayList) {
                    Address address = new Address(value);
                    jsonObject.put(Address.ADDRESS_STRING, address.address);
                } else {
                    if(object.toString().equals(Venue.VENUE_CODE_STRING)) {
                        venueCode = value;
                    }
                    jsonObject.put(object.toString(), value);
                }
            }
            ObjectUtil.addToVenueCodeMap(venueCode, jsonObject);
            JSONArrayList.put(jsonObject);
        }
        return JSONArrayList;
    }

    public static ArrayList<Object> getVenueFormat() {
        return venueFormat;
    }
}
