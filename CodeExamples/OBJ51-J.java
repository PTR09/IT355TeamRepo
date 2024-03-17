//Keeping class variables private and 
//keeping neccessary checks for accessibility
//is essential to data protection.

final class math {
  pirvate final int a;
  private final int b;

  math(int a, int b){
    this.a = a;
    this.b = b;
  }

  public int add(){
    return (a + b);
  }
}