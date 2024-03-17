package OBJ05;

public class Obj05 {
    public static void main(String args[]){
        Numbers n = new Numbers();
        int[] nums = n.getNums();
        n.displayNums();
        nums[5] = 10;
        n.displayNums();
    }
}
