package objects.products.procuct_list;

import objects.products.product.Product;
import objects.products.services.ParkingPass;
import objects.products.services.PersonalSeatLicense;
import objects.products.services.Refreshment;
import objects.products.tickets.GameTicket;
import objects.products.tickets.SeasonPass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ObjectUtil;

import java.util.ArrayList;
import java.util.Collections;

public class ProductList {

    private static final String PRODUCT_LIST_STRIING = "products";
    private static final String NUMBER_OF_UNITS = "quantity";
    private static final String DATE = "date";
    private static final String HOURS_VALID = "hoursValid";
    private JSONArray JSONArray;
    private static ArrayList<String> seats = new ArrayList<>();

    private static final ArrayList<Object> quantityFormat = new ArrayList<Object>() {
        {
            add(ProductList.NUMBER_OF_UNITS);
        }
    };

    private static final ArrayList<Object> personalSeatLicenseFormat = new ArrayList<Object>() {
        {
            add(ProductList.NUMBER_OF_UNITS);
            add(ProductList.seats);
        }
    };

    private static final ArrayList<Object> parkingPassFormat = new ArrayList<Object>() {
        {
            add(ProductList.DATE);
            add(ProductList.NUMBER_OF_UNITS);
            add(ProductList.HOURS_VALID);
        }
    };

    private static final ArrayList<Product> productFormat = new ArrayList<>();
    private ArrayList<JSONObject> products = new ArrayList<>();

    public ProductList(String products) throws JSONException {
        this.products = this.parseProducts(products);
        this.JSONArray = new JSONArray();
    }

    private ArrayList<JSONObject> parseProducts(String input) throws JSONException {
        String values[] = ObjectUtil.splitToTokens(input);
        JSONObject product = ObjectUtil.getProductDataFromCode(values[0]);
        String productType = product.getString("productType");
        ArrayList<Object> productFormat = null;
        switch (productType) {
            case Product.PARKING_PASS_SHORT:
                productFormat = parkingPassFormat;
                break;
            case Product.PERSONAL_SEAT_LICENCE_SHORT:
                productFormat = personalSeatLicenseFormat;
                break;
        }
        if(productFormat == null) {
            productFormat = quantityFormat;
        }
        for(int counter = 0; counter < values.length; counter ++) {
            String value = values[counter];
            JSONObject jsonObject = new JSONObject();
            String[] productTokenArray =
        }
        ArrayList<String> products = new ArrayList<>();
        JSONObject product = ObjectUtil.getProductDataFromCode(values[0]);
        Collections.addAll(products, values);
        return products;
    }

    public static ArrayList<Product> getProductFormat() {
        return productFormat;
    }
}
