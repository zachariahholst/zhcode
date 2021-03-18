/*
 * Zach Holst
 * CS1450 Sec 001
 * Assignment 8
 * April  16, 2020
 * The purpose of this assignment is to simulate a solved
 * crossword puzzle, using a 2D array, ArrayLists, Queues, and iterators
 * to create the crossword puzzle and fill in the letters to it
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HolstZachAssignment8 {

	public static void main(String[] args) throws FileNotFoundException {
		
		//Create file and scanner objects for all needed files
		File listLetters = new File("listLetters.txt");
		File listSlots = new File("listslots.txt");
		File queueLetters = new File("queueLetters.txt");
		File queueSlots = new File("queueslots.txt");
		
		Scanner listLettersScanner = new Scanner(listLetters);
		Scanner listSlotsScanner = new Scanner(listSlots);
		Scanner queueLettersScanner = new Scanner(queueLetters);
		Scanner queueSlotsScanner = new Scanner(queueSlots);
		
		//create 2 ArrayLists, one to hold letters from the listletters file,
		//another to hold the dimensions for the 2d array from the listslots file
		ArrayList<Character> lettersArray = new ArrayList<>();
		ArrayList<Slot> slotsArray = new ArrayList<>();
		
		//use while.hasNext to read through the array letters file and add all letters
		//into the ArrayList
		while(listLettersScanner.hasNext()) {
			
			lettersArray.add(listLettersScanner.next().charAt(0));
		}
		
		//use while.hasNext to read through the array slots file, creating slot
		//objects with the information on the row and column, and place the object
		//into the ArrayList
		//first two integers in file define size of 2d array for crosswords class, 
		//those are read before the while loop begins
		int rowSize = 0;
		int colSize = 0;
		
		rowSize = listSlotsScanner.nextInt();
		colSize = listSlotsScanner.nextInt();
		
		while(listSlotsScanner.hasNext()) {
			
			Slot slot = new Slot(listSlotsScanner.nextInt(), listSlotsScanner.nextInt());
			slotsArray.add(slot);
			
		}
		
		//create crossword object
		Crossword crossword1 = new Crossword(rowSize, colSize);
		
		//create an iterator for both of the ArrayLists
		Iterator<Character> listLetterIterator = lettersArray.iterator();
		Iterator<Slot> listSlotIterator = slotsArray.iterator();
		
		//call method in crossword class to fill the crossword using the iterators
		crossword1.enterLetters(listLetterIterator, listSlotIterator);
		
		//call method in crossword class to print the crossword
		System.out.print("***************\nCrossword #1\n***************\n");
		crossword1.printCrossword();

		//Repeat all steps, but replace ArrayLists with queues
		Queue<Character> lettersQueue = new LinkedList<>();
		Queue<Slot> slotsQueue = new LinkedList<>();
		
		//use while.hasNext to read through the queue letters file and add all letters
		//into the queue
		while(queueLettersScanner.hasNext()) {
			
			lettersQueue.offer(queueLettersScanner.next().charAt(0));
		}
		
		//use while.hasNext to read through the queue slots file, creating slot
		//objects with the information on the row and column, and place the object
		//into the queue
		//first two integers in file define size of 2d array for crosswords class, 
		//those are read before the while loop begins

		rowSize = queueSlotsScanner.nextInt();
		colSize = queueSlotsScanner.nextInt();
		
		while(queueSlotsScanner.hasNext()) {
			
			Slot slot = new Slot(queueSlotsScanner.nextInt(), queueSlotsScanner.nextInt());
			slotsQueue.offer(slot);
			
		}
		
		//create crossword object
		Crossword crossword2 = new Crossword(rowSize, colSize);

		//create an iterator for both of the Queues
		Iterator<Character> queueLetterIterator = lettersQueue.iterator();
		Iterator<Slot> queueSlotIterator = slotsQueue.iterator();
		
		//call method in crossword class to fill the crossword using the iterators
		crossword2.enterLetters(queueLetterIterator, queueSlotIterator);
		
		//call method in crossword class to print the crossword
		System.out.print("\n\n***************\nCrossword #2\n***************\n");
		crossword2.printCrossword();
		
		
		//close all scanner objects, end program
		listLettersScanner.close();
		listSlotsScanner.close();
		queueLettersScanner.close();
		queueSlotsScanner.close();
	}

}

//this class represents a slot in the crossword puzzle
//used to determine locations in the 2d array of the crossword class
class Slot {
	
	private int row;
	private int column;
	
	public Slot (int row, int column) {
		
		this.row = row;
		this.column = column;
		
	}
	
	public int getRow() {
		
		return row;
	}
	
	public int getColumn() {
		
		return column;
	}
}

//this class represents a crossword, holding a 2d array to be
//filled with characters to create a solved crossword.
//locations in the array without a character are filled with '_'
class Crossword {
	
	private char[][] crossword;
	private int numRows;
	private int numCols;
	private final char EMPTY_CHARACTER = '_';
	
	public Crossword(int numRows, int numCols) {
		
		this.numRows = numRows;
		this.numCols = numCols;
		
		crossword = new char[numRows][numCols];
		
		for(int i = 0; i < numRows; i++) {
			
			for (int j = 0; j < numCols; j++) {
				
				crossword[i][j] = EMPTY_CHARACTER;
			}
		}
	}
	
	//go through bother iterators to find the location to put the letter
	//into the crossword
	public void enterLetters (Iterator<Character> letterIterator, Iterator<Slot>slotIterator) {
		
		char letter = '0';
		int row = 0;
		int column = 0;
		Slot tempSlot = new Slot(row, column);
		
		
		while(letterIterator.hasNext()) {
			
			letter = letterIterator.next();
			tempSlot = slotIterator.next();
			row = tempSlot.getRow();
			column = tempSlot.getColumn();
			crossword[row][column] = letter;
		}
	}
	
	//prints the 2d array
	public void printCrossword() {
		
		for(int i = 0; i < numRows; i++) {
			
			for (int j = 0; j < numCols; j++) {
				
				System.out.print(crossword[i][j] + " ");

			}
			System.out.println("");
		}	
		
	}	
}