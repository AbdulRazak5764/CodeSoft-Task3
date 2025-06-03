import java.util.Scanner;

public class ATM {
    private BankAccount userAccount;
    private Scanner scanner;
    
    public ATM(BankAccount account) {
        this.userAccount = account;
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        boolean exit = false;
        
        System.out.println("Welcome to the ATM!");
        System.out.println("Account: " + userAccount.getAccountNumber());
        
        while (!exit) {
            displayMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            
            System.out.println();
        }
    }
    
    private void displayMenu() {
        System.out.println("\n===== ATM MENU =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }
    
    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void checkBalance() {
        System.out.println("\n=== BALANCE INQUIRY ===");
        System.out.printf("Current Balance: $%.2f\n", userAccount.getBalance());
    }
    
    private void deposit() {
        System.out.println("\n=== DEPOSIT ===");
        System.out.print("Enter amount to deposit: $");
        
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            
            if (amount <= 0) {
                System.out.println("Error: Deposit amount must be positive.");
                return;
            }
            
            userAccount.deposit(amount);
            System.out.printf("Successfully deposited $%.2f\n", amount);
            System.out.printf("New Balance: $%.2f\n", userAccount.getBalance());
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid amount format.");
        }
    }
    
    private void withdraw() {
        System.out.println("\n=== WITHDRAWAL ===");
        System.out.print("Enter amount to withdraw: $");
        
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            
            if (amount <= 0) {
                System.out.println("Error: Withdrawal amount must be positive.");
                return;
            }
            
            if (amount > userAccount.getBalance()) {
                System.out.println("Error: Insufficient funds.");
                System.out.printf("Current Balance: $%.2f\n", userAccount.getBalance());
                return;
            }
            
            boolean success = userAccount.withdraw(amount);
            
            if (success) {
                System.out.printf("Successfully withdrew $%.2f\n", amount);
                System.out.printf("Remaining Balance: $%.2f\n", userAccount.getBalance());
            } else {
                System.out.println("Transaction failed. Please try again.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid amount format.");
        }
    }
}
