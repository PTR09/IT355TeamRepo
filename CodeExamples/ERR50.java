public class ERR50 {
    public static void main(String[] args) {
        int counter = 0;
        int[] nums = { 513, 62, 10, -52};

        //This is the bad way to do it
        // try {
        //     int i = 0;
        //     while(true){
        //         counter += nums[i];
        //         i++;
        //     }
            
        // } catch (Exception e) {
            
        // }

        //The proper way is a simple for loop
        for(int i = 0; i < nums.length; i++){
            counter += nums[i];
        }

        System.out.println("Counter is: " + counter);
    }
}
