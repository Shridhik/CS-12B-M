//-----------------------------------------------------------------------------
// QueueTest.java
// Shridhik John
// shjohn@ucsc.edu
// Pa4
// Test Client for the Queue class
//-----------------------------------------------------------------------------

public class QueueTest {

   public static void main(String[] args){
      Queue A = new Queue();
      System.out.println(A.isEmpty());
      A.enqueue(5); A.enqueue(3); A.enqueue(9); A.enqueue(7); A.enqueue(8);
      System.out.println(A.isEmpty());
      System.out.println(A);
      System.out.println(A.peek());
      A.dequeue(); A.dequeue(); A.dequeue();
      System.out.println(A.peek());
      System.out.println(A);
      Queue B = new Queue();
      System.out.println(A.isEmpty());
      System.out.println(B.isEmpty());
      B.enqueue(7); B.enqueue(8);
      System.out.println(A.length());
      System.out.println(B.length());
      A.enqueue(12);
      B.enqueue(13);
      System.out.println(A);
      System.out.println(B);
      System.out.println(A.length());
      System.out.println(B.length());
      A.dequeueAll();
      System.out.println(A);
      System.out.println(A.isEmpty());
      System.out.println(A.length());
      System.out.println(B.length());
      //System.out.println(A.peek());
      System.out.println(B.peek());
      //System.out.println(A.dequeue());
      A.dequeueAll();
   
}
}
