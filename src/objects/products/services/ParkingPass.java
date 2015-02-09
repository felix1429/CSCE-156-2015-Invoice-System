package objects.products.services;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParkingPass {

    public JSONObject parkingPass = new JSONObject();
    public static final String VENUE_CODE_STRING = "venueCode";
    public static final String HOURLY_FEE_STRING = "hourlyFee";
    private static final ArrayList<String> parkingPassFormat = new ArrayList<String>() {
        {
            add(ParkingPass.VENUE_CODE_STRING);
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
            token = input[i];
            obj.put(parkingPassFormat.get(i), token);
        }
        return obj;
    }
}
