import java.io.*;
import java.util.*;

// Define a class to represent an item in the shopping cart
class CartItem implements Serializable {
    private static final long serialVersionUID = 1L; // Add a serialVersionUID to prevent version mismatch

    private String name;
    private double price;

    public CartItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ": $" + price;
    }
}

// Define a class to deserialize cart items with a whitelist
class CartItemDeserializer {
    private static final Set<String> whitelist = new HashSet<>(Arrays.asList("CartItem"));

    public static CartItem deserialize(byte[] buffer) throws IOException, ClassNotFoundException {
        CartItem ret = null;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer)) {
            try (ObjectInputStream ois = new WhitelistedObjectInputStream(bais, whitelist)) {
                ret = (CartItem) ois.readObject();
            }
        }
        return ret;
    }
}

// Define a custom ObjectInputStream to enforce a whitelist of allowed classes
class WhitelistedObjectInputStream extends ObjectInputStream {
    private final Set<String> whitelist;

    public WhitelistedObjectInputStream(InputStream inputStream, Set<String> whitelist) throws IOException {
        super(inputStream);
        this.whitelist = whitelist;
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass cls) throws IOException, ClassNotFoundException {
        if (!whitelist.contains(cls.getName())) {
            throw new InvalidClassException("Unexpected serialized class", cls.getName());
        }
        return super.resolveClass(cls);
    }
}

public class SER12 {
    public static void main(String[] args) {
        try {
            byte[] serializedData = serialize(new CartItem("Product", 19.99)); // Serialized data of CartItem object
            CartItem deserializedItem = CartItemDeserializer.deserialize(serializedData);
            System.out.println("Deserialization successful: " + deserializedItem);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization failed: " + e.getMessage());
        }
    }

    // Utility method to serialize a CartItem object
    private static byte[] serialize(CartItem item) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(item);
        }
        return baos.toByteArray();
    }
}
