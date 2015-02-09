package objects.products.services;


import objects.venue.Venue;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ObjectUtil;

import java.util.ArrayList;

public class ParkingPass {

    public JSONObject parkingPass = new JSONObject();
    public static  ArrayList<Object> venue = Venue.getVenueFormat();
    public static final String HOURLY_FEE_STRING = "hourlyFee";
    private static final ArrayList<Object> parkingPassFormat = new ArrayList<Object>() {
        {
            add(venue);
            add(ParkingPass.HOURLY_FEE_STRING);
        }
    };

    public ParkingPass(String[] parkingPassArray) throws JSONException {
        this.parkingPass = this.parseParkingPass(parkingPassArray);
    }

    private JSONObject parseParkingPass(String[] input) throws JSONException {
        JSONObject obj = new JSONObject();
        String token;
        for(int i = 0; i < input.length; i ++) {
            Object object = parkingPassFormat.get(i);
            token = input[i];
            if(object instanceof ArrayList) {
                obj.put(Venue.VENUE_STRING, ObjectUtil.getVenueDataFromCode(token));
            } else {
                obj.put(parkingPassFormat.get(i).toString(), token);
            }
        }
        return obj;
    }
}
