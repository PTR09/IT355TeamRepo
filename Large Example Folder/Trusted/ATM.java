package Trusted; //ENV01

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Rules used:
SEC05-J - All fields that shouldn't be accessed outside the class should be private.
EXP00-J - Do not ignore values returned by methods.
OBJ10-J - Do not use public static nonfinal fields.
OBJ11-J - Be wary of letting constructors throw exceptions.
MET00-J - Ensure method arguments are compatible with what you do with the variable.
FIO14-J - Perform proper cleanup at program termination.
MET05-J - Ensure that constructors do not call overridable methods.
MET12-J - Do not use finalizers.
FIO08-J - Distinguish between characters or bytes read from a stream and –1 
ENV02-J - Do not trust the values of environment variables.
FIO02-J - Detect and handle file-related errors
IDS01-J - Normalize Strings before validating them.
ENV01-J - Place all security-sensitive code in a single JAR and sign and seal it
ERR08-J - Do not catch NullPointerException or any of its ancestors
STR02-J - Specify an appropriate locale when comparing locale-dependent data

Recommendations used:
EXP50-J - Do not confuse abstract object equality with reference equality
EXP51-J - Do not perform assignments in conditional expressions.
EXP52-J - Use braces for the body of an if, for, or while statement.
DCL52-J - Do not declare more than one variable per declaration.
DCL50-J - Use visually distinct identifiers.
OBJ54-J - Do not attempt to help the garbage collector by setting local reference variables to null.
DCL53-J - Minimize the scope of variables.
MET50-J - Avoid ambiguous or confusing uses of overloading
IDS50-J - Use conservative file naming conventions
ERR50-J - Use exceptions only for exceptional conditions

*/

// ATM class to do bank operations
public class ATM {

    private static int choice = 0; //SEC05, OBJ10, DCL53
    private static String user = System.getProperty("user.name"); //ENV02

    public static void main(String[] args) {
        Bank account = new Bank(0.00);
        Bank account2 = new Bank(0.00);
        boolean isSame = account.equals(account2); //EXP50

        operations(account);
        
    }
    
    // Takes the user's input to perform a specific bank operations  
    private static void operations(Bank account) { //MET00
        Scanner scanner = new Scanner(System.in);
        //STR02
        Locale.setDefault(Locale.ENGLISH);
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
                     //This prevents a null value from being passed (ERR08)
                    if (depositAmount = null) 
                        depositAmoutn = 0;
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
                    //This prevents a null value from being passed (ERR08)
                    if (withdrawAmount = null)
                        withdrawAmount = 0;
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
                    System.exit(0); // FIO14
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    // Prints the menu for the user's choice input
    private static void printMenu() {
        System.out.println("Welcome " + user + " to the ATM.");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Read from file");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    // Prints the reciept of deposit and withdrawals along with the date and time
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

    // Allows a user to desposit from a file, Ex. depositing a check
    private static void depositFromFile(Bank account) {
        System.out.print("Enter the file name for deposit: ");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        //IDS01
        filePath = Normalizer.normalize(filePath, Form.NFKC);
        
        // Validate
        Pattern pattern = Pattern.compile("[^A-Za-z0-9._]"); //IDS50
        Matcher matcher = pattern.matcher(filePath);
        if (matcher.find()) {
            System.out.println("Illegal File Name");
            return;
        }

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextDouble()) {
                double depositAmount = fileScanner.nextDouble(); // FIO08
                try {
                    account.deposit(depositAmount);
                    System.out.println("Deposit of $" + depositAmount + " successful.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            }
        } catch (FileNotFoundException e) { //FIO02
            System.out.println("File not found.");
        }
        scanner.close();
    }
}

// Transaction class to create an object that holds amount and timestamp
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

