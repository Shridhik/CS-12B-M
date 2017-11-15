//-----------------------------------------------------------------------------
// List.java
// shjohn@ucsc.edu
// lab6
// Shridhik John
// Array based implementation of List ADT that takes a list of anything using generics 
// (with array doubling)
//-----------------------------------------------------------------------------
  @SuppressWarnings("overrides")
public class List<T> implements ListInterface<T>{
   
   private static final int INITIAL_SIZE = 1;
   private int physicalSize;      // current length of underlying array
   private T[] item;            // array of List items
   private int numItems;          // number of items in this List

   // List()
   // Constructor for the List class
  @SuppressWarnings("unchecked")
  public List(){
      physicalSize = INITIAL_SIZE;
      item =(T[]) new Object[physicalSize];
      numItems = 0;
   }

   // arrayIndex()
   // transforms a List index to an Array index
   private int arrayIndex(int listIndex){
      return listIndex-1;
   }

   // doubleItemArray()
   // doubles the physical size of the underlying array item[]
  @SuppressWarnings("unchecked")
   private void doubleItemArray(){
      physicalSize *=2;
      T[] newArray = (T[])new Object[physicalSize];
      for(int i=0; i<numItems; i++){
         newArray[i] = item[i];
      }
      item = newArray;
   }


   // ADT operations ----------------------------------------------------------

   // isEmpty()
   // pre: none
   // post: returns true if this IntgerList is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // size()
   // pre: none
   // post: returns the number of elements in this List
   public int size() {
      return numItems;
   }

   // get()
   // pre: 1 <= index <= size()
   // post: returns item at position index
   public T get(int index) throws ListIndexOutOfBoundsException {
      
      if( index<1 || index>numItems ){
         throw new ListIndexOutOfBoundsException(
            "get(): invalid index: "+index);
      }
      return item[arrayIndex(index)];
   }

   // add()
   // inserts newItem in this List at position index
   // pre: 1 <= index <= size()+1
   // post: !isEmpty(), items to the right of newItem are renumbered
   public void add(int index, T newItem) 
      throws ListIndexOutOfBoundsException{
      
      if( index<1 || index>(numItems+1) ){
         throw new ListIndexOutOfBoundsException(
            "add(): invalid index: "+index);
      }
      
      if( numItems == physicalSize ) {
         doubleItemArray();
      }
      
      for(int i=numItems; i>=index; i--) {
         item[arrayIndex(i+1)] = item[arrayIndex(i)];
      }
      item[arrayIndex(index)] = newItem;
      numItems++;
   }

   // remove()
   // deletes item from position index
   // pre: 1 <= index <= size()
   // post: items to the right of deleted item are renumbered
   public void remove(int index)
      throws ListIndexOutOfBoundsException{
         
      if( index<1 || index>numItems ){
         throw new ListIndexOutOfBoundsException(
            "remove(): invalid index: "+index);
      }
      
      for(int i=index+1; i<=numItems; i++){
         item[arrayIndex(i-1)] = item[arrayIndex(i)];
      }
      numItems--;
   }

   // removeAll()
   // pre: none
   // post: isEmpty()
   public void removeAll(){
      numItems = 0;
   }


   // Other operations --------------------------------------------------------

   // toString()
   // pre: none
   // post:  prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
      int i;
      String s = "";

      for(i=0; i<numItems; i++){
         s += item[i] + " ";
      }
      return s;
   }

   // equals()
   // pre: none
   // post: returns true if this List matches rhs, false otherwise
   // Overrides Object's equals() method
    @SuppressWarnings("unchecked")
   public boolean equals(Object rhs){
      int i = 0;
      boolean eq = false;
      List<T> R = null;


      if(this.getClass() == rhs.getClass()){
         R = (List<T>)rhs;
         eq = (this.numItems == R.numItems);
         while(eq && i<numItems){
            eq = (this.item[i] == R.item[i]);
            i++;
         }
      }
      return eq;
   }

}
