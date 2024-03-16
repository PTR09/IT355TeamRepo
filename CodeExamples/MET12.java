import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*
 * This example shows clearing object data for garbage collector without 
 * relying on using a finalizer
 */
public class MET12
{
    private BufferedReader reader;
    public static void main(String[] args)
    {
        MET12 obj = null;
        try 
        {
            //assuming theres a test.txt in same directory
            obj = new MET12("test.txt");
        } catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            if (obj != null) 
            {
                //explicitly close object resources when done
                obj.close(); 
            }
        }
    }//end main

    public MET12(String fileName) throws IOException
    {
        this.reader = new BufferedReader(new FileReader(fileName));
        System.out.println("Object created!");
    }//end contructor

    //close object data for garbage collector
    public void close()
    {
        try 
        {
            if (reader != null) 
            {
                //close the BufferedReader
                reader.close();
                System.out.println("File reader closed.");
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }//end method
}//end class