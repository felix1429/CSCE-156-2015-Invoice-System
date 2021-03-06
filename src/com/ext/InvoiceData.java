package com.ext;

import database.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
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

            
        } catch (SQLException e) {
            System.out.println("Error in method removeAllPersons:");
            e.printStackTrace();
        }
    }

    /**
     * Removes the person record from the database corresponding to the
     * provided personCode
     *
     * @param personCode
     */
    public static void removePerson(String personCode) {
        try {
            String query = "DELETE FROM Persons WHERE PersonCode = ?";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{personCode});
            ps.executeUpdate();

            
        } catch (SQLException e) {
            System.out.println("Error in method removePerson:");
            e.printStackTrace();
        }
    }

    /**
     * Method to add a person record to the database with the provided data.
     *
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

            String query = "SELECT PersonID FROM Person WHERE PersonCode = ?";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{personCode});
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                personId = (Integer) rs.getObject("PersonID");
            }

            if (personId == null) {
                query = "INSERT INTO Address (Street, City, State, ZipCode, Country) "
                        + "VALUES (?, ?, ?, ?, ?)";

                ps = dam.prepareStatement(query, new Object[]{street, city, state, zip, country});
                ps.executeUpdate();

                query = "INSERT INTO Person (PersonCode, PersonLastName, PersonFirstName) "
                        + "VALUES (?, ?, ?)";

                ps = dam.prepareStatement(query, new Object[]{personCode, lastName, firstName});
                ps.executeUpdate();

                
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

            
        } catch (SQLException e) {
            System.out.println("Error in method removeALLVenues: ");
            e.printStackTrace();
        }
    }


    /**
     * Removes the venue record from the database corresponding to the
     * provided personCode
     *
     * @param venueCode
     */
    public static void removeVenue(String venueCode) {
        try {
            String query = "DELETE FROM Venue WHERE VenueCode = ?";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{venueCode});
            ps.executeUpdate();

            
        } catch (SQLException e) {
            System.out.println("Error in method removeVenue: ");
            e.printStackTrace();
        }
    }

    /**
     * Method to add a venue record to the database with the provided data.
     *
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
                                String city, String state, String zip, String country, int capacity) {
        try {
            Integer venueId = null;

            String query = "SELECT VenueID FROM Venue WHERE VenueCode = ?";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{venueCode});
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                venueId = (Integer) rs.getObject("VenueID");
            }

            if (venueId == null) {
                query = "INSERT INTO Address (Street, City, State, ZipCode, Country) "
                        + "VALUES (?, ?, ?, ?, ?)";

                ps = dam.prepareStatement(query, new Object[]{street, city, state, zip, country});
                ps.executeUpdate();

                query = "INSERT INTO Venue (VenueName, VenueCapacity) "
                        + "VALUES (?, ?)";
                ps = dam.prepareStatement(query, new Object[]{name, capacity});
                ps.executeUpdate();

                
            }
        } catch (SQLException e) {
            System.out.println("Error in method addVenue:");
            e.printStackTrace();
        }
    }

    /**
     * Adds an email record corresponding person record corresponding to the
     * provided personCode
     *
     * @param personCode
     * @param email
     */
    public static void addEmail(String personCode, String email) {
        try {
            Integer emailId = null;

            String query = "SELECT EmailID FROM Email WHERE EmailAddress = ?";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{email});
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                emailId = (Integer) rs.getObject("EmailID");
            }

            if (emailId == null) {
                query = "INSERT INTO Emial (EmailAddress) "
                        + "VALUES (?)";

                ps = dam.prepareStatement(query, new Object[]{personCode, email});
                ps.executeUpdate();

                
            }
        } catch (SQLException e) {
            System.out.println("Error in method addEmail:");
            e.printStackTrace();
        }
    }

    /**
     * Method that removes every customer record from the database
     */
    public static void removeAllCustomers() {
        try {
            String query = "DELETE FROM Customers";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{});
            ps.executeUpdate();

            
        } catch (SQLException e) {
            System.out.println("Error in method removeAllCustomers: ");
            e.printStackTrace();
        }
    }

    /**
     * Method to add a customerRecord to the database with the provided data.
     *
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
                                   String street, String city, String state, String zip, String country) {
        try {
            Integer customerId = null;

            String query = "SELECT CustomerId FROM Customer WHERE CustomerCode = ?";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{customerCode});
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customerId = (Integer) rs.getObject("CustomerID");
            }
            query = "INSERT INTO Address (street, city, state, zip, country) "
                    + "VALUES (?, ?, ?, ?, ?)";

            ps = dam.prepareStatement(query, new Object[]{street, city, state, zip, country});
            ps.executeUpdate();

            if (customerId == null) {
                query = "INSERT INTO Customer (customerCode, customerType, primaryContactPersonCode, name) "
                        + "VALUES (?, ?, ?, ?)";

                ps = dam.prepareStatement(query, new Object[]{customerCode, customerType, primaryContactPersonCode, name});
                ps.executeUpdate();

            }
            
        } catch (SQLException e) {
            System.out.println("Error in method addCustomer:");
            e.printStackTrace();
        }
    }

    /**
     * Removes all product records from the database
     */
    public static void removeAllProducts() {
        try {
            String query = "DELETE FROM Products";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{});
            ps.executeUpdate();

            
        } catch (SQLException e) {
            System.out.println("Error in method removeAllProducts: ");
            e.printStackTrace();
        }
    }

    /**
     * Removes a particular product record from the database corresponding to the
     * provided productCode
     *
     * @param productCode
     */
    public static void removeProduct(String productCode) {
        try {
            String query = "DELETE FROM Product WHERE productCode = ?";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{productCode});
            ps.executeUpdate();

            
        } catch (SQLException e) {
            System.out.println("Error in method removeProduct: ");
            e.printStackTrace();
        }
    }

    /**
     * Adds an gameTicket record to the database with the
     * provided data.
     */
    public static void addGameTicket(String productCode, String venueCode, String dateTime,
                                     String team1Name, String team2Name, double pricePerUnit) {
        try {
            String query = "INSERT INTO Product (GameDateTime, Code, VenueCode, Teams, Cost) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{dateTime, productCode, venueCode, team1Name + "," + team2Name, pricePerUnit});
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in method addGameTicket");
            e.printStackTrace();
        }
    }

    /**
     * Adds a seasonPass record to the database with the
     * provided data.
     */
    public static void addSeasonPass(String productCode, String teamName, String seasonStartDate, String seasonEndDate, double cost) {
        try {
            String query = "INSERT INTO Product (Code, Teams, Cost, StartDate, EndDate) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{productCode, teamName, cost, seasonStartDate, seasonEndDate});
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in method addSeasonPass");
            e.printStackTrace();
        }
    }

    /**
     * Adds a ParkingPass record to the database with the
     * provided data.
     */
    public static void addParkingPass(String productCode, String venueCode, double costPerHour) {
        try {

            String query = "INSERT INTO Product (ProductCode, VenueCode, CostPerHour) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{productCode, venueCode, costPerHour});
            ps.executeUpdate();

            

        } catch (SQLException e) {
            System.out.println("Error in method addParkingPass:");
            e.printStackTrace();
        }
    }

    /**
     * Adds a PSL record to the database with the
     * provided data.
     */
    public static void addPSL(String productCode, String ticketCode, double licenseFee) {
        try {

            String query = "INSERT INTO Product (ProductCode, TicketCode, LicenseFee) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{productCode, ticketCode, licenseFee});
            ps.executeUpdate();

            

        } catch (SQLException e) {
            System.out.println("Error in method addPSL:");
            e.printStackTrace();
        }
    }

    /**
     * Adds a refreshment record to the database with the
     * provided data.
     */
    public static void addRefreshment(String productCode, String name, double cost) {
        try {
            String query = "INSERT INTO Product (ProductCode, Refreshment, Cost) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{productCode, name, cost});
            ps.executeUpdate();

            
        } catch (SQLException e) {
            System.out.println("Error in method addRefreshment:");
            e.printStackTrace();
        }
    }

    /**
     * Removes all invoice records from the database
     */
    public static void removeAllInvoices() {
        try {
            String query = "DELETE FROM Invoice";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{});
            ps.executeUpdate();

            
        } catch (SQLException e) {
            System.out.println("Error in method removeAllInvoices: ");
            e.printStackTrace();
        }
    }

    /**
     * Removes the invoice record from the database corresponding to the
     * provided invoiceCode
     *
     * @param invoiceCode
     */
    public static void removeInvoice(String invoiceCode) {
        try {
            String query = "DELETE FROM Invoice WHERE invoiceCode = ?";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{invoiceCode});
            ps.executeUpdate();

            
        } catch (SQLException e) {
            System.out.println("Error in method removeInvoice: ");
            e.printStackTrace();
        }
    }

    /**
     * Adds an invoice record to the database with the given data.
     */
    public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode, String invoiceDate) {
        try {
            String query = "INSERT INTO Invoice (InvoiceCode, CustomerCode, Date, PersonCode) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{invoiceCode, customerCode, invoiceDate, salesPersonCode});
            ps.executeUpdate();

            
        } catch (SQLException e) {
            System.out.println("Error in method addInvoice:");
            e.printStackTrace();
        }
    }

    /**
     * Adds a particular gameticket (corresponding to productCode to an
     * invoice corresponding to the provided invoiceCode with the given
     * number of units
     */
    public static void addGameTicketToInvoice(String invoiceCode, String productCode, int numUnits) {
        try {
            String query = "INSERT INTO InvoiceProduct (ProductCode, InvoiceCode, NumberOfUnits) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{invoiceCode, productCode, numUnits});
            ps.executeUpdate();

            

        } catch (SQLException e) {
            System.out.println("Error in method addGameTicketToInvoice:");
            e.printStackTrace();
        }
    }

    /**
     * Adds a particular seasonpass (corresponding to productCode to an
     * invoice corresponding to the provided invoiceCode with the given
     * begin/end dates
     */
    public static void addSeasonPassToInvoice(String invoiceCode, String productCode, String startDate, int quantity) {
        try {
            String query = "INSERT INTO InvoiceProduct (ProductCode, InvoiceCode, StartDate, NumberOfUnits"
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = dam.prepareStatement(query, new Object[]{invoiceCode, productCode, startDate, quantity});
            ps.executeUpdate();

            

        } catch (SQLException e) {
            System.out.println("Error in method addGameTicketToInvoice:");
            e.printStackTrace();
        }
    }

    /**
     * Adds a particular Parkingpass (corresponding to productCode to an
     * invoice corresponding to the provided invoiceCode with the given
     * number of quantity.
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, String validDate, int quantity, int noOfHours) {
        try {

            String query = "INSERT INTO InvoiceProduct (InvoiceCode, ProductCode, BeginDate, NumberOfHours, NumberOfUnits) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{invoiceCode, productCode, validDate, quantity, noOfHours});
            ps.executeUpdate();

            

        } catch (SQLException e) {
            System.out.println("Error in method addParkingPassToInvoice:");
            e.printStackTrace();
        }
    }

    /**
     * Adds a particular PSL (corresponding to productCode to an
     * invoice corresponding to the provided invoiceCode with the given
     * number of quantity.
     */
    public static void addPSLToInvoice(String invoiceCode, String productCode, int quantity, String[] seats) {
        try {
            String query = "INSERT INTO InvoiceProduct (InvoiceCode, ProductCode, NumberOfUnits, Seats) "
                    + "VALUES (?, ?, ?, ?)";

            String seatsString = "";
            for (String seat : seats) {
                seatsString += (seat + ",");
            }

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{invoiceCode, productCode, quantity, seatsString});
        } catch (SQLException e) {
            System.out.println("Error in method addPSLToInvoice:");
            e.printStackTrace();
        }
    }

    /**
     * Adds a particular refreshment (corresponding to productCode to an
     * invoice corresponding to the provided invoiceCode with the given
     * number of quantity.
     */
    public static void addRefreshmentToInvoice(String invoiceCode, String productCode, int quantity) {
        try {

            String query = "INSERT INTO InvoiceProduct (InvoiceCode, ProductCode, NumberOfUnits) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement ps = dam.prepareStatement(query, new Object[]{invoiceCode, productCode, quantity});
            ps.executeUpdate();

            

        } catch (SQLException e) {
            System.out.println("Error in method addRefreshmentToInvoice:");
            e.printStackTrace();
        }
    }
}
