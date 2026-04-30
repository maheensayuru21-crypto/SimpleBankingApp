import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Load all existing accounts from the file
        HashMap<String, Account> bankMap = DatabaseManager.loadAllAccounts();
        boolean appRunning = true;

        while (appRunning) {
            try {
                System.out.println("\n===== MAIN MENU =====");
                System.out.println("1. Login to Existing Account");
                System.out.println("2. Register New Account");
                System.out.println("3. Exit");
                System.out.print("Choice: ");

                int mainChoice = scanner.nextInt();

                switch (mainChoice) {
                    case 1:
                        // --- LOGIN LOGIC ---
                        System.out.print("Enter Account Number: ");
                        String accNum = scanner.next();

                        if (bankMap.containsKey(accNum)) {
                            handleUserSession(bankMap.get(accNum), bankMap, scanner);
                        } else {
                            System.out.println("Error: Account not found.");
                        }
                        break;

                    case 2:
                        // --- REGISTRATION LOGIC ---
                        System.out.print("Enter Account Holder Name: ");
                        scanner.nextLine(); // Consume leftover newline
                        String name = scanner.nextLine();

                        System.out.print("Enter Initial Deposit: ");
                        double initialDep = scanner.nextDouble();

                        // Generate a random 4-digit ID (Simple version of a Primary Key)
                        String newAccNum = String.valueOf((int)(Math.random() * 9000) + 1000);
                        
                        Account newAcc = new Account(newAccNum, name, initialDep);
                        bankMap.put(newAccNum, newAcc);
                        
                        // Save the new user
                        DatabaseManager.saveNewAccount(newAcc);

                        System.out.println("\nSUCCESS! Account created.");
                        System.out.println("Your Account Number is: " + newAccNum);
                        break;

                    case 3:
                        appRunning = false;
                        System.out.println("Exiting System. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a numeric choice.");
                scanner.nextLine(); // Clear buffer
            }
        }
        scanner.close();
    }

    /**
     * This method handles the menu for a specific logged-in user.
     * This is a "Helper Method" which makes our code cleaner and more modular.
     */
    private static void handleUserSession(Account currentAccount, HashMap<String, Account> bankMap, Scanner scanner) {
        boolean sessionRunning = true;
        System.out.println("\nWelcome back, " + currentAccount.getAccountHolder() + "!");

        while (sessionRunning) {
            try {
                System.out.println("\n--- Dashboard (" + currentAccount.getAccountNumber() + ") ---");
                System.out.println("1. View Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Logout");
                System.out.print("Choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.printf("Current Balance: $%.2f\n", currentAccount.getBalance());
                        break;
                    // Case 2: Deposit
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double dAmount = scanner.nextDouble(); // Ensure this variable name matches
                        currentAccount.deposit(dAmount);
                        
                        // NEW SQL LOGIC: Update only the specific account in the DB
                        DatabaseManager.updateBalance(currentAccount.getAccountNumber(), currentAccount.getBalance());
                        break;

                    // Case 3: Withdraw
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double wAmount = scanner.nextDouble();
                        if (currentAccount.withdraw(wAmount)) {
                            // NEW SQL LOGIC
                            DatabaseManager.updateBalance(currentAccount.getAccountNumber(), currentAccount.getBalance());
                        }
                        break;
                    case 4:
                        sessionRunning = false;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid numeric input.");
                scanner.nextLine();
            }
        }
    }
}