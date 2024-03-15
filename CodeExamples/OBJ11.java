import java.io.*;
public class OBJ11
{
    /*
     * Example shows constructor that throws exception if there was an error reading from a file
     * reading from file not implemented in this code
     */
    public static void main(String[] args)
    {
        UserData userData = null;
        try 
        {
            //read from file and create new userData objects here

            //example of attempting to create a UserData object
            userData = new UserData("user123", "password123");
        } 
        catch (IOException e) 
        {
            //handle exception
            System.out.println("Error occurred while initializing user data: " + e.getMessage());
        }

        /*
         * if an exception occurred during initialization, userData might be null or partially initialized 
         * which would make it unsafe to access
         */
        //ensuring userData is not null
        if (userData != null) 
        {
            //accessing user data
            System.out.println("Username: " + userData.getUsername());
            System.out.println("Password: " + userData.getPassword());
        } 
        else 
        {
            System.out.println("User data is not available.");
        }
    }//end main
}//end class

class UserData 
{
    //defined as final so unable to be changed
    private final String username;
    private final String password;

    //constructor that throws exception
    public UserData(String username, String password) throws IOException 
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }
}//end class





