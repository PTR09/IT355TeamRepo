final class isTrue{
  private boolean boolVal = false;

  //the synchronized is used to tell the program
  //to run completely through this method before
  //continuing to the next method to prevent 
  //incorrect data sequencing
  public synchronized void toggle(){
    boolVal ^= true;
  }

  public synchronized boolean getVal(){
    return boolVal;
  }
}