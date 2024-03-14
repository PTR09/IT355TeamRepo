import java.util.HashSet;
import java.util.Set;

public class ENV03 {
    private boolean isAuthenticated = false;
    private Set<String> grantedPermissions = new HashSet<>();

    public void grantPermissions(String... permissions) {
        if (isAuthenticated) {
            for (String permission : permissions) {
                if (isValidPermission(permission)) {
                    if (!isDangerousCombination(permission)) {
                        grantedPermissions.add(permission);
                        System.out.println("Permission granted: " + permission);
                    } else {
                        System.out.println("Invalid permission combination: " + permission);
                    }
                } else {
                    System.out.println("Invalid permission: " + permission);
                }
            }
        } else {
            System.out.println("User is not authenticated. Cannot grant permissions.");
        }
    }

    private boolean isValidPermission(String permission) {
        // Dummy method to validate permission
        return permission.matches("^[a-zA-Z]+$");
    }

    private boolean isDangerousCombination(String permission) {
        // Check if the given permission is a dangerous combination
        if (permission.equals("readUserData") && grantedPermissions.contains("writeUserData")) {
            return true;
        }
        return false;
    }

    public void signIn(String username, String password) {
        if (isValidCredentials(username, password)) {
            isAuthenticated = true;
            System.out.println("Successfully signed in as: " + username);
        } else {
            System.out.println("Invalid username or password");
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Dummy method to validate credentials
        return !username.isEmpty() && !password.isEmpty();
    }

    public static void main(String[] args) {
        ENV03 signInManager = new ENV03();
        signInManager.signIn("example_user", "password123");

        // Simulate user with role "admin" being granted permissions
        signInManager.grantPermissions("readUserData", "writeUserData");

        // Simulate user with role "guest" being granted permissions
        signInManager.grantPermissions("readUserData");
    }
}
