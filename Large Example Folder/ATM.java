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
OBJ11 - Be wary of letting constructors throw exceptions.
MET00 - Ensure method arguments are compatible with what you do with the variable.
FIO14 - Perform proper cleanup at program termination.
MET05 - Ensure that constructors do not call overridable methods.
MET12 - Do not use finalizers.
FIO08-J: Distinguish between characters or bytes read from a stream and –1 

Recommendations used:
EXP51 - Do not perform assignments in conditional expressions.
EXP51 - Use braces for the body of an if, for, or while statement.
DCL52 - Do not declare more than one variable per declaration.
DCL50 - Use visually distinct identifiers.
OBJ54 - Do not attempt to help the garbage collector by setting local reference variables to null.
DCL53 - Minimize the scope of variables.

*/


public class ATM {

    private static int choice = 0; //SEC05, OBJ10, DCL53

    public static void main(String[] args) {
        Bank account = new Bank(0.00);

        operations(account);
        
    }

    private static void operations(Bank account) { //MET00
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
                    System.out.println("Deposit From File.\n");
                    depositFromFile(account);
                    break;
                case 5:
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
        System.out.println("4. Read from file");
        System.out.println("5. Exit");
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
            String formattedDate = date.format(dateFormat); //DCL50
            if (amount > 0) { //EXP51, EXP52
                System.out.println("Deposit: $" + amount + " at " + formattedDate);
            } else {
                System.out.println("Withdrawal: -$" + Math.abs(amount) + " at " + formattedDate);
            }
        }
        System.out.println("Current balance: $" + account.getBalance());
    }

    private static void depositFromFile(Bank account) {
        System.out.print("Enter the file name for deposit: ");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextDouble()) {
                double depositAmount = fileScanner.nextDouble();
                try {
                    account.deposit(depositAmount);
                    System.out.println("Deposit of $" + depositAmount + " successful.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}

class Transaction {
    private double amount; //DCL52, OBJ54
    private long timestamp;

    public Transaction(double amount, long timestamp) { //OBJ11, MET05
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

