package output;

import objects.products.product.Product;
import org.json.*;
import utils.InvoiceUtil;

public class InvoiceSummary {

    private JSONArray input;
    private JSONObject invoice;
    private String fullReport;
    private String secondLine;
    private double subtotal;
    private double tax;
    private double total;
    private final double TICKET_TAX_RATE = .06;
    private final double SERVICE_TAX_RATE = .04;
    private final int ITEM_TO_SUBTOTAL = 70;

    public InvoiceSummary(JSONArray input) throws JSONException {
        this.input = input;
        this.fullReport = generateFullReport();
    }

    private String generateFullReport() throws JSONException {
        String individualReports = generateIndividualReportList();
        return individualReports;
    }

    private String generateIndividualReportList() throws JSONException {
        String output = "";
        output += "Individual Invoice Detail Reports\n"
                + InvoiceUtil.generateString("=", 50) + "\n";
        for(int counter = 0; counter < input.length(); counter ++) {
            invoice = (JSONObject)input.get(counter);
            output += getHeader() + "\n"
                    + getSalesperson() + "\n"
                    + getCustomerInfo()
                    + InvoiceUtil.generateString("-", 45) + "\n"
                    + getColumns() + "\n"
                    + getProductInfo()
                    + "\n\n";
        }

        return output;
    }

    private String getCustomerInfo() throws JSONException {
        String infoIndent = "    ";
        return "Customer Info:\n"
                + infoIndent + getCustomerName() + " (" + getCustomerCode() + ")\n"
                + infoIndent + getCustomerType() + "\n"
                + infoIndent + getPrimaryContactName() + "\n"
                + getCustomerAddress();
    }

    private String getCustomerType() throws JSONException {
        String type = InvoiceUtil.getNestedJSON(invoice, "customer", "type");
        return InvoiceUtil.getFullCustomerType(type);
    }

    private String getCustomerAddress() throws JSONException {
        return getAddress(invoice.getJSONObject("customer").getJSONObject("address"));
    }

    private String getAddress(JSONObject address) throws JSONException {
        return "    " + address.getString("street") + "\n"
                + "    " + address.getString("city") + " " + address.getString("state") + " "
                + address.getString("zip") + " " + address.getString("country") + "\n";
    }

    private String getSalesperson() throws JSONException {
        return "Salesperson: " + getName(invoice.getJSONObject("salesperson"));
    }

    private String getCustomerName() throws JSONException {
        return InvoiceUtil.getNestedJSON(invoice, "customer", "name");
    }

    private String getPrimaryContactName() throws JSONException {
        return getName(invoice.getJSONObject("customer").getJSONObject("primaryContact"));
    }

    private String getCustomerCode() throws JSONException {
        return InvoiceUtil.getNestedJSON(invoice, "customer", "customerCode");
    }

    private String getName(JSONObject person) throws JSONException {
        String firstName = person.getString("firstName");
        String lastName = person.getString("lastName");
        return lastName + ", " + firstName;
    }

    private String getHeader() throws JSONException {
        return "Invoice " + InvoiceUtil.getNestedJSON(invoice, "invoiceCode") + "\n"
                + InvoiceUtil.generateString("=", 24);
    }

    private String getColumns() throws JSONException {
        return "Code" + InvoiceUtil.generateString(" ", 6) + "Item"
                + InvoiceUtil.generateString(" ", 70) + "Subtotal" + InvoiceUtil.generateString(" ", 8)
                + "Tax" + InvoiceUtil.generateString(" ", 7) + "Total";
    }

    private String getProductInfo() throws JSONException {
        String productString = "";
        JSONArray products = invoice.getJSONArray("products");
        JSONObject product;
        for(int counter = 0; counter < products.length(); counter ++) {
            product = (JSONObject) products.get(counter);
            secondLine = "";
            String itemInfo = getItemInfo(product);
            getItemTotal(product);
            productString += InvoiceUtil.getNestedJSON(product, "product", "code")
                    + InvoiceUtil.generateString(" ", 6)
                    + itemInfo + (InvoiceUtil.generateString(" ", ITEM_TO_SUBTOTAL - itemInfo.length()))
                    + "$" + InvoiceUtil.generateString(" ", 10 - putTwoZeros(subtotal).length()) + putTwoZeros(subtotal)
                    + " $" + InvoiceUtil.generateString(" ", 10 - putTwoZeros(tax).length()) + putTwoZeros(tax)
                    + " $" + InvoiceUtil.generateString(" ", 10 - putTwoZeros(total).length()) + putTwoZeros(total) + "\n"
                    + (secondLine.equals("") ? "" : InvoiceUtil.generateString(" ", 10) + secondLine + "\n");
        }
        return productString;
    }

    private String getItemInfo(JSONObject product) throws JSONException {
        String info = "";
        switch (InvoiceUtil.getNestedJSON(product, "product", "productType")) {
            case Product.PARKING_PASS_SHORT:
                info = getParkingPassInfo(product);
                break;
            case Product.GAME_TICKET_SHORT:
                info = getGameTicketInfo(product);
                break;
            case Product.SEASON_PASS_SHORT:
                info = getSeasonPassInfo(product);
                break;
            case Product.PERSONAL_SEAT_LICENCE_SHORT:
//                info = getPSLInfo(product);
                break;
            case Product.REFRESHMENT_SHORT:
//                info = getRefreshmentInfo(product);
                break;
        }
        return info;
    }

