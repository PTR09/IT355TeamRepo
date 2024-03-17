

public class ENV02 {
    public static void main(String[] args) {
        String username = System.getenv("USER");
        System.out.println(username);
        username = System.getProperty("user.name");
        System.out.println(username);
    }
}
