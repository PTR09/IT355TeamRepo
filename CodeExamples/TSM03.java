/*
 * This example shows accessing object data after all fields
 * have been initalized
 */
public class TSM03
{
    private int value1;
    private int value2;
    public static void main(String[] args)
    {
        TSM03 obj = new TSM03(10,12);
        //access object data after initialization
        obj.add();
    }//end main

    public TSM03(int value1, int value2) 
    {
        this.value1 = value1;
        this.value2 = value2;
        //call initialization method after all fields are set
        showInitialized();
    }

    private void showInitialized()
    {
        //initialize object
        System.out.println("Object initialized with value1: " + value1);
        System.out.println("Object initialized with value2: " + value2);
    }

    public void add() 
    {
        //fully initalized object
        int value = value1 + value2;
        System.out.println("Object is fully initialized. Value: " + value);
    }
}//end class