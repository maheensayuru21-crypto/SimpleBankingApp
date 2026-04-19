import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Initializing one account to test with
        double savedBalance = FileHandler.loadBalance();
        Account myAccount = new Account("1001", "John Doe", savedBalance);
        boolean running = true;

        System.out.println("--- Welcome to the Backend Bank ---");

        while (running) {
            try {
                System.out.println("\nSelect an operation:");
                System.out.println("1. View Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Current Balance: $" + myAccount.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double dAmount = scanner.nextDouble();
                        myAccount.deposit(dAmount);
                        FileHandler.saveBalance(myAccount.getBalance());
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double wAmount = scanner.nextDouble();
                        myAccount.withdraw(wAmount);
                        FileHandler.saveBalance(myAccount.getBalance());
                        break;
                    case 4:
                        running = false;
                        System.out.println("Session ended. Have a great day!");
                        break;
                    default:
                        System.out.println("Error: Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // CRITICAL: This clears the "bad" input from the scanner buffer
            }
        }
        scanner.close();

    }
}