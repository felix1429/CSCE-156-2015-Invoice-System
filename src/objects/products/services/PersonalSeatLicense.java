package objects.products.services;


import objects.venue.Venue;
import org.json.JSONObject;

import java.util.ArrayList;

public class PersonalSeatLicense {

    public JSONObject psl = new JSONObject();
    public JSONObject productsJsonObject;
    public static ArrayList<Object> venue = Venue.getVenueFormat();
    public static final String LICENSE_FEE_STRING = "licenseFee";
    private static final ArrayList<Object> pslFormat = new ArrayList<Object>() {
        {
            add(venue);
            add(ParkingPass.HOURLY_FEE_STRING);
        }
    };
}
