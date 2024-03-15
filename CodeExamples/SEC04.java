import java.security.*;

public class SEC04 {
    private double balance;

    public SEC04(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        check("depositPermission");
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public void withdraw(double amount) {
        check("withdrawPermission");
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    @SuppressWarnings("removal")
    private void check(String directive) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new SecurityPermission(directive));
        }
    }

    public static void main(String[] args) {
        SEC04 account = new SEC04(1000.0);
        account.deposit(500.0); // Example deposit
        account.withdraw(200.0); // Example withdrawal
    }
}
