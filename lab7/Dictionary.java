//---:--------------------------------------------------------------------------
// Dictionary.java
// lab7
// Shjohn@ucsc.edu
// Shridhik John
// Binary Search Tree implementation of the Dictionary ADT
//-----------------------------------------------------------------------------

// private types and functions ------------------------------------------------

public class Dictionary implements DictionaryInterface{


 // private inner Node class
    private class Node {
      String key;
      String value;
      Node left;
      Node right;
 
    Node(String k, String v){
      this.key = k;
      this.value = v;
      left = null;
      right = null;} 
    }
  
    // Fields for the IntegerList class
    Node root;     // reference to first Node in List
    int numPairs;  // number of items in this IntegerList
   
    // Dictionary()
    // constructor for the IntegerList class
    public Dictionary(){
       root = null;
       numPairs = 0;
    }
   
                                                                                                  
// returns the Node containing key k in the subtree rooted at R, or returns
// NULL if no such Node exists
private Node findKey(Node R, String k){
   if(R==null || k.compareTo(R.key)==0) 
      return R;
   if( k.compareTo(R.key)<0 ) 
      return findKey(R.left, k);
   else // k.compareTo(R.key)>0
      return findKey(R.right, k);
}


// findParent()
// returns the parent of N in the subtree rooted at R, or returns NULL 
// if N is equal to R. (pre: R != NULL)
private Node findParent(Node N, Node R){
   Node P=null;
   if( N!=R ){
      P = R;
      while( P.left!=N && P.right!=N ){
         if((N.key.compareTo(P.key))<0)
            P = P.left;
         else
            P = P.right;
      }
   }
   return P;
}

// findLeftmost()
// returns the leftmost Node in the subtree rooted at R, or NULL if R is NULL.
private Node findLeftmost(Node R){
   Node L = R;
   if( L!=null ) for( ; L.left!=null; L=L.left) ;
   return L;
}

// printInOrder()
// prints the (key, value) pairs belonging to the subtree rooted at R in order
// of increasing keys to file pointed to by out.
private String printInOrder( Node R){
    String s ="";
    if( R!=null ){
      s = printInOrder(R.left) + R.key + " " + R.value + "\n" +printInOrder(R.right);
   }
    return s;
}

// deleteAll()
// deletes all Nodes in the subtree rooted at N.
private void deleteAll(Node N){
    root = null;
    numPairs = 0;
}

// public functions -----------------------------------------------------------

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
public boolean isEmpty(){
   return(numPairs==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
public int size(){
    return numPairs;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
public String lookup( String k){
   Node N;
   N = findKey(this.root, k);
   return ( N==null ? null : N.value );
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
public void insert( String k, String v) throws DuplicateKeyException{
   Node N, A, B;
   if( findKey(this.root, k)!=null ){
      throw new DuplicateKeyException("Cannot insert() duplicate keys");   
}
   N = new Node(k, v);
   B = null;
   A = this.root;
   while( A!=null ){
      B = A;
      if(( k.compareTo(A.key))<0)  A = A.left;
      else A = A.right;
   }
   if( B==null ) this.root = N;
   else if( k.compareTo(B.key)<0 ) B.left = N;
   else B.right = N;
   this.numPairs++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
public void delete(String k)throws KeyNotFoundException{
   Node N, P, S;
   N = findKey(this.root, k);
   if( N==null ){
      throw new KeyNotFoundException("Cannot delete() non-existant key");
}
   if( N.left==null && N.right==null ){  // case 1 (no children)
      if( N==this.root ){
         this.root = null;
      }else{
         P = findParent(N, this.root);
         if( P.right==N ) P.right = null;
         else P.left = null;
      }
   }else if( N.right==null ){  // case 2 (left but no right child)
      if( N==this.root ){
         this.root = N.left;
      }else{
         P = findParent(N, this.root);
         if( P.right==N ) P.right = N.left;
         else P.left = N.left;
      }
   }else if( N.left==null ){  // case 2 (right but no left child)
      if( N==this.root ){
         this.root = N.right;
      }else{
         P = findParent(N, this.root);
         if( P.right==N ) P.right = N.right;
         else P.left = N.right;
      }
   }else{                     // case3: (two children: N.left!=NULL && N.right!=NULL)
      S = findLeftmost(N.right);
      N.key = S.key;
      N.value = S.value;
      P = findParent(S, N);
      if( P.right==S ) P.right = S.right;
      else P.left = S.right;
   }
   this.numPairs--;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
public void makeEmpty(){
   deleteAll(this.root);
   this.root = null;
   this.numPairs = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
public String toString(){
	return printInOrder(root);
}
}
