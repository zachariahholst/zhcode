/* Zach Holst
*  CS2060.002
*  Stack using Pointers 
*  Due 3/20/2020
*  This program uses pointer arithmetic to create a stack,
*  using an array to represnt the stack of floats and a 
*  pointer to point to the top element of the array.  this
*  is the only value that keeps track of the elements in the
*  stack.  Makes use of a pointer function array that points
*  to a push() method (add a value to top of stack), pop() 
*  method (prints and removes top value of array) and 
*  unwind() method (calls pop() until stack empty)
*  prevents adding values to stack if full or removing
*  from stack if empty.  
*/

#include <stdio.h>
#define SIZE 5

void welcome(void);
void menu(void);
float * push(float *);
float * pop(float *);
float * unwind(float *);

float stack[SIZE] = {0};

int main(int argc, char * argv[]) {
	
	int choice = 0;
	float * top = NULL;
	top = stack;
	
	float*(* functions[])(float*) = {push, pop, unwind};
	
	welcome();
	menu();
	
	//request user which menu option they wish to execute
	printf(" Enter menu choice > " );
	scanf("%d", &choice);	

	while(choice != 0) {	
			if(choice > 2 || choice < 0) {
				
			printf("%s%d%s\n", " ", choice, " is an invalid menu choice:" 
			" please try again");
			
			printf(" Enter menu choice > " );
			scanf("%d", &choice);	
		}
		
		else {
		//menu option is valid, perform chosen function
		//functions are called from function pointer array
		top = (functions[choice -1])(top);
		
		//request user for next menu choice and reprint menu
		menu();
		printf(" Enter menu choice > " );
		scanf("%d", &choice);
		}
	}	
	(functions[2])(top);
	return 0;
}

//this function prints a welcome message for the program
void welcome() {
	printf("%s", " Welcome to Zach's Stack using pointers program\n"
					"\tThis program asks the user if they \n"
					"\twish to push, pop, or unwind a stack.\n"
					"\tpush adds a value, given by user, " 
					"to the top of the stack.\n"
					"\tpop removes the value at the top"
					" of the stack and prints the value. "
					"\n\tLoops until the user wishes to exit" 
					" with the unwind function, which calls\n"
					" \tthe pop function until the stack is empty.\n");
}

//this function prints the user menu for the program
//menu format given by the wonderful professor Pam
void menu() {
	printf("%s", "\n"
			     " 0	exit(and unwind stack)\n"
		  		 " 1	push value onto stack\n"
		         " 2	pop value off stack\n\n");
}

//this function pushes a value to the top of a stack
float * push(float * top) {
	
	float valueToPush = 0;
	
	if(top < stack + SIZE) {	
		printf("\n Enter value to push to stack > " );
		scanf("%f", &valueToPush);
		
		*top = valueToPush;
		top++;
	}
	else {
		printf("%s", "\nStack is full, cannot push another value to stack\n");
	}	
	return top;
}

//this function removes the top number of the stack and prints
//it out.
float * pop(float * top){
	if(top > stack) {
		top--;
		printf("%s%.2f%s", "\n Value removed from stack: ", *top, "\n");
	}
	else {
		printf("%s", "\nStack is empty, no value to pop.\n");
	}
	return top;
}

//this function loops through the entire stack, calling
//the pop() function until empty, printing all values
float * unwind(float * top) {
	while(top > stack) {	
		top = pop(top);
	}
	printf("%s", "\nStack is now empty.  Thank you!");
	return top;
}
