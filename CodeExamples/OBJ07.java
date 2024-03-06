public final class OBJ07 {
    private final String username;
    private final String password;
    
    public OBJ07(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public static void main(String[] args) {
        OBJ07 original = new OBJ07("user123", "password123");
    }
}
