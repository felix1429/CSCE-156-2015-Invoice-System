package sorting;

import com.sun.xml.internal.ws.api.server.SDDocument;

import java.sql.SQLException;
import java.util.ArrayList;

public class Sorting {

    public ArrayList<String[]> sortByCustomer() throws SQLException {
        ArrayList<String[]> names = SortingData.getNames();

        for(int i = 0; i < names.size(); i++) {
            int min = i;
            for(int j = i + 1; j < names.size(); j ++) {
                if(names.get(j)[0].compareTo(names.get(i)[0]) < 0) {
                    min = j;
                }
            }
            String[] tmp = names.get(i);
            names.set(i, names.get(min));
            names.set(min, tmp);
        }
        return names;
    }

    public ArrayList<String[]> sortByTotal() throws SQLException {
        ArrayList<String[]> invoiceTotals = SortingData.getTotals();

        for(int i = 0; i < invoiceTotals.size(); i++) {
            int min = i;
            for(int j = i + 1; j < invoiceTotals.size(); j ++) {
                if(Double.parseDouble(invoiceTotals.get(j)[1]) < Double.parseDouble(invoiceTotals.get(i)[1])) {
                    min = j;
                }
            }
            String[] tmp = invoiceTotals.get(i);
            invoiceTotals.set(i, invoiceTotals.get(min));
            invoiceTotals.set(min, tmp);
        }
        return invoiceTotals;
    }

    public ArrayList<String[]> sortByType() throws SQLException {
        ArrayList<String[]> customerTypes = SortingData.getCustomerTypes();
        ArrayList<String[]> member = new ArrayList<>();
        ArrayList<String[]> nonMember = new ArrayList<>();
        ArrayList<String[]> agent = new ArrayList<>();
        for(String[] i : customerTypes) {
            switch (i[0]) {
                case "M":
                    member.add(i);
                    break;
                case "A":
                    agent.add(i);
                    break;
                default:
                    nonMember.add(i);
                    break;
            }
        }

        ArrayList<String[]> types = new ArrayList<>();
        agent = sortBySalesperson(agent);
        types.addAll(agent);
        member = sortBySalesperson(member);
        types.addAll(member);
        nonMember = sortBySalesperson(nonMember);
        types.addAll(nonMember);
        return types;
    }

    private ArrayList<String[]> sortBySalesperson(ArrayList<String[]> in) {
        for(int i = 0; i < in.size(); i++) {
            int min = i;
            for(int j = i + 1; j < in.size(); j ++) {
                if(in.get(j)[0].compareTo(in.get(i)[0]) < 0) {
                    min = j;
                }
            }
            String[] tmp = in.get(i);
            in.set(i, in.get(min));
            in.set(min, tmp);
        }
        return in;
    }

    public ArrayList returnInvoices() {
        return new ArrayList();
    }
}
