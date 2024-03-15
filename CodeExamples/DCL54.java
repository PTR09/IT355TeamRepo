public class DCL54 {
    private static final double PI = 3.14;

    public static double circleArea(double radius){
        return PI * radius * radius;
    }

    public static void main(String[] args){
        int radius = 2;
        circleArea(radius);
    }
    
}
