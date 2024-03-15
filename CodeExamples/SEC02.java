import java.util.ArrayList;
import java.util.List;

public class SEC02 {
    public static void main(String[] args) {
        // Untrusted list from an untrusted source
        ArrayList<String> untrustedList = new ArrayList<>();
        untrustedList.add("Chloe");
        untrustedList.add("Jacob");
        untrustedList.add("Alex");

        // Security check based on the untrusted list
        performSecurityCheck(untrustedList);

        // Modify the untrusted list after the security check
        untrustedList.add("Marco");

        // Now perform the security check again
        performSecurityCheck(untrustedList);
    }

    // Perform a security check based on the provided list
    public static void performSecurityCheck(ArrayList<String> inputList) {
        // Defensive copy of the input list to prevent modification
        List<String> copyList = new ArrayList<>(inputList);

        int size = inputList.size();
        for (int i = 0; i < size; i++) {
            String item = inputList.get(i);
            copyList.add(new String(item));
        }

        // Check if the list contains any sensitive information
        for (int i = 0; i < copyList.size(); i++) {
            String item = copyList.get(i);
            if (item.equals("Marco")) {
                System.out.println("Security check failed: Marco is in the list.");
                return;
            }
        }
        System.out.println("Security check passed: No sensitive information found.");
    }
}
