protected static final Digester Digester = construct();

//general constructor for a digester
protected Digester construct(){
  //create a new digester
  Digester dig = new Digester();

  //since 'dig.getParser()' is referenced, this checks for 
  //a default parser check to ensure one has been initialized
  //prior to digester construction. 
  dig.getParser();
  return dig;
}