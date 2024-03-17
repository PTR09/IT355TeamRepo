

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SEC05 {
    private int i = 3;
    private int j = 4;
    private int k = 8;
    private ArrayList<String> allowedToEdit = new ArrayList<String>();

    SEC05(){
        allowedToEdit.add("i");
        allowedToEdit.add("k");
    }
 
    public String toString() {
        return "FieldExample: i=" + i + ", j=" + j + ", k=" + k;
    }
 
    public void zeroI() {
        this.i = 0;
    }
 
    public void zeroField(String fieldName) {
        try {
            // This ensures that only fields that are directly allowed to be accessed in this way by outside calls can be changed
            if(!allowedToEdit.contains(fieldName)){
                throw new IllegalAccessException();
            }
            Field f = this.getClass().getDeclaredField(fieldName);

            f.setInt(this, 0);

        } catch (NoSuchFieldException ex) {
            System.out.println("no such field");
        } catch (IllegalAccessException ex) {
            System.out.println("Illegal access");
        }
    }
    
    public static void main(String[] args) {
        SEC05 fe = new SEC05();
        System.out.println(fe.toString());
        String[] variables = {"i", "j", "k"};
        for (String vari : variables) {
            fe.zeroField(vari);
            System.out.println(fe.toString());
        }
    }
}
