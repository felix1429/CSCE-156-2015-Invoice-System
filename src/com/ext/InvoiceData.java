package com.ext;

import database.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class InvoiceData {

    private static Driver dam = new Driver(
            "jdbc:mysql://cse.unl.edu/thennig", "thennig", "LvFXo3");

    /**
     * Method that removes every person record from the database
     */
    public static void removeAllPersons() {
        try {
            String query = "DELETE FROM Persons";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{});
            ps.executeUpdate();

            dam.closeConnection(ps);
        } catch (SQLException e) {
            System.out.println("Error in method removeAllPersons:");
            e.printStackTrace();
        }
    }

    /**
     * Removes the person record from the database corresponding to the
     * provided personCode
     * @param personCode
     */
    public static void removePerson(String personCode) {
        try {
            String query = "DELETE FROM Persons WHERE PersonCode = ?";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{personCode});
            ps.executeUpdate();

            dam.closeConnection(ps);
        } catch (SQLException e) {
            System.out.println("Error in method removePerson:");
            e.printStackTrace();
        }
    }

    /**
     * Method to add a person record to the database with the provided data.
     * @param personCode
     * @param firstName
     * @param lastName
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param country
     */
    public static void addPerson(String personCode, String firstName, String lastName,
                                 String street, String city, String state, String zip, String country) {
        try {
            Integer personId = null;

            String query = "SELECT PersonID FROM Persons WHERE PersonCode = ?";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{personCode});
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                personId = (Integer) rs.getObject("PersonID");
            }

            if(personId == null) {
                query = "INSERT INTO Address (Street, City, State, ZipCode, Country) "
                    + "VALUES (?, ?, ?, ?, ?)";

                ps = dam.prepareStatement(query, new Object[]{street, city, state, zip, country});
                ps.executeUpdate();

                query = "INSERT INTO Person (PersonCode, PersonLastName, PersonFirstName) "
                        + "VALUES (?, ?, ?)";

                ps = dam.prepareStatement(query, new Object[]{personCode, lastName, firstName});
                ps.executeUpdate();

                dam.closeConnection(ps);
            }
        } catch (SQLException e) {
            System.out.println("Error in method addPerson:");
            e.printStackTrace();
        }
    }

    /**
     * Method that removes every venue record from the database
     */
    public static void removeAllVenues() {
        try {
            String query = "DELETE FROM Venues";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{});
            ps.executeUpdate();

            dam.closeConnection(ps);
        } catch (SQLException e) {
            System.out.println("Error in method removeALLVenues: ");
            e.printStackTrace();
        }
    }


    /**
     * Removes the venue record from the database corresponding to the
     * provided personCode
     * @param venueCode
     */
    public static void removeVenue(String venueCode) {
        try {
            String query = "DELETE FROM Venue WHERE VenueCode = ?";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{venueCode});
            ps.executeUpdate();

            dam.closeConnection(ps);
        } catch (SQLException e) {
            System.out.println("Error in method removeVenue: ");
            e.printStackTrace();
        }
    }

    /**
     * Method to add a venuerecord to the database with the provided data.
     * @param venueCode
     * @param name
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param country
     * @param capacity
     */
    public static void addVenue(String venueCode, String name, String street,
                                String city, String state, String zip, String country, int capacity) {}

    /**
     * Adds an email record corresponding person record corresponding to the
     * provided personCode
     * @param personCode
     * @param email
     */
    public static void addEmail(String personCode, String email) {}

    /**
     * Method that removes every customer record from the database
     */
    public static void removeAllCustomers() {}

    /**
     * Method to add a customerRecord to the database with the provided data.
     * @param customerCode
     * @param customerType
     * @param primaryContactPersonCode
     * @param name
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param country
     */
    public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode, String name,
                                   String street, String city, String state, String zip, String country) {}

    /**
     * Removes all product records from the database
     */
    public static void removeAllProducts() {}

    /**
     * Removes a particular product record from the database corresponding to the
     * provided productCode
     * @param assetCode
     */
    public static void removeProduct(String productCode) {}

    /**
     * Adds an gameTicket record to the database with the
     * provided data.
     */
    public static void addGameTicket(String productCode, String venueCode, String dateTime, String team1Name, String team2Name, double pricePerUnit) {	}

    /**
     * Adds a seasonPass record to the database with the
     * provided data.
     */
    public static void addSeasonPass(String productCode, String teamName, String seasonStartDate, String seasonEndDate, double cost) {}

    /**
     * Adds a ParkingPass record to the database with the
     * provided data.
     */
    public static void addParkingPass(String productCode, String venueCode, double costPerHour) {}

    /**
     * Adds a PSL record to the database with the
     * provided data.
     */
    public static void addPSL(String productCode, String ticketCode, double licenseFee) {}

    /**
     * Adds a refreshment record to the database with the
     * provided data.
     */
    public static void addRefreshment(String productCode, String name, double cost) {}

    /**
     * Removes all invoice records from the database
     */
    public static void removeAllInvoices() {}

    /**
     * Removes the invoice record from the database corresponding to the
     * provided invoiceCode
     * @param invoiceCode
     */
    public static void removeInvoice(String invoiceCode) {}

    /**
     * Adds an invoice record to the database with the given data.
     */
    public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode, String invoiceDate) {}

    /**
     * Adds a particular gameticket (corresponding to productCode to an
     * invoice corresponding to the provided invoiceCode with the given
     * number of units
     */
    public static void addGameTicketToInvoice(String invoiceCode, String productCode, int numUnits) {}

    /**
     * Adds a particular seasonpass (corresponding to productCode to an
     * invoice corresponding to the provided invoiceCode with the given
     * begin/end dates
     */
    public static void addSeasonPassToInvoice(String invoiceCode, String productCode, String startDate, int quantity){}

    /**
     * Adds a particular Parkingpass (corresponding to productCode to an
     * invoice corresponding to the provided invoiceCode with the given
     * number of quantity.
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, String validDate, int quantity, int noOfHours) {}

    /**
     * Adds a particular PSL (corresponding to productCode to an
     * invoice corresponding to the provided invoiceCode with the given
     * number of quantity.
     */
    public static void addPSLToInvoice(String invoiceCode, String productCode, int quantity, String[] seats) {}

    /**
     * Adds a particular refreshment (corresponding to productCode to an
     * invoice corresponding to the provided invoiceCode with the given
     * number of quantity.
     */
    public static void addRefreshmentToInvoice(String invoiceCode, String productCode, int quantity) {}
}
