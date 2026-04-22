import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileHandler {
    private static final String FILE_NAME = "accounts.txt";

    // Saves the entire collection of accounts to the file
    public static void saveAllAccounts(Map<String, Account> accounts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts.values()) {
                writer.println(acc.getAccountNumber() + "," + acc.getAccountHolder() + "," + acc.getBalance());
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Loads all accounts from the file into a HashMap
    public static HashMap<String, Account> loadAllAccounts() {
        HashMap<String, Account> accounts = new HashMap<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            // Create a default account if the file doesn't exist yet
            Account defaultAcc = new Account("1001", "John Doe", 500.0);
            accounts.put(defaultAcc.getAccountNumber(), defaultAcc);
            return accounts;
        }

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String accNum = parts[0];
                    String name = parts[1];
                    double bal = Double.parseDouble(parts[2]);
                    accounts.put(accNum, new Account(accNum, name, bal));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing balance: " + e.getMessage());
        }
        return accounts;
    }
}