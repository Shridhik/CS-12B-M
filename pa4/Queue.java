//-----------------------------------------------------------------------------
// Queue.java
// Shridhik John
// shjohn@ucsc.edu
// pa4
// Queue ADT
//-----------------------------------------------------------------------------


public class Queue implements QueueInterface{

   private class Node{
      Object item;
      Node next;
      Node(Object item){
         this.item = item;
         this.next = null;
      }
   }

   private int numItems;      // number of items in this IntegerQueue
   private Node front;         // index of front element
   private Node back;          // index of back element

   //constructor
   public void Queue(){
	numItems = 0;
	front = null;
	back = null;
}

   // isEmpty()
   // pre: none
   // post: returns true if this Queue is empty, false otherwise
   public boolean isEmpty(){
	return (numItems == 0);
	}
   // length()
   // pre: none
   // post: returns the length of this Queue.
   public int length(){
	return (numItems);
	}
   // enqueue()
   // adds newItem to back of this Queue
   // pre: none
   // post: !isEmpty()
   public void enqueue(Object newItem){
      if( front == null){
        Node N = new Node(newItem);
        front = N;
        numItems++;
      }
      else{
        Node N = front;
        while( N != null){
          if(N.next == null){
            break;
          }
          N = N.next;
        }
        N.next = new Node(newItem);
        back = N.next;
        numItems++;
      }
    }
   // dequeue()
   // deletes and returns item from front of this Queue
   // pre: !isEmpty()
   // post: this Queue will have one fewer element
   public Object dequeue() throws QueueEmptyException{
      if(isEmpty()){  // could also check top==null
         throw new QueueEmptyException("Queue error: cannot dequeue() empty queue");
      }
      Object topitem = peek();
      front = front.next;
      numItems--;
      return topitem;
   }
   // peek()
   // pre: !isEmpty()
   // post: returns item at front of Queue
   public Object peek() throws QueueEmptyException{
      if(isEmpty()){  // could also check top==null
         throw new QueueEmptyException("Queue error: cannot peek() empty queue");
      }
      return front.item;
      }

   // dequeueAll()
   // sets this Queue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() throws QueueEmptyException{
      if( numItems==0 ){  // could also check top==null
         throw new QueueEmptyException("Queue error: cannot dequeueAll() empty Queue");
      }
      front = null;
      back = null;
      numItems = 0;
   }

   // toString()
   // overrides Object's toString() method
   public String toString(){
      StringBuffer sb = new StringBuffer();
      for(Node N = front; N!=null; N=N.next){
         sb.append(N.item).append(" ");
      }
      return new String(sb);
   }
}

