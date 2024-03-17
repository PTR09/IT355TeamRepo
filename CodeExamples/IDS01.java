

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDS01 {
    public static void main(String[] args) {
        String s =  "\u2701"  + "script";
 
        // Normalize
        s = Normalizer.normalize(s, Form.NFKC);
        
        // Validate
        Pattern pattern = Pattern.compile("[✀✐✁]");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            System.out.println("found illegal character");
            throw new IllegalStateException();
        } else {
            System.out.println("no illegal character found");
        } 

    }
    

}
