import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

public class IDS50 {
    public static void main(String[] args) throws Exception {
        String filename = "*fileName";
        
        // Ensures only allowed characters are in the file name
        Pattern pattern = Pattern.compile("[^A-Za-z0-9._]");
        Matcher matcher = pattern.matcher(filename);
        if (matcher.find()) {
            throw new InvalidNameException();
        }
        File f = new File(filename);
        OutputStream out = new FileOutputStream(f);
        out.close();
    }
}
