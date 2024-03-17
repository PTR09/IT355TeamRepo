import java.util.*;

public class MET50 {
    List<Integer> lst;

    MET50(){
        lst = new ArrayList<>();
        lst.add(5);
        lst.add(23);
    }
    
    public static void main(String[] args) {
        MET50 obj = new MET50();
        System.out.println(obj.getInt(0));
        System.out.println(obj.integLocation(23));
    }

    // //Checking if int is in list
    // public int getInt(Integer integ){
    //     for(int i = 0; i < lst.size(); i++){
    //         if(lst.get(i).equals(integ)){
    //             return i;
    //         }
    //     }
    //     return -1;
    // }

    // Get item at location i
    public int getInt(int i){
        return lst.get(i);
    }

    // This is the better alternative for overloading since its clear and avoids the autoboxing issue
    public int integLocation(Integer integ){
        for(int i = 0; i < lst.size(); i++){
            if(lst.get(i).equals(integ)){
                return i;
            }
        }
        return -1;
    }


}
