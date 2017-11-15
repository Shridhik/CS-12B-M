//////////////////////////
// DictionaryTest
// pa5
// Shridhik John
// shjohn@ucsc.edu
// a test file for PA5's Dictionary
/////////////////////////

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180
/*
int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   char* k;
   char* v;
   char* word1[] = {"one","two","three","four","five","six","seven"};
   char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
   int i;

   for(i=0; i<7; i++){
      insert(A, word1[i], word2[i]);
   }

   printDictionary(stdout, A);

   for(i=0; i<7; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }

   // insert(A, "five", "glow");

delete(A, "one");
   delete(A, "three");
   delete(A, "seven");

   printDictionary(stdout, A);

   for(i=0; i<7; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }

   // delete(A, "one"); 
   
   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%d\n", size(A));
   makeEmpty(A);
   printf("%s\n", (isEmpty(A)?"true":"false"));

   freeDictionary(&A);

   return(EXIT_SUCCESS);
}*/

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   FILE* in = fopen("DictionaryClient2.c", "r");
   FILE* out = fopen("DictionaryClient2-out", "w");
   char* key;
   char* value;
   char* keyBuffer = NULL;
   char* valBuffer = NULL;
   int keyBufferOffset = 0, valBufferOffset = 0;
   int keyBufferLength = 0, valBufferLength = 0;
   char line[MAX_LEN+1];
   char label[MAX_LEN+1];
   int i, labelLength, lineLength, lineNumber = 0;

   while( fgets(line, MAX_LEN, in)!=NULL ){


      lineNumber++;
      lineLength = strlen(line)-1;
      line[lineLength] = '\0';  // overwrite newline '\n' with null '\0'
      valBufferLength += (lineLength+1);
      valBuffer = realloc(valBuffer, valBufferLength*sizeof(char) );
      value = &valBuffer[valBufferOffset];
      strcpy(value, line);
      valBufferOffset = valBufferLength;
      sprintf(label, "line %d:\t", lineNumber);
      labelLength = strlen(label);
      keyBufferLength += (labelLength+1);
      keyBuffer = realloc(keyBuffer, keyBufferLength*sizeof(char) );
      key = &keyBuffer[keyBufferOffset];
      strcpy(key, label);
      keyBufferOffset = keyBufferLength;
   }


   keyBufferOffset = valBufferOffset = 0;
   for(i=0; i<lineNumber; i++){
      key = &keyBuffer[keyBufferOffset];
      value = &valBuffer[valBufferOffset];
      insert(A, key, value);
      keyBufferOffset += (strlen(key) + 1);
      valBufferOffset += (strlen(value) + 1);
   } 

   printDictionary(out, A);



   // free memory and close files
      freeDictionary(&A);
   free(keyBuffer);
   free(valBuffer);
   fclose(in);
   fclose(out);

   return(EXIT_SUCCESS);
}
