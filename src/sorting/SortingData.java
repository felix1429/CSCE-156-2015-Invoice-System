package sorting;


import database.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SortingData {
    private static ArrayList<String[]> names = new ArrayList<String[]>();

    private static Driver dam = new Driver(
            "jdbc:mysql://cse.unl.edu/thennig", "thennig", "LvFXo3");

    public static ArrayList<String[]> getNames() throws SQLException {

        names.clear();

        String query = "SELECT DISTINCT PersonLastName, PersonFirstName "
                + "FROM Persons JOIN Invoices ON Invoices.PersonID = Persons.PersonID;";

        PreparedStatement ps = dam.prepareStatement(query, new Object[] {});
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            names.add(new String[] {rs.getString("PersonLastName"),
                    rs.getString("PersonFirstName")});
        }
        dam.closeConnection(rs, ps);

        return names;
    }

    public static ArrayList<String[]> getCustomerTypes() throws SQLException {

        ArrayList<String[]> customerTypes = new ArrayList<String[]>();

        String query = "SELECT DISTINCT CustomerType, InvoiceCode, PersonLastName, PersonFirstName"
                + " FROM Customers JOIN Invoices"
                + " ON Invoices.CustomerID = Customers.CustomerID JOIN Persons"
                + " ON Persons.PersonID = Invoices.PersonID;";

        PreparedStatement ps = dam.prepareStatement(query, new Object[] {});
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            customerTypes.add(new String[] {rs.getString("CustomerType"),
                    rs.getString("InvoiceCode"), rs.getString("PersonLastName"), rs.getString("PersonFirstName")});
        }

        dam.closeConnection(rs, ps);

        return customerTypes;
    }
}
