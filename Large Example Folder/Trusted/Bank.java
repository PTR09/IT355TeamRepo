package Trusted; //ENV01

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Rules:
LCK08-J: Ensure actively held locks are released on exceptional conditions
MET01-J: Never use assertions to validate method arguments
OBJ01-J: Limit accessibility of fields
OBJ05-J: Do not return references to private mutable class members
OBJ07-J: Sensitive classes must not let themselves be copied 
MET03-J: Methods that perform a security check must be declared private or final

Recommendations:
EXP51-J - Do not perform assignments in conditional expressions
EXP52-J: Use braces for the body of an if, for, or while statement 
MET50-J: Avoid ambiguous or confusing uses of overloading
ERR50-J: Use exceptions only for exceptional conditions


*/

public class Bank implements Cloneable{

    // OBJ01-J: Limit accessibility of fields
    private static final double MAX_BALANCE = 1000000.0;
    private double balance;
    private final Lock lock = new ReentrantLock();
    private ArrayList<Transaction> transactions;

    // Constructor for Bank
    public Bank(double initialBalance) {
        // Error check for if the initial balance is negative
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    // Deposit method with lock for thread safety LCK08
    final public void deposit(double amount) { // MET03
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        lock.lock();
        try {
            balance += amount;
            transactions.add(new Transaction(amount, System.currentTimeMillis()));
            if (balance > MAX_BALANCE) {
                balance = MAX_BALANCE;
            }
        } finally {
            lock.unlock();
        }
    }

    // Withdraw method with lock for thread safety
    final public void withdraw(double amount) throws InsufficientFundsException { // MET03
        if (amount < 0) { // Error checking for negative amounts
            throw new IllegalArgumentException("Withdrawal amount cannot be negative");
        }
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                transactions.add(new Transaction(-amount, System.currentTimeMillis()));
            } else { // Error check for if withdrawing more than what is in the account.
                throw new InsufficientFundsException("Insufficient funds for withdrawal");
            }
        } finally { // Unlock after performing the calculations
            lock.unlock();
        }
    }

    // Get Balance method with lock for thread safety
    final public double getBalance() { // MET03
        lock.lock();
        try {
            return balance;
        } finally { // Unlock after getting the balance
            lock.unlock();
        }
    }

    public ArrayList<Transaction> getTransactions() {
        //OBJ05-J never return references to private mutable class members
        return (ArrayList) transactions.clone();
    }

    // Protection against cloning sensitive classes for Rule OBJ07-J
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of Bank objects is not allowed");
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
