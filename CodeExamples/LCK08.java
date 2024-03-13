import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LCK08 {

    private final Lock lock = new ReentrantLock();

    public void doSomething() {
        lock.lock();
        try {
            // Critical section
            System.out.println("Inside critical section");
            if (someCondition()) {
                throw new RuntimeException("Error occurred");
            }
        } finally {
            // Ensure the lock is released even if an exception occurs
            if (((ReentrantLock) lock).isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    private boolean someCondition() {
        // Simulated condition
        return true;
    }

    public static void main(String[] args) {
        LCK08 example = new LCK08();
        example.doSomething();
    }
}

