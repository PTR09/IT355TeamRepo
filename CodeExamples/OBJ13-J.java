//this creates a back end list
private static final type [] arr = { ... };

//this sets a user end list to a clone of the 
//back end list
public static final type [] newArr() {
  return arr.CLONE();
}
//another method would be to remove the users ability
//to alter the list if you still wished to pass it through.