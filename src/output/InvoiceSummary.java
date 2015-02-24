package output;

import org.json.*;
import utils.InvoiceUtil;

public class InvoiceSummary {

    private JSONArray input;
    private String fullReport;

    public InvoiceSummary(JSONArray input) throws JSONException {
        this.input = input;
        this.fullReport = generateFullReport();
    }

    private String generateFullReport() throws JSONException {
        String individualReports = generateIndividualReportList();
        return "";
    }

    private String generateIndividualReportList() throws JSONException {
        String output = "";
        output += "Individual Invoice Detail Reports\n";
        output += InvoiceUtil.generateString("=", 50) + "\n";
        for(int counter = 0; counter < input.length(); counter ++) {
            JSONObject invoice = (JSONObject)input.get(counter);
            output += getSalesperson(invoice) + "\n";
            output += getCustomerInfo(invoice);
            output += InvoiceUtil.generateString("-", 45) + "\n";
        }

        return output;
    }

    private String getCustomerInfo(JSONObject invoice) throws JSONException {
        String info = "";
        info += "Customer Info:\n";
        String infoIndent = "    ";
        info += infoIndent + getCustomerName(invoice) + " (" + getCustomerCode(invoice) + ")\n";
        info += infoIndent + getCustomerType(invoice) + "\n";
        info += infoIndent + getPrimaryContactName(invoice) + "\n";
        info += getCustomerAddress(invoice) + "\n";
        return info;
    }

    private String getCustomerType(JSONObject invoice) throws JSONException {
        String type = InvoiceUtil.getNestedJSON(invoice, "customer", "type");
        return InvoiceUtil.getFullCustomerType(type);
    }

    private String getCustomerAddress(JSONObject invoice) throws JSONException {
        return getAddress(invoice.getJSONObject("customer").getJSONObject("address"));
    }

    private String getAddress(JSONObject address) throws JSONException {
        String addressString = "";
        addressString += "    " + address.getString("street") + "\n";
        addressString += "    " + address.getString("city") + " " + address.getString("state") + " ";
        addressString += address.getString("zip") + " " + address.getString("country") + "\n";
        return addressString;
    }

    private String getSalesperson(JSONObject invoice) throws JSONException {
        return "Salesperson: " + getName(invoice.getJSONObject("salesperson"));
    }

    private String getCustomerName(JSONObject invoice) throws JSONException {
        return InvoiceUtil.getNestedJSON(invoice, "customer", "name");
    }

    private String getPrimaryContactName(JSONObject invoice) throws JSONException {
        return getName(invoice.getJSONObject("customer").getJSONObject("primaryContact"));
    }

    private String getCustomerCode(JSONObject invoice) throws JSONException {
        return InvoiceUtil.getNestedJSON(invoice, "customer", "customerCode");
    }

    private String getName(JSONObject person) throws JSONException {
        String firstName = person.getString("firstName");
        String lastName = person.getString("lastName");
        return lastName + ", " + firstName;
    }

    public String getFullReport() {
        return this.fullReport;
    }
}
