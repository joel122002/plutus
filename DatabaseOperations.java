package ATM_system;

import java.sql.*;

public class DatabaseOperations {
    // Used to establish a connection to the database
    private Connection conn;
    // Used for executing a static SQL statement and returning the results it produces.
    private Statement stmt;

    // Connecting to database when constructor is called
    DatabaseOperations() {
        try {
            // Getting an instance the driver
            try {
                Class.forName("org.sqlite.JDBC");
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            // The object used for executing a static SQL statement and returning the results it produces.
            stmt = (Statement) conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS accounts (\n" +
                    "    CARD_NUMBER INTEGER ,\n" +
                    "    PIN INTEGER ,\n" +
                    "    BALANCE INTEGER \n" +
                    ");";
            stmt.execute(sql);
            System.out.println("Connection is created successfully:");

        } catch (SQLException excep) {
            excep.printStackTrace();
        } catch (Exception excep) {
            excep.printStackTrace();
        } finally {

        }
    }

    // Method to create a new user
    public int insert(long cardNumber, short pin, long balance) {
        try {
            // Creating a query with the new users data
            String query1 = "INSERT INTO accounts " + "VALUES (" + cardNumber + ", " + pin + ", " + balance + ")";
            // Executing the statement
            stmt.executeUpdate(query1);
            System.out.println("Successfully inserted into table");
            // Returns 1 if successful
            return 1;
        } catch (SQLException e) {
            System.out.println(e);
            // Returns 0 if unsuccessful
            return 0;
        }
    }

    // Method to update user details with the given card number
    public int update(long cardNumber, short pin, long balance) {
        try {
            // Creating a query with the users data
            String query1 = "UPDATE accounts " + "SET PIN = " + pin + ", BALANCE = " + balance + " WHERE CARD_NUMBER = " + cardNumber;
            // Executing the statement
            stmt.executeUpdate(query1);
            System.out.println("Successfully Updated table");
            // Returns 1 if successful
            return 1;
        } catch (SQLException e) {
            System.out.println(e);
            // Returns 0 if unsuccessful
            return 0;
        }
    }

    // Method that returns a User object with the given card number
    public User getUserInfo(long cardNumber) {
        // Creating a query to retrieve user's data with the given card number
        String query = "SELECT * FROM accounts WHERE CARD_NUMBER = " + cardNumber;
        try {
            // Getting the result
            ResultSet rs = stmt.executeQuery(query);
            // Iterating to the result and storing all column's data in variables
            while(rs.next()){
                long cardNumberOfUser = rs.getLong("CARD_NUMBER");
                short pinOfUser = rs.getShort("PIN");
                long balance = rs.getLong("BALANCE");
                // Creating a user object with this stored data
                return new User(cardNumberOfUser, pinOfUser, balance);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // Returning null if no user found or error encountered
        return null;
    }

    // Main method to add a user via command line
    public static void main(String[] args) {
        // Three arguments needed i.e. card number, pin and balance
        // If it detects more or less than 3 arguments we return with a error message
        if (args.length != 3) {
            System.out.println("Invalid number of arguments.");
            return;
        }
        // Initializing card number, balance and pin
        long cardNumber = 0, balance = 0;
        short pin = 0;
        // Getting card number, balance and pin from the command line
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                cardNumber = Long.parseLong(args[i]);
            else if (i == 1)
                pin = Short.parseShort(args[i]);
            else
                balance = Long.parseLong(args[i]);
        }
        // Creating an object of DatabaseOperations to insert
        DatabaseOperations databaseOperations = new DatabaseOperations();
        // Inserting the new user
        databaseOperations.insert(cardNumber, pin, balance);
    }
}
