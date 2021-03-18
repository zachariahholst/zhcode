/*
 * Zach Holst
 * CS 1450 Sec 001
 * Assignment 4
 * February 20, 2020
 * This assigment simulates a pinball machine, with Targets
 * and a playing field.  It makes use of a 2D array in the
 * playing field to create a field of rows and columns.
 * A method is used to read from a file and simulate a game
 * based off of the field created with a different file.
 * A report of the hits that were on each Target is printed.
 * The Comparable method is implemented in the class used
 * to print the report.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HolstZachAssignment4 {

	public static void main(String[] args) throws IOException {

		//Create file object
		File inputFileName = new File("PinballMachineTargets.txt");

		//create scanner for first file
		Scanner inputFile = new Scanner(inputFileName);

		//create a pinball machine object
		PinballMachine pinballMachine = new PinballMachine(inputFile.nextInt(), inputFile.nextInt());

		String type = "";
		int id = 0;
		int points = 0;
		int row = 0;
		int column = 0;

		//iterate through the file, creating new targets based
		//off of the information from the file
		//use hasNext from scanner method to determine if there is another
		//target for the scanner to read
		//add all targets to the playing field in the pinballMachine class
		while(inputFile.hasNext()) {

			row = inputFile.nextInt();
			column = inputFile.nextInt();
			type = inputFile.next();
			id = inputFile.nextInt();
			points = inputFile.nextInt();

			Target target = new Target(type, id, points);

			pinballMachine.addTargetToPlayingField(row, column, target);

		}

		//call method to display field in pinballMachine
		pinballMachine.displayPlayingField();

		//call play method
		play(pinballMachine);

		//call print report
		printReport(pinballMachine);

		//close scanner 
		inputFile.close();
	}

	//This method creates a new file and scanner to read from a file that
	//simulates a pinball game.  It determines if the ball has hit a target,
	//and if it has, records and prints which target it was, as well as how many
	//points it was worth to add to the overall score.
	public static void play(PinballMachine pinballMachine) throws IOException {

		File gameFile = new File("Play.txt");

		Scanner gameScanner = new Scanner(gameFile);

		int score = 0;
		int row = 0;
		int column = 0;

		System.out.println("");
		System.out.println("");
		System.out.println("-----------------------------------------");
		System.out.println("\tSimulate Pinball Game");
		System.out.println("-----------------------------------------");
		System.out.printf("%-14s%-6s%-12s%-6s","Target Hit", "ID", "Points", "Score");
		System.out.println("");
		System.out.println("-----------------------------------------");

		while(gameScanner.hasNext()) {

			row = gameScanner.nextInt();
			column = gameScanner.nextInt();

			if(pinballMachine.getTarget(row, column) != null) {

				pinballMachine.getTarget(row, column).incrementHits();

				score += pinballMachine.getTarget(row, column).getPoints();

				System.out.printf("%-14s%-6s%-12s%-6s", pinballMachine.getTarget(row, column).getType(), 
						pinballMachine.getTarget(row, column).getID(),
						pinballMachine.getTarget(row, column).getPoints(), score);
				System.out.println("");
			}
		}

		gameScanner.close();

	}

	//This method prints a report of the pinball game from the preivous method,
	//creating TargetReport objects added to an ArrayList, sorted with the
	//Collections.sort() method, and printing the report of all of the 
	//targets and how many times they have been hit.
	public static void printReport (PinballMachine pinballMachine) {

		int rowCount = 0;
		int columnCount = 0;

		ArrayList<TargetReport>targetReportArrayList = new ArrayList<>();

		while(rowCount < pinballMachine.getNumberRows()) {

			columnCount = 0;

			while(columnCount < pinballMachine.getNumberColumns()) {

				if(pinballMachine.getTarget(rowCount, columnCount) != null) {

					TargetReport targetReport = new TargetReport(rowCount, columnCount, 
							pinballMachine.getTarget(rowCount, columnCount).getType(),
							pinballMachine.getTarget(rowCount, columnCount).getID(),
							pinballMachine.getTarget(rowCount, columnCount).getPoints(),
							pinballMachine.getTarget(rowCount, columnCount).getHits());

					targetReportArrayList.add(targetReport);
					

				}	
				columnCount++;
			}

			rowCount++;
		}

		Collections.sort(targetReportArrayList);


		System.out.println("");
		System.out.println("");
		System.out.println("*****************************************************************");
		System.out.println("\t\tPINBALL MACHINE TARGET HIT REPORT");
		System.out.println("\t\t(From Most Hits to Least Hits");
		System.out.println("*****************************************************************");
		System.out.printf("%s\t%s\t%-10s\t%-1s\t%-4s\t%-4s", "Row", "Column",  "Type", "Number", "Points", "Number Hits");
		System.out.println("");
		System.out.println("-----------------------------------------------------------------");

		for(int count = 0; count < targetReportArrayList.size(); count++) {

			System.out.println(targetReportArrayList.get(count).print());


		}
	}
}

//This class creates pinballObjects
//it has a 2D array to simulate a playing field of the pinball game
//Target objects must be added to the playing field
class PinballMachine {

	private int numberRows;
	private int numberColumns;
	private Target[][] playingField;

	public PinballMachine(int numberRows, int numberColumns) {

		this.numberRows = numberRows;
		this.numberColumns = numberColumns;

		playingField = new Target[numberRows][numberColumns];

	}

	public int getNumberRows() {

		return numberRows;

	}

	public int getNumberColumns() {

		return numberColumns;

	}

	public void addTargetToPlayingField(int row, int column, Target target) {

		playingField[row][column] = target;

	}

	public Target getTarget (int row, int column) {

		if(playingField[row][column] == null) {

			return null;

		}

		else {

			return playingField[row][column];	

		}

	}

	public void displayPlayingField() {

		int columnCount = 0;
		int rowCount = 0;

		System.out.print("      ");
		while(columnCount < getNumberColumns()) {

			System.out.printf("%-12s","Column " + columnCount);

			columnCount++;

		}

		System.out.println("");
		System.out.println("");

		while(rowCount < getNumberRows()) {

			columnCount = 0;

			System.out.printf("%-6s","Row " + rowCount);

			while(columnCount < getNumberColumns()) {

				if(getTarget(rowCount, columnCount) == null) {

					System.out.printf("%-12s","-------");

				}

				else {

					System.out.printf("%-12s",getTarget(rowCount, columnCount).getType());

				}

				columnCount++;

			}

			rowCount++;
			System.out.println("");

		}
	}
}

//This class creates Target objects
//These are the objects that get added to the playing field in the
//PinballMachine class
class Target {

	private String type;
	private int id;
	private int points;
	private int hits;

	public Target(String type, int id, int points) {

		this.type = type;
		this.id = id;
		this.points = points;

	}

	public String getType() {

		return type;

	}

	public int getID() {

		return id;

	}

	public int getPoints() {

		return points;

	}

	public int getHits() {

		return hits;

	}

	public void incrementHits() {

		hits++;

	}
}

//This class creates TargetReport objects
//it must implement the Comparable java method
//must have a compareTo method
class TargetReport implements Comparable <TargetReport> {

	private int rowNumber;
	private int columnNumber;
	private String type;
	private int id;
	private int points;
	private int hits;

	public TargetReport(int rowNumber, int columnNumber, String type, int id, 
			int points, int hits) {		
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
		this.type = type;
		this.id = id;
		this.points = points;
		this.hits = hits;
		
		
	}

	public String print() {

		return String.format("%d\t%d\t%-10s\t%-1d\t%-4d\t%-4d", 
				rowNumber, 
				columnNumber, 
				type,
				id,
				points,
				hits);

	}

	@Override
	public int compareTo(TargetReport otherReport) {	
		if(otherReport.hits > this.hits) {
			return 1;
		}
		else if(otherReport.hits < this.hits) {
			return -1;
		}
		else {
			return 0;
		}
	}
}
