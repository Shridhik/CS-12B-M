//-----------------------------------------------------------------------------
// Dictionary.c
// Shridhik John
// shjohn@unix.ucsc.edu
// Pa5
// Hash Table implementation of the Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


const int tableSize=101; // or some prime other than 101


// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
 int sizeInBits = 8*sizeof(unsigned int);
 shift = shift & (sizeInBits - 1);
 if ( shift == 0 )
 return value;
 return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
 unsigned int result = 0xBAE86554;
 while (*input) {
 result ^= *input++;
 result = rotate_left(result, 5);
 }
 return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
 return pre_hash(key)%tableSize;
}

// private types and functions ------------------------------------------------

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor for private Node type
Node newNode(char* k, char* v) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = calloc(strlen(k)+1, sizeof(char));
   strcpy(N->key,k);
   N->value = calloc(strlen(v)+1, sizeof(char));
   strcpy(N->value,v);
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for private Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      Node N = *pN;
      free(*pN);
      *pN = NULL;
   }
}

// DictionaryObj
typedef struct DictionaryObj{
   Node*  table;
   int numPairs;
} DictionaryObj;


// findKey()
// returns the Node containing key k in the subtree rooted at R, or returns
// NULL if no such Node exists
Node findKey(Dictionary D, char* k){
   Node N =  D->table[hash(k)];
       while(N != NULL && strcmp(N->key, k) != 0) {
            N = N->next;
}
	   return N;
}


// findParent()
// returns the parent of N in the subtree rooted at R, or returns NULL 
// if N is equal to R. (pre: R != NULL)
Node findParent(Dictionary D, char* k){
   Node N = D->table[hash(k)];
       while(N->next != NULL && strcmp(N->next->key, k) != 0) {
            N = N->next;
}
           return N;
}




// deleteAll()
// deletes all Nodes in the subtree rooted at N.
void deleteAll(Node N){
   if( N!=NULL ){
      deleteAll(N->next);
      freeNode(&N);
   }
}

// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->table = calloc(tableSize, sizeof(Node*));
   D->numPairs = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL ){
      makeEmpty(*pD);
      free(*pD);
      *pD = NULL;
   }
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numPairs==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling size() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numPairs);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   Node N;
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   N = findKey(D, k);
   return ( N==NULL ? NULL : N->value );
}


// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   Node N;
   int tblidx = hash(k);
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numPairs > 0 && lookup(D, k) != NULL){
      fprintf(stderr, "Dictionary: insert() cannot insert duplicate keys: \"%s\"\n", k);
      exit(EXIT_FAILURE);
   }
   else if(D->table[tblidx] == NULL){
      N = newNode(k, v);
      D->table[tblidx] = N;
   }
   else{
      Node N = D->table[tblidx];
      while(N->next != NULL) N = N->next;
       N = newNode(k,v);
   }
   D->numPairs++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   int tblidx = hash(k);
   Node N = findKey(D, k);
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numPairs==0 ){
      fprintf(stderr, "Dictionary Error: calling delete() on empty Dictionary\n");
	  exit(EXIT_FAILURE);
   }
   if(D->table[tblidx] != NULL && D->table[tblidx]->next == NULL){
      D->table[tblidx] = NULL;
   }
   else if(lookup(D,k) != NULL && N == D->table[tblidx]){
      D->table[tblidx] = N->next;
   }
   else{
      Node P = findParent(D, k);
      P->next = N->next;
   }
   freeNode(&N);
   D->numPairs--;
}




// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
int a=0;
while (a < tableSize){
   deleteAll(D->table[a]);
   D->table[a]=NULL;
   a++;
}
   D->numPairs = 0;
 
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   int a=0;
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling printDictionary() on NULL"
         " Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   else{
 	while(a < tableSize){
   		if(D->table[a] != NULL){
                        Node N = D->table[a];
                        while (N!=NULL){   
 				fprintf(out, "%s %s\n", N->key, N->value);
                                N=N->next;
			}
   		}
	a++;
 	}
   }
}
