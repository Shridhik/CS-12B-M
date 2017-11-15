//-----------------------------------------------------------------------------
// Search.java
// Shridhk John
// shjohn@ucsc.edu
// Pa2
// 7/9/17
// determines whether or not the target word is amongst the words in the input file, 
//      prints a message to stdout stating whether or not the target was found, AND 
//      states the line on which the target word was found
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Search {

   // mergeSort()
   // sort subarray A[p...r]
   public static void mergeSort(String[] word,int[] lineNumber, int p, int r){
      int q;
      if(p < r) {
         q = (p+r)/2;
         // System.out.println(p+" "+q+" "+r);
         mergeSort(word,lineNumber, p, q);
         mergeSort(word,lineNumber, q+1, r);
         merge(word,lineNumber, p, q, r);
      }
   }

   // merge()
   // merges sorted subarrays A[p..q] and A[q+1..r]
   public static void merge(String[] word,int[] lineNumber, int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
      String[] L = new String[n1];
      String[] R = new String[n2];
      int[] Ln = new int[n1];
      int[] Rn = new int[n2];
      int i, j, k;

      for(i=0; i<n1; i++){
         L[i] = word[p+i];
         Ln[i] = lineNumber[p+i];
      }
      for(j=0; j<n2; j++){ 
         R[j] = word[q+j+1];
         Rn[j] = lineNumber[q+j+1];
      }

      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<n1 && j<n2 ){
	    // using mergeSort's merge method from examples however using the compareTo function to compare strings
            if( L[i].compareTo(R[j])<0 ){
               word[k] = L[i];
               lineNumber[k] = Ln[i];
               i++;
            }else{
               word[k] = R[j];
               lineNumber[k] = Rn[j];
               j++;
            }
         }else if( i<n1 ){
            word[k] = L[i];
	    lineNumber[k] = Ln[i];
            i++;
         }else{ // j<n2
            word[k] = R[j];
            lineNumber[k] = Rn[j];
	    j++;
         }
      }
   }

   public static void main(String[] args) throws IOException {
        Scanner in= null;
        int lineCount = 0;
        int lC = 0;
        String word = null;
        String[] B = null;
        int[] C = null;
        String targ = null;
	// makes sure there are more than one arguments, otherwise the given error will print
	if(args.length < 2){
         System.err.println("Usage: Search file target1 [target2 ..] ");
         System.exit(1);
      			   }

        // count the number of lines in the input file
      	in = new Scanner(new File(args[0]));
        while( in.hasNextLine() ){
                word = in.nextLine();
		lineCount++;
        }
        //initializing String array B and integer array C with no of lines(lineCount) in file as array size
        B = new String[lineCount];
        C = new int[lineCount];
	// allows the file to be reread
        in = new Scanner(new File(args[0]));
        // each line is recorded as an element in array B(word) and the each word is assigned a number starting from 1 in array C    
      	while( in.hasNextLine() ){ 
           word = in.nextLine();
           B[lC] = word;
           C[lC] = lC+1;
      	   lC++;
	}
      // a for loop that executes the MergeSort and Binary Search for each target (args 1 through n) to be executed and printed
      for(int k=1; k< args.length; k++)
	{

	targ = args[k];	

    //  String[] B = {"entire", "beginning", "possibly", "specified","key","value","initial","before","dictionary","however"};
    //  int [] C = {1,2,3,4,5,6,7,8,9,10};
      mergeSort(B,C, 0, B.length-1);
      //for(int i=0; i<B.length; i++) 
      //   System.out.print(B[i]+" ");
      //System.out.println();

      // BinarySearch
      //System.out.println(binarySearch(B,C, 0, B.length-1, "initial"));
     binarySearch(B,C, 0, B.length-1, targ);
      }
     in.close();
   }




   static int binarySearch(String[] A, int[] C, int p, int r, String target){
      int q;
      // if the word is not found on the list after binary search, it cannot be found
      if(p > r) {
         System.out.println(target + " not found");
         return -1; 
      } 
     else{
         q = (p+r)/2;
	 // checks if the target is equivalent to the word in the array (not Case sensetive) 
         if(target.equalsIgnoreCase(A[q])){
	    // main print statement
            System.out.println(target + " found on line " + C[q]);
	    return -1;
         }else if(target.compareTo(A[q])<0){
            return binarySearch(A, C, p, q-1, target);
         }else{ // target > A[q]
            return binarySearch(A, C, q+1, r, target);
         }
      }
   }

}
