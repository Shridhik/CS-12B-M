/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//charType.c
//Shridhik John
//shjohn@unix.ucsc.edu
//July/23/2017
//lab4
//takes two command line arguments giving the input and output file
//names respectively, then classifies the characters on each line of the input file into the following categories:
//alphabetic, numeric, punctuation, or white space.
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

#include<stdio.h>
#include<ctype.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#define MAX_STRING_LENGTH 100

// function prototype 
void extract_chars(char* s, char* a, char* d, char* p, char* w);

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   FILE* in;        // handle for input file                  
   FILE* out;       // handle for output file                 
   char* line;      // string holding input line              
   char* alpha; // string holding all alpha-numeric chars
   char* digit; // string holding all digit chars
   char* punct; // string holding all punctuation chars
   char* wspace; // string holding all white space chars
   int Lnum = 1;

   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
	if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and alpha_num on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   digit = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   punct = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   wspace = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   assert( line!=NULL && alpha!=NULL && digit!=NULL && punct!=NULL && wspace!=NULL );

   // read each line in input file, extract alpha-numeric characters 
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
      //extract_alpha_num(line, alpha_num);
      extract_chars(line, alpha, digit, punct, wspace);
      fprintf(out,"line %d contains:\n",Lnum);
      if (strlen(alpha) == 1){
      fprintf(out,"%zu alphabetic character: %s\n",strlen(alpha), alpha);
      }
      else{
      fprintf(out,"%zu alphabetic characters: %s\n",strlen(alpha), alpha);
      }
      if (strlen(digit) == 1){
      fprintf(out,"%zu numeric character: %s\n",strlen(digit), digit);
      }
      else{
      fprintf(out,"%zu numeric characters: %s\n",strlen(digit), digit);
      }
      if (strlen(punct) == 1){
      fprintf(out,"%zu punctuation character: %s\n", strlen(punct), punct);
      }
      else{
      fprintf(out,"%zu punctuation characters: %s\n", strlen(punct), punct);
      }
      if (strlen(wspace) == 1){
      fprintf(out,"%zu whitespace character:%s\n",strlen(wspace), wspace);
      }
      else{
      fprintf(out,"%zu whitespace characters:%s\n",strlen(wspace), wspace);
      }
      Lnum++;
   }

   // free heap memory 
   free(line);
   free(alpha);
   free(digit);
   free(punct);
   free(wspace);

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}

// function definition 
void extract_chars(char* s, char* a, char* d, char* p, char* w){
   int i=0, j=0, k=0, l=0, m=0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH){
	if( isalpha( (int) s[i]) ) a[j++] = s[i];
	if( isdigit( (int) s[i]) ) d[k++] = s[i];
        if( ispunct( (int) s[i]) ) p[l++] = s[i];
	if( isspace( (int) s[i]) ) w[m++] = s[i];
        i++;
   }
   a[j] = '\0';
   d[k] = '\0';
   p[l] = '\0';
   w[m] = '\0';
}

