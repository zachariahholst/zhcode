/* Zach Holst
*  CS 2060.002
*  File IO Programming Assignment 6
*  Due 5/7/2020
*  This program uses reading and append functions
*  to read and write to a file. The user will input
*  the name of the file they wish to manipulate.  
*  the program will check for validity of the file 
*  before any sort of manipulation to the file
*  The program will first print out the file,
*  then ask the user to input 3 strings for 3 lines
*  of the file, and then print the file for a final time.
*  has a Max line length of 1024 characters
*  Lots of the used code was inspired by the great professor Pam
*/

#include <stdio.h>
#include <stdlib.h>
#define MAX 1024

int main(int argc, char * argv[]) {
	
	//create a FILE pointer for the file
	FILE * filePtr = NULL;
	char fileName[50] = "0";
	char userInputString[50] = "0";
	char * line = NULL;
	char * endOfFile = NULL;
	char trash = '\0';
	
	//allocate memort for the line pointer
	line = (char *)malloc(sizeof(char)* MAX);
	
	//request file name from the user
	printf(" what is the file\'s name > ");
	scanf("%s", fileName);
	
	//check if the file exists or not
	if((filePtr = fopen(fileName, "r")) == NULL) {
		
		printf("%s", " does not exist\n\n", fileName);
	}
	//if the file exists, the program will read the information
	//in the file, using a while loop until endOfFile is equal to NULL
	else {
		
		endOfFile = fgets(line, MAX, filePtr);
		
			while(endOfFile != NULL) {
				
				printf(" %s", line);
				endOfFile = fgets(line, MAX, filePtr);
			}
			//done reading file, fclose the file pointer
			printf("\n Done Reading File\n\n");
			fclose(filePtr);
		}
	
	//check if the file can be open
	if((filePtr = fopen(fileName, "a")) == NULL) {
		
		printf("%s ERROR opening file \n\n", fileName);
	}
	
	//file can be open
	else {
		
		//throw the scanf to the trash to remove the character
		//left in the buffer
		scanf("%c", &trash);
		
		//ask user for 3 string inputs, using a for loop
		for(int i = 0; i < 3; i++) {
		
			printf(" Input a string to the file > ");
			scanf("c", &userInputString);
			fgets(line, MAX, stdin);
			fprintf(filePtr, "%s", line);
		}
		
		//done writing to file, close the file pointer
		fclose(filePtr);
		printf("\n Done writing to File\n");
	}
	
	//check if the file exists
	if((filePtr = fopen(fileName, "r")) == NULL) {
		
		printf("%s does not exist\n\n", fileName);
	}
	
	//file exists.  go through the file until 
	//endOfFile equals NULL and print each line 
	//in the file
	else {
		
		endOfFile = fgets(line, MAX, filePtr);
		printf("%s", line);
		
		while(endOfFile != NULL) {
			
			printf(" %s", line);
			endOfFile = fgets(line, MAX, filePtr);
		}
		
		//file is done reading, close file
		printf("\n Done Reading File\n");
		fclose(filePtr);
	}
	 
	 //end program, free malloc() memory from line pointer
	printf("\n Program is done, have a great day!");
	free(line);
	return 0;
}
