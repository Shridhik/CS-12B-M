//-----------------------------------------------------------------------------
// Dictionary.java
// Shridhik John
// shjohn@unix.ucsc.edu
// pa3
// Linked List implementation of the Dictionary ADT
//-----------------------------------------------------------------------------

public class Dictionary implements DictionaryInterface {

   // private inner Node class
   private class Node {
      String key;
      String value;
      Node next;

      Node(String key, String value){
         this.key = key;
         this.value = value;
         next = null;
      }
   }

   // Fields for the IntegerList class
   private Node head;     // reference to first Node in List
   private int numItems;  // number of items in this IntegerList

   // Dictionary()
   // constructor for the IntegerList class
   public Dictionary(){
      head = null;
      numItems = 0;
   }

// Implementation of ADT operations //////////////////////////////////////

   // lookup()
   // returns a reference to the Node at position index in this IntegerList
   public String  lookup(String key){
      Node N = head;
      while (N!= null){
	if( N.key.equals(key)){
      	    return N.value;
	    }
	N = N.next;
	}
	return null;
   }

   // isEmpty()
   // pre: none
   // post: returns true if this IntgerList is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // size()
   // pre: none
   // post: returns the number of elements in this IntegerList
   public int size() {
      return numItems;
   }


   // insert()
   // inserts newItem into this IntegerList at position index
   // pre: 1 <= index <= size()+1
   // post: !isEmpty(), items to the right of newItem are renumbered
   public void insert(String key, String value) throws DuplicateKeyException{
      if (lookup(key) != null){
         throw new DuplicateKeyException( "cannot insert duplicate keys ");
      }
      if(head == null){
         Node N = new Node(key, value);
         N.next = head;
         head = N;
	 numItems++;                                     // new addition to increment size of array when you add first one
      }else{ // adds to the back of the list
         Node N = head;
         while( N != null){ // while the reference value is not empty
          if(N.next == null){  // check if the next one is empty. if it is empty break
            break;
          }
          N = N.next; // will keep going to Next key until there is an empty node 
        }
        N.next = new Node(key,value); // places new key and value
        numItems++; // increments up numitems by one since value is added
      }
    }
 

   // delete()
   // deletes item at position index from this IntegerList
   // pre: 1 <= index <= size()
   // post: items to the right of deleted item are renumbered
  public void delete(String key) throws KeyNotFoundException{
    if( lookup(key) == null){ //if we are expected to delete a key that does not exist
      throw new KeyNotFoundException("cannot delete non-existent key");
    }
    else{ // special case to delete an emoty or list with one element
      if(numItems <= 1){//removes first element
        makeEmpty();
      }
      else{//removes the first and another element
        Node N = head; // reference points to head's first variable
        if(N.key.equals(key)){ // if the reference variable's key is equal to the key we are looking for
          head = N.next; // make the new head value equal to the next element on the list skipping over the one we want to delete
          numItems--; // decrease the number of items by one
        }
        else{
          while(!N.next.key.equals(key)){ // if the reference variables key is not equal to the key we are looking for 
            N = N.next; // make the next value the new reference variable
          }
          N.next = N.next.next; //make the next item equal to the item down two values
          numItems--; //decrease the number of items
        }
      }
    }
 } 


   // makeEmpty()
   // pre: none
   // post: isEmpty()
   public void makeEmpty(){
      head = null;
      numItems = 0;
   }


   // toString
   // pre: none
   // post:  prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
     StringBuffer sb = new StringBuffer();
     Node N = head;
      for( ; N!=null; N=N.next){
         sb.append(N.key).append(" ").append(N.value).append("\n");
      }
      return new String(sb);
   }
}

