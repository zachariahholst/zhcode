/* Zach Holst
*  CS2060.002
*  Ordered Linked List Assignment 4
*  Due 4/16/2020
*  This program uses nodes to create an ordered linked list.
*  It prompts the user to enter integer values greater than
*  zero until they wish to terminate the program by entering
*  zero or a negetive value.  The program creates a new node 
*  with each input, and inserts it into the list, using node
*  pointers to go through the list and determine the location
*  the new node should go. The linked list is ordered in 
*  ascending order.  
*  The list is printed out with a function before exiting the program.
*  malloc() is used to allocate memory for new
*  nodes and free() is used at the end of the program to free
*  all dynamically allocated nodes.  
*  a global listNode called header is used to point to the beginning
*  of the list to remove the need for special cases, and it is global
*  to remove the need of passing it into functions
*/

#include <stdio.h>
#include <stdlib.h>

//create listNode structure, with data and next operators
struct listNode{
	int data;
	struct listNode*next;
};

//define global header to point at beginning of listNode
struct listNode header = {0, NULL};

void welcome(void);
void menu(void);
void createNewNode(int);
void insertNode(struct listNode*);
void printList(void);
void freeNode(void);

int main(int argc, char * argv[]) {
	
	int input = 0;
	
	//call functions to print welcome and menu
	welcome();
	menu();
	
	//prompt user to enter first value
	printf(" Insert value into list > " );
	scanf("%d", &input);	
	
	while(input > 0) {
		
		createNewNode(input);
		
		printf(" Insert value into list > " );
		scanf("%d", &input);
		
	}
	//call functions to print the list of integers and free all nodes
	printList();
	freeNode();
	
	return 0;
}

//this function displays a welcome message and introduction of what the program does
void welcome() {
	printf("%s", " Welcome to Zach Holst's Ordered Linked List program!"
			     " \n\tThis program lets you input integer values greater than 0"
				 " \n\tinto an ascending ordered linked list."
				 " \n\tOnce the user terminates the program, the values"
				 " \n\tinside the linked list will be displayed in the console.");
}

//this function instructs the user on what to do in the program
void menu() {
	
	printf("\n\n%s", "\tPlease enter integer values greater than "
				     "\n\t0 to be inserted in the list."
				     "\n\tIf you wish to terminate the program, "
				 	 "\n\tenter an integer value equal to or less than 0.\n\n");
	
}

//this function creates a new node and inputs the data to it based on the user input
//calls the insertNode function within the function
void createNewNode(int input) {
	
	struct listNode *newNode = NULL;
	newNode = (struct listNode*)malloc(sizeof(struct listNode));
	newNode->data = input;
	insertNode(newNode);
}

//inserts new node into the list, sorts in ascending order
void insertNode(struct listNode *newNode) {
	
	struct listNode *current = NULL;
	current = &header;
	
	while((current->next != NULL) && (current->next->data < newNode->data)) {
		
		current = current->next;
	}
	newNode->next = current->next;
	current->next = newNode;
	
}

//prints the list of integers
void printList() {

	struct listNode *temp = &header;
	//to avoid printing header's value of 0, set temp to temp->next
	temp = temp->next;
	
	printf("\n\n%s", " Values in the listNode in Ascending order: ");
	while(temp != NULL) {
		
		printf("%d ", temp->data);
		temp = temp->next;
	}
	printf("\n\n%s" , " Exiting program.  Have a nice day!");
	
}

//frees all dynamically allocated memory nodes
void freeNode() {
	
	struct listNode *temp = &header;
	while(temp != NULL) {
		
		free(temp);
		temp = temp->next;
	}
}
