import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FIO03 {
    public static void main(String[] args) {
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("tempnam", ".tmp");

            try (BufferedWriter writer =
                Files.newBufferedWriter(tempFile, Charset.forName("UTF8"),
                                        StandardOpenOption.DELETE_ON_CLOSE)) {
                writer.write("temporary file");
                writer.newLine();
            }
        
            System.out.println("Temporary file write done, file erased");
        } catch (FileAlreadyExistsException x) {
            System.err.println("File exists: " + tempFile);
        } catch (IOException x) {
            System.err.println("Error creating temporary file: " + x);
        }
    }
}
