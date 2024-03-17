

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FIO14 {
  public static void main(String[] args) throws FileNotFoundException {
    final PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("foo.txt")));
    Runtime.getRuntime().addShutdownHook(new Thread() {
        public void run() {
          // Log shutdown and close all resources
          System.out.println("Closing file");
          out.close();
        }
    });
    out.println("hello");
    //out.close();
    // This is always preferable compared to Runtime halt since it runs shutdown hooks thus closing the file in this case
    System.exit(1);
    // This one should not be used due to skipping all shutdown hooks
    //Runtime.getRuntime().halt(1);
  }
}
