//-----------------------------------------------------------------------------
//   IntegerStack.c
//   Shridhik John
//   shjohn@ucsc.edu
//   Lab5
//   Implementation file for Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// DictionaryObj
 struct DictionaryObj{
    Node top;
    Node bottom;
       int numItems;
       }DictionaryObj;

// newNode()
// constructor of the Node type
Node newNode(char* x, char* y) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = x;
   N->value = y;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}


// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Stack type
Dictionary newDictionary(void){
   Dictionary S = malloc(sizeof(DictionaryObj));
   assert(S!=NULL);
   S->top = NULL;
   S->bottom = NULL; 
   S->numItems = 0;//initial numitems
   return S;
}

// freeDictionary()
// destructor for the Stack type
void freeDictionary(Dictionary* pS){ //points to the address if memory for Dictionary
   if( pS!=NULL && *pS!=NULL ){ // conditions to empty address
      if( !isEmpty(*pS) ) makeEmpty(*pS);
      free(*pS);
      *pS = NULL;
   }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary S){
   if( S==NULL ){
      fprintf(stderr, 
              "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(S->numItems==0); //will return the necessary boolean
}

// size()
// returns the number of(key,value) pairs in D
// pre: none
int size(Dictionary S){
	return(S->numItems); //size given by numitems
}

// insert()
// pushes x onto top of S
// pre: none
void insert(Dictionary S, char* x, char* y){
      if( lookup(S, x) != NULL){ //error message if a key already exists
fprintf(stderr, "Dictionary Error: cannot insert duplicate key");}
      else{
      if(S->numItems == 0){ // inserting the first item if none exists
         S->top = S->bottom = newNode(x, y);
      }else{ //default statement to add an item and value
         Node N;
         N = newNode(x, y);
         S->bottom->next = N;
         S->bottom = N;
       }
       S->numItems++;
   }
}

// delete()
// deletes and returns item at top of S
// pre: !isEmpty(S)
void delete(Dictionary S,char* x){
    Node N = S->top;
    if(S==NULL){ //error printed if we're trying to delete something in an empty dictionary
        fprintf(stderr, "Stack Error: calling delete() on NULL StackRef\n");
	exit(EXIT_FAILURE);
	       }
    else if(lookup(S,x)==NULL){ // if the key doesnt exist, error message is printed
        fprintf(stderr, "Stack Error: deleting non-existant key\n");
	exit(EXIT_FAILURE);
	                      }
    else{
        if(S->numItems<=1){; // if there are no items, clear memory
	    freeNode(&N);
	}
    else{
	if(N->key==x){       // remove key if found
	    Node temp = S->top; 
	    S->top = N->next;
	    S->numItems--; // decrement items
	    freeNode(&temp); // free memory
		     }
	else{              // find the key by traversing through list
	    while(N->next->key!=x){
	        N = N->next;
				  }
	    Node temp =  N->next;
	    N->next = N->next->next;
	    freeNode(&temp);
	    S->numItems--;
	    }	
	}
    }
}


// lookup()
// returns item on top of S
// pre: !isEmpty(S)
char* lookup(Dictionary S, char* x){
     Node N = S->top;
       if( S == NULL ){ // if lookup is called on an empty Dictionary
         fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary\n");
         exit(EXIT_FAILURE);
      }
      while(N != NULL){
         if(strcmp(N->key,x)== 0) // Strings are comparyed and if the N's key is equal to target, value is returned

            return N->value;
         N = N->next; //else we check the next key/value
      }
         return NULL; // if it is not found
      }


// makeEmpty()
// resets S to the empty state
// pre: !isEmpty(S)
void makeEmpty(Dictionary S){
   if( S==NULL ){
      fprintf(stderr, 
              "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");}
	else{
	S->numItems=0;
        S->top = NULL;
        S->bottom = NULL;
       	freeNode(&S->top);
   }
}
// printDictionary()
// prints a text representation of S to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary S){
	Node N;
        if( S==NULL ){
           fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
           exit(EXIT_FAILURE);
      }
      for(N=S->top; N!=NULL; N=N->next) fprintf(out, "%s %s \n" , N->key, N->value);
      fprintf(out, "\n");
   }
