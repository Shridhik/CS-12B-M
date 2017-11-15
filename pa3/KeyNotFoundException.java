//-----------------------------------------------------------------------------
// KeyNotFoundException.java
// Shridhik John
// shjohn@unix.ucsc.edu
// pa3
// Exception error for when a key is not found
//-----------------------------------------------------------------------------

public class KeyNotFoundException extends RuntimeException{
   public KeyNotFoundException(String s){
      super(s);
   }
}
