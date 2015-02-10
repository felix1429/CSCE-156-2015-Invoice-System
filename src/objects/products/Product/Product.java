package objects.products.product;

import objects.base.BaseObject;
import objects.products.services.ParkingPass;
import objects.products.services.PersonalSeatLicense;
import objects.products.services.Refreshment;
import objects.products.tickets.GameTicket;
import objects.products.tickets.SeasonPass;
import org.json.*;

import java.io.IOException;
import java.util.ArrayList;

public class Product extends BaseObject {

    private static final String PRODUCT_CODE_STRING = "code";
    public static final String PRODUCT_TYPE_STRING = "productType";
    private static final String JSON_NAME_PRODUCTS = "products";
    public static final String GAME_TICKET_SHORT = "TG";
    public static final String SEASON_PASS_SHORT = "TS";
    public static final String PARKING_PASS_SHORT = "SP";
    private static final String PERSONAL_SEAT_LICENCE_SHORT = "SL";
    private static final String REFRESHMENT_SHORT = "SR";
    private static final ArrayList<Object> productFormat = new ArrayList<Object>() {
        {
            add(Product.PRODUCT_CODE_STRING);
            add(Product.PRODUCT_TYPE_STRING);
        }
    };
    private static ArrayList<Object> product = productFormat;

    public Product(String filePath) throws IOException, JSONException {
        super(filePath);
        this.JSONname = Product.JSON_NAME_PRODUCTS;
        this.finalJSON = this.convertToJSON(fileArray);
        this.finalJSONString = this.finalJSON.toString(3);
        this.outerJSONObject = createJSONShell(this.JSONname, this.finalJSONString);
    }

    private JSONArray convertToJSON(ArrayList<String[]> fileArray) throws JSONException {
        for(int counter = 1; counter <= this.numberOfRecords; counter ++) {
            lineTokenArray = fileArray.get(counter);
            JSONObject jsonObject = new JSONObject();
            for (int count = 0; count < 2; count++) {
                Object object = product.get(count);
                value = lineTokenArray[count];
                if(count < 2) {
                    jsonObject.put(object.toString(), value);
                }
                if(object.toString().equals(Product.PARKING_PASS_SHORT)) {
                    new ParkingPass(lineTokenArray, jsonObject);
                } else if(object.toString().equals(Product.GAME_TICKET_SHORT)) {
                    new GameTicket(lineTokenArray, jsonObject);
                } else if(object.toString().equals(Product.SEASON_PASS_SHORT)) {
                    new SeasonPass(lineTokenArray, jsonObject);
                } else if(object.toString().equals(Product.PERSONAL_SEAT_LICENCE_SHORT)) {
                    new PersonalSeatLicense(lineTokenArray, jsonObject);
                } else if(object.toString().equals(Product.REFRESHMENT_SHORT)) {
                    new Refreshment(lineTokenArray, jsonObject);
                }
            }
            JSONArrayList.put(jsonObject);
        }
        return JSONArrayList;
    }
}
