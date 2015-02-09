package objects.products.tickets;


import org.json.JSONObject;

import java.util.ArrayList;

public class SeasonPass {

    public JSONObject seasonPass = new JSONObject();
    public JSONObject productsJsonObject;
    public static final String TEAM = "team";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String PRICE = "price";
    private static final ArrayList<String> seasonPassFormat = new ArrayList<String>() {
        {

        }
    }
}
