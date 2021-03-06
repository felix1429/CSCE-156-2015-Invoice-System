package objects.products.services;

import objects.venue.Venue;
import utils.ObjectUtil;

import org.json.*;
import java.util.ArrayList;

public class ParkingPass {

    public JSONObject parkingPass = new JSONObject();
    public JSONObject productsJsonObject = new JSONObject();
    public static  ArrayList<Object> venue = Venue.getVenueFormat();
    public static final String HOURLY_FEE_STRING = "hourlyFee";
    private static final ArrayList<Object> parkingPassFormat = new ArrayList<Object>() {
        {
            add(venue);
            add(ParkingPass.HOURLY_FEE_STRING);
        }
    };

    public ParkingPass(String[] parkingPassArray, JSONObject productsJsonObject) throws JSONException {
        this.productsJsonObject = productsJsonObject;
        this.parkingPass = this.parseParkingPass(parkingPassArray);
    }

    private JSONObject parseParkingPass(String[] input) throws JSONException {
        for(int i = 0; i < input.length - 2; i++) {
            Object object = parkingPassFormat.get(i);
            String token = input[i + 2];
            if(object instanceof ArrayList) {
                this.productsJsonObject.put(Venue.VENUE_STRING, ObjectUtil.getVenueDataFromCode(token));
            } else {
                this.productsJsonObject.put(object.toString(), token);
            }
        }
        return this.productsJsonObject;
    }
}
