package objects.products.procuct_list;

import objects.products.product.Product;
import utils.ObjectUtil;

import org.json.*;
import java.util.ArrayList;

public class ProductList {

    private String[] seatList;
    private boolean arrayList = false;
    public static final String PRODUCT_LIST_STRING = "products";
    private static final String PRODUCT_STRING = "product";
    private static final String NUMBER_OF_UNITS = "quantity";
    private static final String DATE = "date";
    private static final String HOURS_VALID = "hoursValid";
    private static final String SEATS = "seats";
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
    private JSONArray products = new JSONArray();

    public ProductList(String products) throws JSONException {
        this.JSONArray = new JSONArray();
        this.products = this.parseProducts(products);
    }

    private JSONArray parseProducts(String input) throws JSONException {
        String values[] = ObjectUtil.splitToTokens(input);
        for(int count = 0; count < values.length; count ++) {
            JSONObject entry = new JSONObject();
            String[] productValues = ObjectUtil.parseProductDataToTokens(values[count]);
            JSONObject product = ObjectUtil.getProductDataFromCode(productValues[0]);
            entry.put(ProductList.PRODUCT_STRING, product);
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
            if (productFormat == null) {
                productFormat = quantityFormat;
            }
            for (int counter = 1; counter < productValues.length; counter++) {
                if(productFormat.contains(seats)) {
                    arrayList = true;
                    if(counter == 1) {
                        seatList = new String[Integer.parseInt(productValues[counter])];
                    } else {
                        seatList[counter - 2] = productValues[counter];
                    }
                } else {
                    entry.put(productFormat.get(counter - 1).toString(), productValues[counter]);
                }
            }
            if(arrayList) {
                entry.put(ProductList.SEATS, seatList);
            }
            this.JSONArray.put(entry);
        }
        return this.JSONArray;
    }

    public JSONArray getProducts() {
        return this.products;
    }
}
