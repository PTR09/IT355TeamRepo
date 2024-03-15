public class IDS11 {
    public static void main(String[] args) {
        String input = "IsThisValid?";

        // Perform string modifications before validation
        String modifiedString = modifyString(input);

        // Validate the modified string
        boolean isValid = validateString(modifiedString);

        // If modifications were made here, like removing the '?',
        // the string would then be valid which is not good practice.

        // Output validation result
        if (isValid) {
            System.out.println("The modified string is valid.");
        } else {
            System.out.println("The modified string is not valid.");
        }
    }

    // Perform any string modifications before validation
    public static String modifyString(String input) {
        // Example: Convert the string to lowercase
        return input.replace('?', '0');
    }

    public static boolean validateString(String input) {
        // Example: Check if the string contains only letters or numbers using regex
        return input.matches("[a-zA-Z0-9]+");
    }
}
