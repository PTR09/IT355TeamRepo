import java.util.Arrays;

public class EXP02 {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        int[] array3 = {3, 2, 1};

        // Using Object.equals() for array reference comparison, which is unexpected if wanting to compare content
        System.out.println("Using Object.equals():");
        System.out.println(array1.equals(array2)); // false
        System.out.println(array1.equals(array3)); // false

        // Using Arrays.equals() for array content comparison
        System.out.println("\nUsing Arrays.equals():");
        System.out.println(Arrays.equals(array1, array2)); // true
        System.out.println(Arrays.equals(array1, array3)); // false

        // Using == for array reference comparison
        System.out.println("\nUsing ==:");
        System.out.println(array1 == array2); // false, not same reference
        System.out.println(array1 == array3); // false
    }
}
