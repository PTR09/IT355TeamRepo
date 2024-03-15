class Parent {
    protected final void talk() {
        System.out.println("blahblahblah");
    }
}

class Child extends Parent {
    // Do not create an overridable method here
    protected Child() {
        super.talk();
    }
}

public class MET04 {
    public static void main(String[] args) {
        Child child = new Child();
        child.talk(); // "blahblahblah"
    }
}
