package objects.products.product;

import objects.base.BaseObject;
import objects.products.services.ParkingPass;
import org.json.*;
import utils.ObjectUtil;

import java.io.IOException;
import java.util.ArrayList;

public class Product extends BaseObject {

    private static final String PRODUCT_CODE_STRING = "code";
    private static final String PRODUCT_TYPE_STRING = "productType";
    private static final String JSON_NAME_PRODUCTS = "products";
    private static final String GAME_TICKET_SHORT = "TG";
    private static final String SEASON_PASS_SHORT = "TS";
    private static final String PARKING_PASS_SHORT = "SP";
    private static final String PERSONAL_SEAT_LICENCE_SHORT = "SL";
    private static final String REFRESHMENT_SHORT = "SR";
    private static final ArrayList<Object> productFormat = new ArrayList<Object>() {
        {
            add(Product.PRODUCT_CODE_STRING);
            add(Product.PRODUCT_TYPE_STRING);
        }
    };
    private static ArrayList<Object> product = productFormat;
    private String[] productTokens;

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
            productTokens = ObjectUtil.sendProductDataToArray(lineTokenArray);
            JSONObject jsonObject = new JSONObject();
            for (int count = 0; count < 2; count++) {
                Object object = product.get(count);
                value = lineTokenArray[count];
                if(count == 0) {
                    jsonObject.put(object.toString(), value);
                } else if(object.toString().equals(Product.PARKING_PASS_SHORT)) {
                    ParkingPass parkingPass = new ParkingPass(productTokens);

                }
            }
        }
    }
}
