import java.lang.InvalidStateException; 

// this is some object, could be any type
private Object myObject = null;

// this would be a setter method for an object of the same type 
// that sets your object parameter to the object parameter passed in. 
void setObject(Object parameter) throws InvalidStateException{ 
  
  //the rule essentially says you need checks for inconsistencies
  //when setting objects/parameters directly to another object/parameter
  if (parameter == null){
    throw null;
  }
  if(isInvalidState(parameter)){
    throw new InvalidStateException();
  }
  myObject = parameter;
}