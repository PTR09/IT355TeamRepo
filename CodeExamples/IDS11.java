import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDS11 {

  // Sanitizes the input string
  public static String filterString(String str) {
    // Normalize the string
    String normalizedString = Normalizer.normalize(str, Form.NFKC);

    // Replaces all noncharacter code points with Unicode U+FFFD
    normalizedString = normalizedString.replaceAll("[\\p{Cn}]", "\uFFFD");

    // Checks if '<script>' in the input, if so it would be invalid
    Pattern pattern = Pattern.compile("<script>");
    Matcher matcher = pattern.matcher(normalizedString);
    if (matcher.find()) {
      throw new IllegalArgumentException("Invalid input");
    }
    // String has been sanitized
    return normalizedString;
  }

  public static void main(String[] args) {
    // Example usage with a potentially malicious input
    String maliciousInput = "Hello, this is a <scr" + "\uFDEF" + "ipt> example";
    // Below string would give 'Invalid input'
    // String maliciousInput = "Hello, this is a <script> example!";
    String sanitizedInput = filterString(maliciousInput);
    System.out.println("Sanitized input: " + sanitizedInput);
    // If the string was altered again here, it would need to be re-sanitized
  }
}
