

public class MET03 {
    public static void main(String args[]){
        UserVerifier uv = new UserVerifier();
        if(uv.verifyUser("134")){
            System.out.println("You have the correct password");
        }
        else{
            System.out.println("You do not have the correct password");
        }
    }

    static class UserVerifier{
        //If this method was not made final or private a subclass could overwrite this and not verify the password is correct
        public final boolean verifyUser(String password) { 
            if(password.equals("1234")){
                return true;
            }
            else{
                return false;
            }
        }
    }

    static class AttackerUserVerify extends UserVerifier{
        // // This will not work due to the innability to override final methods
        // @Override
        // public final boolean verifyUser(String password){
        //     return true;
        // }
    }
    
}
