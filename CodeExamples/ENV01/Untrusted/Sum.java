package ENV01.Untrusted;

public class Sum {
    //Since this is untrusted this can have any functionality in the function call causing unexpected results
    public int add(int num1, int num2){
        return num1 - num2;
    }
}
