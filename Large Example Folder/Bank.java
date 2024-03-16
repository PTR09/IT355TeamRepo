import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private static final double MAX_BALANCE = 1000000.0;
    private double balance;
    private final Lock lock = new ReentrantLock();

    // Constructor for Bank
    public Bank(double initialBalance) {
        if (initialBalance < 0) { // Error check for if the initial balance is negative
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
    }

    // Deposit method with lock for thread safety
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        lock.lock();
        try {
            balance += amount;
            if (balance > MAX_BALANCE) {
                balance = MAX_BALANCE;
            }
        } finally {
            lock.unlock();
        }
    }

    // Withdraw method with lock for thread safety
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount < 0) { // Error checking for negative amounts
            throw new IllegalArgumentException("Withdrawal amount cannot be negative");
        }
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
            } else { // Error check for if withdrawing more than what is in the account.
                throw new InsufficientFundsException("Insufficient funds for withdrawal");
            }
        } finally { // Unlock after performing the calculations
            lock.unlock();
        }
    }

    // Get Balance method with lock for thread safety
    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally { // Unlock after getting the balance
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Bank account = new Bank(1000.0);
        try {
            account.deposit(500.0);
            account.withdraw(200.0);
            System.out.println("Current balance: " + account.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Insufficient funds: " + e.getMessage());
        }
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
