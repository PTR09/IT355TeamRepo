public static void STR02-J(String word){
  Locale.setDefault(Locale.ENGLISH);
  
  // This allows the developer to specify words that 
  // cannot be entered into the system
  if(word.toUpperCase().equals("BANNED")){
    //another option is to use '.equalsIgnoreCase("BANNED")'
    //instead of declaring the default locale.
    
    return; //this causes the program to end this method
  }
  // code to use word for intended purpose.
}