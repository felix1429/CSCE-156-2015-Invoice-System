package com.ext;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 * NOTE: DONOT CHANGE THE PACKAGE NAME OR ANY OF THE METHOD SIGNATURES
 */
public class InvoiceData {

    /**
     * Method that removes every person record from the database
     */
    public static void removeAllPersons() {}

    /**
     * Removes the person record from the database corresponding to the
     * provided <code>personCode</code>
     * @param personCode
     */
    public static void removePerson(String personCode) {}

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
                                 String street, String city, String state, String zip, String country) {}

    /**
     * Method that removes every venue record from the database
     */
    public static void removeAllVenues() {}


    /**
     * Removes the venue record from the database corresponding to the
     * provided <code>personCode</code>
     * @param venueCode
     */
    public static void removeVenue(String venueCode) {}

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
     * provided <code>personCode</code>
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
     * provided <code>productCode</code>
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
     * provided <code>invoiceCode</code>
     * @param invoiceCode
     */
    public static void removeInvoice(String invoiceCode) {}

    /**
     * Adds an invoice record to the database with the given data.
     */
    public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode, String invoiceDate) {}

    /**
     * Adds a particular gameticket (corresponding to <code>productCode</code> to an
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of units
     */
    public static void addGameTicketToInvoice(String invoiceCode, String productCode, int numUnits) {}

    /**
     * Adds a particular seasonpass (corresponding to <code>productCode</code> to an
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * begin/end dates
     */
    public static void addSeasonPassToInvoice(String invoiceCode, String productCode, String startDate, int quantity){}

    /**
     * Adds a particular Parkingpass (corresponding to <code>productCode</code> to an
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, String validDate, int quantity, int noOfHours) {}

    /**
     * Adds a particular PSL (corresponding to <code>productCode</code> to an
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     */
    public static void addPSLToInvoice(String invoiceCode, String productCode, int quantity, String[] seats) {}

    /**
     * Adds a particular refreshment (corresponding to <code>productCode</code> to an
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     */
    public static void addRefreshmentToInvoice(String invoiceCode, String productCode, int quantity) {}
}
