import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Load the "Database"
        HashMap<String, Account> bankMap = FileHandler.loadAllAccounts();

        System.out.println("--- Welcome to the Backend Bank ---");
        System.out.print("Enter Account Number to Login: ");
        String accNum = scanner.next();

        // Check if account exists
        if (bankMap.containsKey(accNum)) {
            Account currentAccount = bankMap.get(accNum);
            System.out.println("Login Successful! Welcome, " + currentAccount.getAccountHolder());
            
            boolean running = true;

            while (running) {
                try {
                    System.out.println("\n--- " + currentAccount.getAccountHolder() + "'s Menu ---");
                    System.out.println("1. View Balance");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Exit");
                    System.out.print("Choice: ");

                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.printf("Current Balance: $%.2f\n", currentAccount.getBalance());
                            break;
                        case 2:
                            System.out.print("Enter deposit amount: ");
                            double dAmount = scanner.nextDouble();
                            currentAccount.deposit(dAmount);
                            FileHandler.saveAllAccounts(bankMap);
                            break;
                        case 3:
                            System.out.print("Enter withdrawal amount: ");
                            double wAmount = scanner.nextDouble();
                            if (currentAccount.withdraw(wAmount)) {
                                FileHandler.saveAllAccounts(bankMap);
                            }
                            break;
                        case 4:
                            running = false;
                            System.out.println("Logged out. Goodbye!");
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid number.");
                    scanner.nextLine(); // Clear the bad input
                }
            }
        } else {
            System.out.println("Access Denied: Account number not found.");
        }
        
        scanner.close();
    }
}