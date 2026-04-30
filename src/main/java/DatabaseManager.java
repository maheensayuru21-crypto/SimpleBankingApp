import java.sql.*;
import java.util.HashMap;

public class DatabaseManager {
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/bank_db";
    private static final String USER = "root"; // Your SQL username
    private static final String PASS = "Mmsql@21"; // Your SQL password
    public static Object updateBalance;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static HashMap<String, Account> loadAllAccounts() {
        HashMap<String, Account> accounts = new HashMap<>();
        String query = "SELECT * FROM accounts";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String accNum = rs.getString("account_number");
                String name = rs.getString("account_holder");
                double bal = rs.getDouble("balance");
                accounts.put(accNum, new Account(accNum, name, bal));
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
        return accounts;
    }
    public static void saveNewAccount(Account acc) {
        String query = "INSERT INTO accounts (account_number, account_holder, balance) VALUES (?, ?, ?)";
    
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, acc.getAccountNumber());
            pstmt.setString(2, acc.getAccountHolder());
            pstmt.setDouble(3, acc.getBalance());
            
            pstmt.executeUpdate();
            System.out.println("Account record created in database.");
        
        } catch (SQLException e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    public static void updateBalance(String accNum, double newBalance) {
        String query = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accNum);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }
}