import java.util.Scanner;
 
class IDS07 
{
  /*
   * This example shows using a restricted user choice when dealing with directories
   * to prevent any possibilities of arugemnt injections
   */
  public static void main(String[] args) throws Exception 
  {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter an integer for what directory you want to enter: ");
    String dir = scan.nextLine();
 
    //this only allows integer choices
    int number = Integer.parseInt(dir);
    switch (number) 
    {
      //option 1
      case 1:
        dir = "data1";
        System.out.println("Accessing directory 1 with " + dir + ".");
        break;
      //option 2
      case 2:
        dir = "data2";
        System.out.println("Accessing directory 2 with " + dir + ".");
        break;
      //invalid
      default:
        dir = null;
        break;
    }
    //handle error
    if (dir == null) 
    {
        System.out.println("Directory not found.");
    }

  }//end main
}//end class