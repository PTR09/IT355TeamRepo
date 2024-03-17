/*
 * this example shows converting floating-point operations to floating points 
 * to ensure no data is lost and program precision is maintained
 */
public class NUM50
{
    public static void main(String[] args)
    {
        int num1 = 9;
        int num2 = 4;
        //casting num1 to float to ensure the output of the division operator will be a float
        System.out.println((float)num1/num2);
        //making the digit 3 a literal floating point to ensure floating point output
        System.out.println(11/3f);
    }//end main
}//end class