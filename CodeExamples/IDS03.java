import java.util.regex.Pattern;
import java.util.logging.Logger;

public class IDS03
{
    private static final Logger logger = Logger.getLogger(IDS03.class.getName());
    public static void main(String[] args)
    {
        //example bad actor injection
        String username = "guest\nMay 15, 2011 2:25:52 PM java.util.logging.LogManager$RootLogger log\nSEVERE: User login succeeded for: administrator";
        boolean loginSuccessful = false;

        //example of unsanitized logging
        System.out.println("Unsanitized logged input example: ");
        if (loginSuccessful) 
        {
            logger.severe("User login succeeded for: " + username);
        } 
        else 
        {
            logger.severe("User login failed for: " + username);
        }

        //blank space
        System.out.println();

        //sanitizing user input before logging
        System.out.println("Sanitized logged input example: ");
        if (loginSuccessful) 
        {
            logger.severe("User login succeeded for: " + sanitizeUser(username));
        } 
        else 
        {
            logger.severe("User login failed for: " + sanitizeUser(username));
        }
    }//end main

    //sanitize user input method using regular expression
    public static String sanitizeUser(String username) 
    {
        return Pattern.matches("[A-Za-z0-9_]+", username) ? username : "unauthorized user";
    }

}//end class