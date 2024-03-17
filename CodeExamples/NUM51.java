public class NUM51 {
    public int hash(int i, int j){
        int val = i % j;
        if(val < 0){
            return -val;
        }
        return val;
    }

    public int badHash(int i, int j){
        return i % j;
    }

    public static void main(String[] args) {
        NUM51 obj = new NUM51();
        System.out.println(obj.badHash(-5, 7));
        System.out.println(obj.hash(-5, 7));
    }

}
