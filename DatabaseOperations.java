package ATM_system;

import java.sql.*;

public class DatabaseOperations {
    private Connection conn;
    private Statement stmt;

    DatabaseOperations() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                System.out.println(e);
            }
            conn = (Connection) DriverManager.getConnection(Credentials.SQL_URL + "?" +
                    "user=" + Credentials.SQL_USERNAME + "&password=" + Credentials.SQL_PASSWORD);
            stmt = (Statement) conn.createStatement();
            System.out.println("Connection is created successfully:");

        } catch (SQLException excep) {
            excep.printStackTrace();
        } catch (Exception excep) {
            excep.printStackTrace();
        } finally {

        }
    }

    private int insert(long cardNumber, short pin, long balance) {
        try {
            String query1 = "INSERT INTO accounts " + "VALUES (" + cardNumber + ", " + pin + ", " + balance + ")";
            stmt.executeUpdate(query1);
            System.out.println("Successfully inserted into table");
            return 1;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }

    private int update(long cardNumber, short pin, long balance) {
        try {
            String query1 = "UPDATE accounts " + "SET PIN = " + pin + ", BALANCE = " + balance + " WHERE CARD_NUMBER = " + cardNumber;
            stmt.executeUpdate(query1);
            System.out.println("Successfully Updated table");
            return 1;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Invalid number of arguments.");
            return;
        }
        long cardNumber = 0, balance = 0;
        short pin = 0;
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                cardNumber = Long.parseLong(args[i]);
            else if (i == 1)
                pin = Short.parseShort(args[i]);
            else
                balance = Long.parseLong(args[i]);
        }
        DatabaseOperations databaseOperations = new DatabaseOperations();
        databaseOperations.insert(cardNumber, pin, balance);
    }
}
