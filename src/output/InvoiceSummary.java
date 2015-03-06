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
    private double invoiceTax;
    private double invoiceSubtotal;
    private double invoiceTotal;
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
            invoiceTax = 0;
            invoiceSubtotal = 0;
            invoiceTotal = 0;
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
            invoiceTax += tax;
            invoiceSubtotal += subtotal;
            invoiceTotal += total;
            productString += InvoiceUtil.getNestedJSON(product, "product", "code")
                    + InvoiceUtil.generateString(" ", 6)
                    + itemInfo + (InvoiceUtil.generateString(" ", ITEM_TO_SUBTOTAL - itemInfo.length()))
                    + "$" + InvoiceUtil.generateString(" ", 10 - putTwoZeros(subtotal).length()) + putTwoZeros(subtotal)
                    + " $" + InvoiceUtil.generateString(" ", 10 - putTwoZeros(tax).length()) + putTwoZeros(tax)
                    + " $" + InvoiceUtil.generateString(" ", 10 - putTwoZeros(total).length()) + putTwoZeros(total) + "\n"
                    + (secondLine.equals("") ? secondLine : InvoiceUtil.generateString(" ", 10) + secondLine + "\n");
        }
        System.out.println(invoiceTotal);
        productString += getSubtotal()
                + getDiscount()
                + getAdditionalFee()
        + getTotal();
        return productString +"\n";
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
                info = getPSLInfo(product);
                break;
            case Product.REFRESHMENT_SHORT:
                info = getRefreshmentInfo(product);
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
                tax = getCustomerType().equals("Member") ? 0 : subtotal * TICKET_TAX_RATE;
                break;
            case Product.PERSONAL_SEAT_LICENCE_SHORT:
                quantity = ((String[])product.get("seats")).length;
                double ticketPrice = Double.parseDouble(InvoiceUtil.getNestedJSON(product, "product", "ticket", "price"));
                subtotal = ((ticketPrice * .18) * quantity) + 55;
                tax = getCustomerType().equals("Member") ? 0 : subtotal * SERVICE_TAX_RATE;
                break;
            case Product.REFRESHMENT_SHORT:
                quantity = Integer.parseInt(product.getString("quantity"));
                subtotal = Double.parseDouble(InvoiceUtil.getNestedJSON(product, "product", "cost")) * quantity;
                tax = getCustomerType().equals("Member") ? 0 : subtotal * SERVICE_TAX_RATE;
                break;
        }
        if(getCustomerType().equals("Member")) {
            total = (subtotal);
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

    private String getSubtotal() {
        return InvoiceUtil.generateString(" ", 80) + InvoiceUtil.generateString("=", 35) + "\n"
                + "SUBTOTAL" + InvoiceUtil.generateString(" ", 72) + "$"
                + InvoiceUtil.generateString(" ", 10 - putTwoZeros(invoiceSubtotal).length()) + putTwoZeros(invoiceSubtotal)
                + " $" + InvoiceUtil.generateString(" ", 10 - putTwoZeros(invoiceTax).length()) + putTwoZeros(invoiceTax)
                + " $" + InvoiceUtil.generateString(" ", 10 - putTwoZeros(invoiceTotal).length()) + putTwoZeros(invoiceTotal) + "\n";
    }

    private String getDiscount() throws JSONException {
        double discount = 1;
        boolean noTax = false;
        if(InvoiceUtil.getNestedJSON(invoice, "customer", "type").equals("M")) {
            noTax = true;
            discount = .93;
        } else if(InvoiceUtil.getNestedJSON(invoice, "customer", "type").equals("A")) {
            discount = .88;
        }
        invoiceTotal *= discount;
        return "DISCOUNT " + "(" + (int)(100 - (discount * 100)) + "%"
                + (noTax ? " & NO TAX)" + InvoiceUtil.generateString(" ", 82) : ")" + InvoiceUtil.generateString(" ", (102 - ("DISCOUNT " + "(" + (int)(100 - (discount * 100))).length())))
                + "$" + InvoiceUtil.generateString(" ", 9 - putTwoZeros(invoiceTotal * (1 - discount)).length())
                + "-" + putTwoZeros(invoiceTotal * (1 - discount)) + "\n";
    }

    private String getAdditionalFee() throws JSONException {
        int fee = 0;
        if(getCustomerType().equals("Member")) {
            fee = 120;
        } else if(getCustomerType().equals("Agent")) {
            fee = 150;
        }
        invoiceTotal += fee;
        return "ADDITIONAL FEE (" + getCustomerType() + ")"
                + InvoiceUtil.generateString(" ", 104 - ("ADDITIONAL FEE (" + getCustomerType() + ")").length())
                + "$" + InvoiceUtil.generateString(" ", 10 - putTwoZeros(fee).length()) + putTwoZeros(fee) + "\n";
    }

    private String getTotal() throws JSONException {
        return "TOTAL" + InvoiceUtil.generateString(" ", 99) + "$"
                + InvoiceUtil.generateString(" ", 10 - putTwoZeros(invoiceTotal).length()) + putTwoZeros(invoiceTotal);
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

    private String getPSLInfo(JSONObject product) throws JSONException {
        String[] seats = (String[])product.get("seats");
        return "PSL [" + seats[0] + ", " + seats[1] + "] (" + seats.length + " units @ 18% of "
                + InvoiceUtil.getNestedJSON(product, "product", "ticket", "code")
                + " with $55.00 fee)";
    }

    private String getRefreshmentInfo(JSONObject product) throws JSONException {
        int quantity = Integer.parseInt(product.getString("quantity"));
        return InvoiceUtil.getNestedJSON(product, "product", "name") + " ("
                + quantity + (quantity == 1 ? " unit" : " units") + " @ "
                + InvoiceUtil.getNestedJSON(product, "product", "cost") + "/unit)";
    }

    public String getFullReport() {
        return this.fullReport;
    }
}
