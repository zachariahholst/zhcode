/* Zach Holst
*  CS2060.002
*  Pointer Functions 2
*  Due 3/12/2020
*  This program uses pointer functions to create 
*  a simple calculator.  It prompts the user
*  to select what type of operation they want to
*  execute, and input two operands to do the 
*  operation on.  Operation functionss aren't
*  specifically called, rather the pointer function
*  array does the work to know which function to call
*  based off of user input.
*/
#include <stdio.h>

void welcome(void);
void calcMenu(void);
double add(double, double);
double subtract(double, double);
double multiply(double, double);
double devide(double, double);

int main(int argc, char * argv[]) {
	
	int choice = 0;
	double operand1 = 0;
	double operand2 = 0;
	double result = 0;
	
	//add all functions to pointer function array
	double (*operation[])(double, double) = {add, subtract, 
											 multiply, devide};

	//call methods to print welcome message and menu
	welcome();
	calcMenu();
	
	//request user which operation they wish to make
	printf(" Enter menu choice > " );
	scanf("%d", &choice);	
	
	//loop until user enters 0 to exit program
	while (choice != 0) {
		
		//handle invalid inputs for menu choices
		if(choice > 4 || choice < 0) {
			printf("%s%d%s\n", " ", choice, " is an invalid menu choice:" 
			" please try again");
			printf(" Enter menu choice > " );
			scanf("%d", &choice);	
		}
		//menu choice valid. ask user for value of both operands and 
		//perform necessary operation based on menu choice
		//once operation complete, print result, and prompt user for 
		//another menu choice
		else {
			//request user to enter operand values
			printf(" Enter value of first operand > " );
			scanf("%lf", &operand1);
			printf(" Enter value of second operand > " );
			scanf("%lf", &operand2);
			
			//equate result based off which operation in function 
			//pointer array is called
			result = (operation[choice-1])(operand1, operand2);
			printf("\n%s%.2f\n\n", " The result of the calculation is: " 
								 , result);
			
			//prompt the user to select a new operation
			printf("%s\n" , " Please select another operation" 
							"or enter 0 to exit");
			calcMenu();
			printf(" Enter menu choice > " );
			scanf("%d", &choice);	
		}	
	}
	return 0;
}

//this function prints welcome message for program
void welcome() {
	printf("%s", " Welcome to Zach's simple calculator program\n"
					"\tThis program asks the user for two operands\n"
					"\tand then asks which type of operation\n"
					"\tthe user wishes to perform.\n"
					"\tLoops until the user wishes to exit" 
					"with input of 0.\n\n");
}

//this function prints calculator menu for each loop
void calcMenu() {
	printf("%-4s%s\n", 	   " 0", "exit");
	printf("%-4s%-9s%s\n", " 1", "add", "(operand 1) + (operand2)");
	printf("%-4s%-9s%s\n", " 2", "subtract", "(operand 1) - (operand2)");
	printf("%-4s%-9s%s\n", " 3", "multiply", "(operand 1) * (operand2)");
	printf("%-4s%-9s%s\n\n", " 4", "devide", "(operand 1) / (operand2)");
}

//These are all necessary calculation functions
//Addition, subtraction, multiplicatoin, and devision
double add(double operand1, double operand2) {
	printf("\n%s%.2f%s%.2f\n", " ", operand1, " + ", operand2);
	return operand1 + operand2;
}

double subtract(double operand1, double operand2) {
	printf("\n%s%.2f%s%.2f\n", " ", operand1, " - ", operand2);
	return operand1 - operand2;
}

double multiply(double operand1, double operand2) {
	printf("\n%s%.2f%s%.2f\n", " ", operand1, " * ", operand2);
	return operand1 * operand2;
}

double devide(double operand1, double operand2) {
	//prevents deviding by zero from messing up the code
	//if devision by zero would happen, returns 0.
	if(operand2 == 0) {
		printf("\n%s%.2f%s%.2f\n", " ", operand1, " / ", operand2);
		return 0;
	}
	else {
			printf("\n%s%.2f%s%.2f\n", " ", operand1, " / ", operand2);
			return operand1/operand2;
	}
}
