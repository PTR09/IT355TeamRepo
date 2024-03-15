import java.io.*;
public class SER05
{
    public static void main(String[] args)
    {
        Outer outer = new Outer(10);
        Outer.Inner inner = new Outer.Inner(20);

        //serialize the outer object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("outer.txt"))) 
        {
            //inner object unable to be written to file because is not serialized
            //out.writeObject(inner);

            out.writeObject(outer);
            System.out.println("Outer object serialized successfully.");
        } 
        catch (IOException e)
        {
            System.err.println("Error occurred during serialization: " + e.getMessage());
        }

    }//end main
}//end class


//outer class
class Outer implements Serializable 
{
    private int outerData;

    //inner class
    static class Inner 
    {
        private int innerData;

        public Inner(int innerData) 
        {
            this.innerData = innerData;
        }

        public int getInnerData() 
        {
            return innerData;
        }
    }

    public Outer(int outerData) 
    {
        this.outerData = outerData;
    }

    public int getOuterData() 
    {
        return outerData;
    }
}//end class