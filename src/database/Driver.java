package database;

import java.sql.*;

public class Driver {

    private String url;
    private String username;
    private String password;

    private static Connection conn;

    public Driver(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        this._instantiateDatabase();
    }

    private Connection _instantiateDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            System.out.println("InstantiationException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try {
            conn = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            System.out.println("SQLException: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return conn;
    }

    public PreparedStatement prepareStatement(String query,  Object args[]) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        int counter = 1;
        for(Object o : args) {
            try {
                System.out.println(o);
                int i = Integer.parseInt(String.valueOf(o));
                System.out.println("int   " + i);
                ps.setInt(counter, i);
            } catch (NumberFormatException e) {
                System.out.println("String  " + o);
                ps.setString(counter, String.valueOf(o));
            } catch (ClassCastException exp) {
                Float f = new Float(o.toString());
                System.out.println("Float  " + f);
                ps.setFloat(counter, f);
            }
            counter++;
        }
        System.out.println(ps.toString());
        return ps;
    }

    public void closeConnection(ResultSet rs, PreparedStatement ps) throws SQLException {
        rs.close();
        ps.close();
        conn.close();
    }

    public void closeConnection(PreparedStatement ps) throws SQLException {
        ps.close();
        conn.close();
    }
}
