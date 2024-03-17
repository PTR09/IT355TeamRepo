package OBJ05;

public class Numbers {
    private int[] nums;
    Numbers(){
        nums = new int[10];
        for(int i = 0; i < 10; i++){
            nums[i] = i;
        }
    }
    public int[] getNums(){
        //Don't do this because then any outside changes affect the inside class
        // return nums;
        return nums.clone();
    }

    public void displayNums(){
        for(int i = 0; i < 10; i++){
            System.out.print(nums[i] + ", ");
        }
        System.out.println();
    }
}
