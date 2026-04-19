import java.io.*;
import java.util.Scanner;

public class FileHandler {
    private static final String FILE_NAME = "balance.txt";

    // Method to save the balance to a file
    public static void saveBalance(double balance) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(String.valueOf(balance));
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Method to load the balance from a file
    public static double loadBalance() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return 500.0; // Default balance if no file exists
        }

        try (Scanner reader = new Scanner(file)) {
            if (reader.hasNextDouble()) {
                return reader.nextDouble();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return 500.0;
    }
}