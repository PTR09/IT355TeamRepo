/*
 * this example focuses on the minimization of scope variables
 * since the i variable is defined within it's own for loop scope,
 * i can be reused multiple different times throughout the program without
 * causing any errors
 */
public class DCL53
{
    public static void main(String[] args)
    {
        //count up to 5
        for (int i = 0; i <= 5; i++)
        {
            System.out.println("Count: " + i);
        }

        //count down from 5
        for (int i = 5; i >= 0; i--)
        {
            System.out.println("Count: " + i);
        }
    }//end main
}//end class