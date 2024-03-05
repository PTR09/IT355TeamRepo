import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class FIO05 {
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {

        // An initial byte buffer with data
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.put("Sensitive data".getBytes(StandardCharsets.UTF_8));

        // Calling a method would do processing on the buffer
        processBuffer(buffer);
        
        // Print the contents of the buffer
        byte[] data = new byte[BUFFER_SIZE];
        buffer.flip();
        buffer.get(data);
        System.out.println("Data after processing: " + new String(data, StandardCharsets.UTF_8));
    }

    // Method that would process buffer
    // Assume it was filled
    public static void processBuffer(ByteBuffer buffer) {
        buffer.clear();
    }
}
