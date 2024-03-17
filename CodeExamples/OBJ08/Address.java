package OBJ08;

public class Address {
    private int houseNumber = 100;
    private String street = "N University Street";
    public class Location{
        public void display(){
            System.out.println(houseNumber + " " + street);
        }
    }
}
