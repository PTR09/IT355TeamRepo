class Animal {}

class Dog extends Animal {}

public class OBJ09 {
    public static void main(String[] args) {
        Animal animal = new Dog();

        // Incorrect way: Comparing class names as strings
        if (animal.getClass().getName().equals("package.Dog")) {
            System.out.println("The animal is a Dog.");
        } else {
            System.out.println("The animal is not a Dog.");
        }

        // Correct way: Comparing using ==
        if (animal.getClass() == Dog.class) {
            System.out.println("The animal is a Dog.");
        } else {
            System.out.println("The animal is not a Dog.");
        }
    }
}
