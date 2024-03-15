import java.util.Scanner;

public class EXP00 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");

        // Do not do this:
        //input.nextLine();

        String userName = input.nextLine();

        System.out.println("Username: " + userName);

        input.close();
    }
    
}
