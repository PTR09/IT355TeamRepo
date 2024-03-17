class example{
  //don't use a variable to signify whether the program
  //should stop running or not. 
}

//in another class or just somewhere else
List<sample> longL = new ArrayList<sample>();

//done with longL, now remove references:
longL.set(idx, null);
