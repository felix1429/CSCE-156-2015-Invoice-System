package objects.products.product;

import objects.base.BaseObject;
import objects.products.services.*;
import objects.products.tickets.*;

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
        this.finalJSONString = this.finalJSON.toString(2);
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
                switch (value) {
                    case Product.PARKING_PASS_SHORT:
                        ParkingPass parkingPass = new ParkingPass(lineTokenArray, jsonObject);
                        jsonObject = parkingPass.parkingPass;
                        break;
                    case Product.GAME_TICKET_SHORT:
                        GameTicket gameTicket = new GameTicket(lineTokenArray, jsonObject);
                        jsonObject = gameTicket.gameTicket;
                        break;
                    case Product.SEASON_PASS_SHORT:
                        SeasonPass seasonPass = new SeasonPass(lineTokenArray, jsonObject);
                        jsonObject = seasonPass.seasonPass;
                        break;
                    case Product.PERSONAL_SEAT_LICENCE_SHORT:
                        PersonalSeatLicense psl = new PersonalSeatLicense(lineTokenArray, jsonObject);
                        jsonObject = psl.psl;
                        break;
                    case Product.REFRESHMENT_SHORT:
                        Refreshment refreshment = new Refreshment(lineTokenArray, jsonObject);
                        jsonObject = refreshment.refreshment;
                        break;
                }
            }
            JSONArrayList.put(jsonObject);
        }
        return JSONArrayList;
    }
}
