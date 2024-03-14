public class MET06 {
    public static void main(String[] args) {
        Person person1 = new Person("Alice", 30);

        try {
            // Cloning the person object
            Person person2 = (Person) person1.clone();

            // Printing the details of both original and cloned objects
            System.out.println("Original Person: " + person1.getName() + ", " + person1.getAge());
            System.out.println("Cloned Person: " + person2.getName() + ", " + person2.getAge());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

class Person implements Cloneable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public final Object clone() throws CloneNotSupportedException {
        // Constructors cannot be overridden
        Person clone = (Person) super.clone();
        // This method uses 'final', preventing overriding
        startCloning();

        return clone;
    }

    // As this method uses 'final', it cannot be overridden
    private final void startCloning() {
        // Add initialization logic here if needed
        System.out.println("Starting to clone Person...");
        System.out.println("Person cloned!");
    }
}
