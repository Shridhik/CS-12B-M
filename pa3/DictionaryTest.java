// DictionaryTest.java
// Shridhik John
// shjohn@unix.ucsc.edu
// pa3
// self made Test file to check if Dictionary.java works

class DictionaryTest{
//public static void main (String[] args){
    //String v;  
    //Dictionary A = new Dictionary();
	//System.out.println(A.isEmpty());
	//System.out.println(A.size());
	//v = (A.lookup("1"));
        //System.out.println("key=1 "+(v==null?"not found":("value="+v)));
     // Dictionary A = new Dictionary();
     // A.insert("1","a");
     // A.insert("2","b");
     // A.insert("3","c");
     // A.insert("4","d");
     //A.insert("5","e");
     //A.insert("6","f");
     // A.insert("7","g");
     // System.out.println(A);

      //v = A.lookup("1");
      //System.out.println("key=1 "+(v==null?"not found":("value="+v)));
      //v = A.lookup("3");
      //System.out.println("key=3 "+(v==null?"not found":("value="+v)));
      //v = A.lookup("7");
      //System.out.println("key=7 "+(v==null?"not found":("value="+v)));
      //v = A.lookup("8");
      //System.out.println("key=8 "+(v==null?"not found":("value="+v)));
      //System.out.println();
      
	//System.out.println(A.size());
       //A.insert("2","f");  // causes DuplicateKeyException
      //A.delete("1");
      //A.delete("3");
      //System.out.println(A.size());	
      //A.delete("7");
      //System.out.println(A.size());
      //System.out.println(A);
      //}}



   public static void main(String[] args){
      String v;
      Dictionary A = new Dictionary();
      A.insert("1","a");
      A.insert("2","b");
      A.insert("3","c");
      A.insert("4","d");
      A.insert("5","e");
      A.insert("6","f");
      A.insert("7","g");
      System.out.println(A);

      v = A.lookup("1");
      System.out.println("key=1 "+(v==null?"not found":("value="+v)));
      v = A.lookup("3");
      System.out.println("key=3 "+(v==null?"not found":("value="+v)));
      v = A.lookup("7");
      System.out.println("key=7 "+(v==null?"not found":("value="+v)));
      v = A.lookup("8");
      System.out.println("key=8 "+(v==null?"not found":("value="+v)));
      System.out.println();

      // A.insert("2","f");  // causes DuplicateKeyException //works
      
      A.delete("1");
      A.delete("3");
      A.delete("7");
      System.out.println(A);

      A.delete("8");  // causes KeyNotFoundException // works
     
      System.out.println(A.isEmpty());
      System.out.println(A.size());
      A.makeEmpty();
      System.out.println(A.isEmpty());
      System.out.println(A);

   }
}
