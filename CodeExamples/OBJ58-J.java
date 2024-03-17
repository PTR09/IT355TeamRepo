//clarifies the package that you are adding to. 
package java.math;

//imports

public class Double{
  //this check is done as part of all constructors
  //not just the default. 
  public Double(double d){
    this(d, verify());
  }
}

private static boolean verify(){
  //the security check will call a different 
  //process if it results back false, therefore
  //we can just return true after verifying the data.
  securityCheck();
  return true;
}
