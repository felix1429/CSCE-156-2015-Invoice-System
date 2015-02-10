package objects.products.services;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Refreshment {

    public JSONObject refreshment = new JSONObject();
    public JSONObject productsJsonObject;
    public static final String NAME = "name";
    public static final String COST = "cost";
    private static final ArrayList<String> refreshmentFormat = new ArrayList<String>() {
        {
            add(Refreshment.NAME);
            add(Refreshment.COST);
        }
    };

    public Refreshment(String[] refreshmentArray, JSONObject productsJsonObject) throws JSONException {
        this.refreshment = this.parseRefreshment(refreshmentArray);
        this.productsJsonObject = productsJsonObject;
    }

    private JSONObject parseRefreshment(String[] input) throws JSONException {
        for(int i = 0; i < input.length - 2; i++) {
            Object object = refreshmentFormat.get(i);
            String token = input[i + 2];
            this.productsJsonObject.put(object.toString(), token);
        }
        return this.productsJsonObject;
    }
}
