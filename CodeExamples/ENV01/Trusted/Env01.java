package ENV01.Trusted;

public class Env01 {
    public static void main(String[] args) {
        //Ensure since this is supposed to be a trusted process that it only calls things within its trusted package
        Sum sum = new Sum();
        System.out.println(sum.add(2,5));
    }

}
