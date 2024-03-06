public class MET01 {

    public static int calculateSum(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Arguments must be non-negative");
        }
        return x + y;
    }

    public static void main(String[] args) {
        try {
            int sum = calculateSum(-5, 10);
            System.out.println("Sum: " + sum);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
