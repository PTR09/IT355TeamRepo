/*
 * this example shows how to properly encode relationships
 * in constant definitions
 */
public class DCL55
{
    public static void main(String[] args)
    {
        //independently defined constant definitions
        final int monthsInYear = 12;
        final int weekInMonth = 4;

        //contant variables that rely on each others definitions
        final double PI = 3.14159265359;
        final double radius = 5.0;
        final double circumference = 2 * PI * radius;

        System.out.println("Circumference of circle with radius of 5: " + circumference);
        System.out.println("Number of months in a year: " + monthsInYear);
        System.out.println("Number of weeks in a month: " + weekInMonth);
    }//end main
}//end class