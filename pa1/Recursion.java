/*
 * Recursion.java
 * Shridhik John
 * shjohn@ucsc.edu
 * pa1
 * Three recursive methods that print out the array in reverse order, and two recursive functions that return the index of the max and min number of an array
 */
public class Recursion {


   // print out the reversal of the leftmost n elements in A using example code of ReverseArray1 from Reverse.java
   static void reverseArray1(int[] X, int n, int[] Y) {
      // if n==0 do nothing
      if(n > 0) { 
         Y[X.length-n]=X[n-1];

         reverseArray1(X, n-1, Y); 
      }
      
   }
   
   // returns the reversal of the rightmost n elements in A using the example code of ReverseArray2 from Reverse.java
   static void reverseArray2(int[] X, int n, int[] Y) {
       
      // if n==0 do nothing
      if( n>0 ){
         Y[n-1]= X[X.length-n];
         reverseArray2(X, n-1, Y);       // returns the rightmost n-1 elements, reversed
      }
   }
   
   static void reverseArray3(int[] X, int i, int j) { //using a temp variable we switch the first number in the array with the last and so on as we increase the left side and                                                               decrease the right side with an index of one
      
      if( i < j ){
         int Temp;
         Temp = X[i];
         X[i] = X[j];
         X[j] = Temp;
         i++;
         j--;
         reverseArray3(X, i++, j--);
      }
   }
   
public static int maxArrayIndex(int[] A, int i, int j) {
{
  // in recursive loop if i has reached the max index number(A.length) then we return j(A.length) as the index for max
  if(i == A.length)  
      return j;
  // Checks if each element of array A[i] is greater than last element of array A[j] and if so keep i as the max value for index else j as max value.
  // Run it recursively until all elements are run through 
  if(A[i] > A[j])
      return maxArrayIndex(A,i+1, i);
  else
      return maxArrayIndex(A,i+1, j);
}
}  

public static int minArrayIndex(int[] A, int i, int j) {
{
  // in recursive loop if i has reached the max index number(A.length) then we return j(A.length) as the index for min
  if(i == A.length) 
      return j;
  // Checks if each element of array A[i] is greater than last element of array A[j] and if so keep i as the max value for index else j as min value.
  // Run it recursively until all elements are run through 
  if(A[i] < A[j])
      return minArrayIndex(A,i+1, i);
  else
      return minArrayIndex(A,i+1, j);
}
}

   public static void main(String[] args) { //Main function that is given for specific output
        int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
        int[] B = new int[A.length];
        int[] C = new int[A.length];
        int minIndex = minArrayIndex(A, 0, A.length-1);
        int maxIndex = maxArrayIndex(A, 0, A.length-1);

        for(int x: A) System.out.print(x+" ");
        System.out.println();

        System.out.println( "minIndex = " + minIndex );
        System.out.println( "maxIndex = " + maxIndex );
        reverseArray1(A, A.length, B);
        for(int x: B) System.out.print(x+" ");
        System.out.println();

        reverseArray2(A, A.length, C);
        for(int x: C) System.out.print(x+" ");
        System.out.println();

        reverseArray3(A, 0, A.length-1);
        for(int x: A) System.out.print(x+" ");
        System.out.println(); 
    } 
   }
  