    private void getItemTotal(JSONObject product) throws JSONException {
        int quantity;
        double price;
        switch (InvoiceUtil.getNestedJSON(product, "product", "productType")) {
            case Product.PARKING_PASS_SHORT:
                quantity = Integer.parseInt(product.getString("quantity"));
                int hoursValid = Integer.parseInt(product.getString("hoursValid"));
                price = Double.parseDouble(InvoiceUtil.getNestedJSON(product, "product", "hourlyFee"));
                subtotal = quantity * hoursValid * price;
                tax = getCustomerType().equals("Member") ? 0 : subtotal * SERVICE_TAX_RATE;
                break;
            case Product.GAME_TICKET_SHORT:
                quantity = Integer.parseInt(product.getString("quantity"));
                price = Double.parseDouble(InvoiceUtil.getNestedJSON(product, "product", "price"));
                subtotal = quantity * price;
                tax = getCustomerType().equals("Member") ? 0 : subtotal * TICKET_TAX_RATE;
                break;
            case Product.SEASON_PASS_SHORT:
                quantity = Integer.parseInt(product.getString("quantity"));
                String startDate = InvoiceUtil.getNestedJSON(product, "product", "startDate");
                String endDate = InvoiceUtil.getNestedJSON(product, "product", "endDate");
                int daysBetween = InvoiceUtil.getDaysBetweenDates(startDate, endDate);
                String invoiceDate = invoice.getString("invoiceDate");
                int effectiveDays = (daysBetween - InvoiceUtil.getDaysBetweenDates(startDate, invoiceDate));
                price = Double.parseDouble(InvoiceUtil.getNestedJSON(product, "product", "price"));
                if(daysBetween == effectiveDays) {
                    subtotal = price * quantity;
                } else {
                    subtotal = ((price / daysBetween) * effectiveDays + ((daysBetween - effectiveDays) * ((price / daysBetween) * .3))) * quantity;
                }
                tax = getCustomerType().equals("Member") ? 0 : subtotal * SERVICE_TAX_RATE;
                break;
            case Product.PERSONAL_SEAT_LICENCE_SHORT:
//                info = getPSLInfo(product);
                break;
            case Product.REFRESHMENT_SHORT:
//                info = getRefreshmentInfo(product);
                break;
        }
        if(getCustomerType().equals("Member")) {
            total = (subtotal * .97) + 120;
        } else if(getCustomerType().equals("Agent")) {
            total = (subtotal * .88) + tax + 150;
        } else {
            total = subtotal + tax;
        }
    }

    public String putTwoZeros(double in) {
        in = Math.round(in * 100.0) / 100.0;
        String st = String.valueOf(in);
        if(st.substring((st.length() - 2)).contains(".")) {
            st += "0";
        }
        return st;
    }

    private String getParkingPassInfo(JSONObject product) throws JSONException {
        int quantity = Integer.parseInt(product.getString("quantity"));
        int hoursValid = Integer.parseInt(product.getString("hoursValid"));
        return "Parking Pass " + product.getString("date")
                + " (" + quantity + " " + (quantity == 1 ? "unit" : "units") + " @ $"
                + InvoiceUtil.getNestedJSON(product, "product", "hourlyFee") + "/hr for "
                + hoursValid + (hoursValid == 1 ? " hr" : " hrs") + ")";
    }

    private String getGameTicketInfo(JSONObject product) throws JSONException {
        int quantity = Integer.parseInt(product.getString("quantity"));
        String price = InvoiceUtil.getNestedJSON(product, "product", "price");
        secondLine = "(" + quantity + " " + (quantity == 1 ? "unit" : "units") + " @ $" + price + "/unit)";
        return "Game Ticket " + InvoiceUtil.getNestedJSON(product, "product", "gameDateTime")
                + " " + InvoiceUtil.getNestedJSON(product, "product", "team1") + " vs "
                + InvoiceUtil.getNestedJSON(product, "product", "team2");
    }

    private String getSeasonPassInfo(JSONObject product) throws JSONException {
        int quantity = Integer.parseInt(product.getString("quantity"));
        String startDate = InvoiceUtil.getNestedJSON(product, "product", "startDate");
        String endDate = InvoiceUtil.getNestedJSON(product, "product", "endDate");
        String invoiceDate = invoice.getString("invoiceDate");
        int daysBetween = InvoiceUtil.getDaysBetweenDates(startDate, endDate);
        secondLine = "(" + quantity + " units @ $" + InvoiceUtil.getNestedJSON(product, "product", "price")
                + "0/unit prorated " + ((daysBetween - InvoiceUtil.getDaysBetweenDates(startDate, invoiceDate)) + 1)
                + "/" + daysBetween + " days)";
        return "Season Pass - " + InvoiceUtil.getNestedJSON(product, "product", "team");
    }

//    private String getPSLInfo(JSONObject product) {
//
//    }
//
//    private String getRefreshmentInfo(JSONObject product) {
//
//    }
//
    public String getFullReport() {
        return this.fullReport;
    }
}
