import java.io.*;

public class SER01 {

    // Serialization
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    // Deserialization
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    // Serialize object to byte array
    public static byte[] serializeObject(Object obj) throws IOException {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput)) {
            objectOutput.writeObject(obj);
        }
        return byteOutput.toByteArray();
    }

    // Deserialize byte array to object
    public static Object deserializeObject(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteInput = new ByteArrayInputStream(data);
        try (ObjectInputStream objectInput = new ObjectInputStream(byteInput)) {
            return objectInput.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String exampleString = "Hello, World!";
        
        // Serialization
        byte[] serializedObj = serializeObject(exampleString);

        // Deserialization
        String deserializedString = (String) deserializeObject(serializedObj);
        
        // Output: Hello, World!
        System.out.println(deserializedString);
    }
}
