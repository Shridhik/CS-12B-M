//-----------------------------------------------------------------------------
// DuplicateKeyException.java
// Shridhik John
// shjohn@unix.ucsc.edu
// pa3
// Exception error for input of a key that already exists
//-----------------------------------------------------------------------------

public class DuplicateKeyException extends RuntimeException{
   public DuplicateKeyException(String s){
      super(s);
   }
}
