import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {

    public static int choice = 0;

    public static void main(String[] args) {
        Bank account = new Bank(0.00);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the ATM.");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
            choice = scanner.nextInt();
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
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }
}

