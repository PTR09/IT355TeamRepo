/*
 * This example shows comparing objects using the .equals() method
 */
public class EXP50
{
    public static void main(String[] args)
    {
        //string objects
        String name1 = new String("John");
        String name2 = new String("Carl");
        String name3 = new String("John");

        //checks if objects containing john and carl are same
        System.out.println(name1.equals(name2));
        //checks if objects containing john and john are same
        System.out.println(name1.equals(name3));

    }//end main
}//end class