import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/* Rules used:
SEC05 - All fields that shouldn't be accessed outside the class should be private.
EXP00 - Do not ignore values returned by methods.
OBJ10 - Do not use public static nonfinal fields.
MET01 - Never use assertions to validate method arguments.


*/


public class ATM {

    private static int choice = 0; //SEC05, OBJ10

    public static void main(String[] args) {
        Bank account = new Bank(0.00);

        operations(account);
        
    }

    private static void operations(Bank account) { //MET01
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();

            try {
            choice = scanner.nextInt(); //EXP00
            } catch (InputMismatchException e) {
                System.out.println("Choose an integer, please try again.");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("Current balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    try {
                    account.deposit(depositAmount);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + "\n");
                        break;
                    }
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    try {
                        account.withdraw(withdrawAmount);
                    } catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage() + "\n");
                        break;
                    }
                    System.out.println("Withdrawal successful.\n");
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    scanner.close();
                    printReciept(account);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Welcome to the ATM.");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private static void printReciept(Bank account) {
        ArrayList<Transaction> transactions = account.getTransactions();
        System.out.println("Transaction history: ");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        for(int i = 0; i < transactions.size(); i ++) {
            Transaction transaction = transactions.get(i);
            Double amount = transaction.getAmount();
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(transaction.getTimestamp()), ZoneOffset.ofHours(7));
            String formattedDate = date.format(dateFormat);
            if (amount > 0) {
                System.out.println("Deposit: $" + amount + " at " + formattedDate);
            } else {
                System.out.println("Withdrawal: -$" + Math.abs(amount) + " at " + formattedDate);
            }
        }
        System.out.println("Current balance: $" + account.getBalance());
    }
}

class Transaction {
    private double amount;
    private long timestamp;

    public Transaction(double amount, long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public double getAmount(){
        return amount;
    }

    public long getTimestamp(){
        return timestamp;
    }

}

