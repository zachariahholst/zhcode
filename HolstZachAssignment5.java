/*
 * Zach Holst
 * CS 1450 Sec 001
 * Assignment 5
 * February 27, 2020
 * This assigment gives practice into generic methods
 * and classes, and using stacks.  It creates a generic
 * class that creates a generic stack, and then 2 integer
 * and 2 string classes must be created and manipulated 
 * with methods in the generic class.  
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HolstZachAssignment5 {

	public static void main(String[] args) throws IOException {

		//Create two generic stacks that hold integer values
		GenericStack<Integer> numStack1 = new GenericStack<>();
		GenericStack<Integer> numStack2 = new GenericStack<>();

		//create file and scanner object for first number file to add
		//to first numStack.  Do the same for the second numStack\
		//use while(.hasNext) to go through the file
		File numbers1 = new File("numbers1.txt");
		Scanner number1File = new Scanner(numbers1);

		while(number1File.hasNext()) {

			numStack1.push(number1File.nextInt());

		}

		File numbers2 = new File("numbers2.txt");
		Scanner number2File = new Scanner(numbers2);

		while(number2File.hasNext()) {

			numStack2.push(number2File.nextInt());

		}

		//print out both stacks, making sure they print in the order 
		//they are in the stack and the stack remains in its original state
		System.out.println("Numbers Stack 1 - filled with values from numbers1.txt");
		System.out.println("------------------------------------------------------");

		for(int count = numStack1.getSize()-1; count >= 0; count--) {

			numStack1.printStack(count);
			System.out.println("");

		}
		System.out.println("");
		System.out.println("Numbers Stack 2 - filled with values from numbers2.txt");
		System.out.println("------------------------------------------------------");

		for(int count = numStack2.getSize() -1; count >= 0; count--) {

			numStack2.printStack(count);
			System.out.println("");

		}

		//create new stack to merge the two number stacks
		GenericStack<Integer> mergedNumStack = new GenericStack<>();

		//call merge method
		mergeStacks(numStack1, numStack2, mergedNumStack);

		//create final merged stack to reverse previous stack
		//to print in the right order
		GenericStack<Integer> finalMergedNumStack = new GenericStack<>();

		//call method to reverse final stack
		reverseStack(mergedNumStack, finalMergedNumStack);

		//print final reversed merged stack
		System.out.println("");
		System.out.println("Merged Stack - lowest value on top");
		System.out.println("------------------------------------------------------");
		for(int count = finalMergedNumStack.getSize()-1; count >= 0; count--) {

			finalMergedNumStack.printStack(count);
			System.out.println("");

		}

		//Create two generic stacks that hold String values
		GenericStack<String> mountainStack1 = new GenericStack<>();
		GenericStack<String> mountainStack2 = new GenericStack<>();

		//create file and scanner object for first mountain file to add
		//to first mountainStack.  Do the same for the second mountainStack
		//use while(.hasNext) to go through the file
		File mountains1 = new File("mountains1.txt");
		Scanner mountains1File = new Scanner(mountains1);

		while(mountains1File.hasNext()) {

			mountainStack1.push(mountains1File.nextLine());


		}

		File mountains2 = new File("mountains2.txt");
		Scanner mountains2File = new Scanner(mountains2);

		while(mountains2File.hasNext()) {

			mountainStack2.push(mountains2File.nextLine());


		}

		//print out both stacks, making sure they print in the order 
		//they are in the stack and the stack remains in its original state
		System.out.println("");
		System.out.println("Mountains Stack 1 - filled with values from mountains1.txt");
		System.out.println("----------------------------------------------------------");

		for(int count = mountainStack1.getSize()-1; count >= 0; count--) {

			mountainStack1.printStack(count);
			System.out.println("");

		}
		System.out.println("");
		System.out.println("Mountains Stack 2 - filled with values from mountains2.txt");
		System.out.println("----------------------------------------------------------");

		for(int count = mountainStack2.getSize() -1; count >= 0; count--) {

			mountainStack2.printStack(count);
			System.out.println("");

		}

		//create new stack to merge mountain stacks
		GenericStack<String> mergedMountainStack = new GenericStack<>();

		//call method to merge mountain stacks together
		mergeStacks(mountainStack1, mountainStack2, mergedMountainStack);

		//create new stack to reverse the merged stack
		GenericStack<String> finalMergedMountainStack = new GenericStack<>();

		//call method to reverse merged stack
		reverseStack(mergedMountainStack, finalMergedMountainStack);

		//print final merged stack
		System.out.println("");
		System.out.println("Merged Stack - lowest value on top");
		System.out.println("------------------------------------------------------");
		for(int count = finalMergedMountainStack.getSize()-1; count >= 0; count--) {

			finalMergedMountainStack.printStack(count);
			System.out.println("");

		}

		//print the two merged stacks side by side 
		//the lowest value in each should be on top
		System.out.println("");
		System.out.println("Printing Merged Stacks side by side - lowest value on top");
		System.out.println("------------------------------------------------------------");
		System.out.println("Integers\tStrings");
		System.out.println("------------------------------------------------------------");

		printTwoStack(finalMergedNumStack, finalMergedMountainStack);

		System.out.println("");
		System.out.println("Number Stack top: " + finalMergedNumStack.peak());
		System.out.println("String stack top: " + finalMergedMountainStack.peak());

		number1File.close();
		number2File.close();
		mountains1File.close();
		mountains2File.close();

	}

	//this method takes two generic stacks and merges them into one generic stack,
	//using the compareTo method to handle any type of variable 
	public static <E extends Comparable<E>> void mergeStacks(GenericStack<E> stack1,
			GenericStack<E> stack2,
			GenericStack<E> mergedStack) {

		while(stack1.isEmpty() && stack2.isEmpty()) {

			if(stack1.peak().compareTo(stack2.peak()) < 0) {

				mergedStack.push(stack1.peak());
				stack1.pop();

			}

			else if(stack1.peak().compareTo(stack2.peak()) == 0) {

				mergedStack.push(stack1.peak());
				stack1.pop();
				mergedStack.push(stack2.peak());
				stack2.pop();

			}

			else if(stack1.peak().compareTo(stack2.peak()) > 0) {

				mergedStack.push(stack2.peak());
				stack2.pop();

			}
		}

		while(stack1.isEmpty()) {

			mergedStack.push(stack1.peak());
			stack1.pop();

		}

		while(stack2.isEmpty()) {

			mergedStack.push(stack2.peak());
			stack2.pop();

		}
	}

	//this method reverses a generic stack from highest to lowest value
	//to lowest to highest value
	public static <E> void reverseStack (GenericStack<E> stack,
			GenericStack<E> finalMergedStack) {

		while(stack.isEmpty()) {

			finalMergedStack.push(stack.peak());
			stack.pop();

		}

	}

	//This method takes 2 generic stacks and prints
	//them side by side.  
	public static <E, F> void printTwoStack(GenericStack<E> stack1, 
			GenericStack<F> stack2) {

		int stackPrintCount = 0;
		int stack1Count = 1;
		int stack2Count = 1;

		if(stack1.getSize() > stack2.getSize()) {

			stackPrintCount = stack1.getSize();

		}

		else {

			stackPrintCount = stack2.getSize();

		}

		for(int count = stackPrintCount -1; count >= 0; count--) {

			if(stack1.getSize() - stack1Count >= 0) {

				stack1.printStack(stack1.getSize() - stack1Count);

			}

			else {

				System.out.print("----");

			}
			System.out.print("\t");
			if(stack2.getSize()- stack2Count >= 0) {

				stack2.printStack(stack2.getSize()- stack2Count);

			}

			else {

				System.out.print("----");

			}
			System.out.println("");
			stack1Count++;
			stack2Count++;

		}
	}

}

//This class creates generic stack objects, using an 
//ArrayList.  It should be able to hold any type of
//value in the stack.  Also contains methods that
//would be necessary in a generic stack, such as
//pop(), peek(), and push()
class GenericStack<E> {

	private ArrayList<E> list = new ArrayList<>();

	public GenericStack() {

		list = new ArrayList<>();

	}

	public int getSize() {

		return list.size();

	}

	public boolean isEmpty() {

		if(list.size() == 0) {

			return false;

		}

		else {

			return true;
		}

	}

	public E peak() {
		
		if(list.size() > 0) {

		return list.get(list.size()-1);
		
		}
		
		else {
			
			return list.get(list.size());
			
		}

	}

	public void push(E E) {

		list.add(E);

	}

	public void pop() {
		
		if(list.size() >0) {
		
			list.remove(list.size()-1);
		}
	}

	public void printStack(int index) {

		System.out.print(list.get(index));

	}
}