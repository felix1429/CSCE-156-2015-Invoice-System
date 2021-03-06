package objects.products.tickets;

import utils.ObjectUtil;

import org.json.*;
import java.util.ArrayList;

public class SeasonPass {

    public JSONObject seasonPass = new JSONObject();
    public JSONObject productsJsonObject = new JSONObject();
    public static final String TEAM = "team";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String PRICE = "price";
    private static final ArrayList<String> seasonPassFormat = new ArrayList<String>() {
        {
            add(SeasonPass.TEAM);
            add(SeasonPass.START_DATE);
            add(SeasonPass.END_DATE);
            add(SeasonPass.PRICE);
        }
    };

    public SeasonPass(String[] seasonPassArray, JSONObject productsJsonObject) throws JSONException {
        this.productsJsonObject = productsJsonObject;
        this.seasonPass = this.parseSeasonPass(seasonPassArray);
    }

    private JSONObject parseSeasonPass(String[] input) throws JSONException {
        for(int i = 0; i < input.length - 2; i++) {
            Object object = seasonPassFormat.get(i);
            String token = input[i + 2];
            this.productsJsonObject.put(object.toString(), token);
        }
        ObjectUtil.addToTicketCodeMap(input[0], this.productsJsonObject);
        return this.productsJsonObject;
    }
}
