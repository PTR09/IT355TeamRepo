import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FIO02 {
    public static void main(String[] args){
        try{
            File file = new File("example.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("Hello, World!");
            writer.close();
        } catch (IOException error){
            System.err.println("Cannot create or write to file");
        }
    }
    
}
