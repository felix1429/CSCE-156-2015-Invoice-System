package objects.products.procuct_list;

import objects.products.product.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ObjectUtil;

import java.util.ArrayList;
import java.util.Collections;

public class ProductList {

    private static final String PRODUCT_LIST_STRIING = "products";
    private static final String PRODUCT_STRIING = "product";
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
    private JSONArray products = new JSONArray();

    public ProductList(String products) throws JSONException {
        this.products = this.parseProducts(products);
        this.JSONArray = new JSONArray();
    }

    private JSONArray parseProducts(String input) throws JSONException {
        String values[] = ObjectUtil.splitToTokens(input);
        for(int count = 0; count < values.length; count ++) {
            JSONObject entry = new JSONObject();
            JSONObject product = ObjectUtil.getProductDataFromCode(values[0]);
            entry.put(ProductList.PRODUCT_STRIING, product);
            String productType = product.getString("productType");
            String[] productValues = ObjectUtil.parseProductDataToTokens(values[count]);
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
            for (int counter = 0; counter < productValues.length; counter++) {
                entry.put(productFormat.get(counter).toString(), productValues[counter]);
            }
            this.JSONArray.put(entry);
        }
        return this.JSONArray;
    }

    public static ArrayList<Product> getProductListFormat() {
        return productFormat;
    }
}
