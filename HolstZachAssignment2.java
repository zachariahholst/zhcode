/*
 * Zach Holst
 * CS 1450 section 1
 * February 6, 2020
 * The purpose of this assignment is to get better
 * practice working with objects and the concepts
 * of polymorphism and inheritence, as well as putting
 * objects in an array and creating them from a file.
 * 
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HolstZachAssignment2 {

	public static void main(String[] args) throws IOException {
		
		//Create a file object to open the train file for reading
		//create a scanner object to use methods in the scanner
		//class to read from the file
		
		File inputFileName = new File("trains.txt");
		
		Scanner inputFile = new Scanner(inputFileName);
		
		//create an array to hold the trains, using the first value
		//in the file as the array's size
		
		Train[] trains = new Train[inputFile.nextInt()];
		
		//Go through each line of the file, reading the type,
		//top speed, and name.  Using if else statements, 
		//it will determine which type has been read and which 
		//object to create.  the objects will be put in the trains
		//array previously created
		
		String type = "";
		String name = "";
		double topSpeed = 0;
		
		for(int count = 0; count < trains.length; count++) {
			
			type = inputFile.next();
			topSpeed = inputFile.nextDouble();
			name = inputFile.nextLine();
			
			if(type.equals("highspeed")) {
				
				trains[count] = new Highspeed(name, topSpeed);
				
			}
			
			else if(type.equals("monorail")) {
				
				trains[count] = new Monorail(name, topSpeed);
				
			}
			
			else if(type.equals("lightrail")) {
				
				trains[count] = new Lightrail(name, topSpeed);
				
			}
			
			else if(type.equals("cog")) {
				
				trains[count] = new CogTrain(name, topSpeed);
				
			}
			
		}
		
		//now that all objects have been created, it will iterate
		//through the trains array and display all information, 
		//including the benefits, from each object and subclass
		//to the console
		
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.printf("%-15s", "Type");
		System.out.printf("%-22s", "Name");
		System.out.printf("%-14s", "Top Speed");
		System.out.printf("%-40s", "Benefit" );
		System.out.println("");
		System.out.println("-------------------------------------------------------------------------------------------");

		for(int count = 0; count < trains.length; count++) {
			
			System.out.printf("%-14s", trains[count].getType());
			System.out.printf("%-23s", trains[count].getName());
			System.out.printf("%-14s", trains[count].getTopSpeed());
			System.out.printf("%1s", trains[count].benefit() );
			System.out.println("");
			
		}
		
		inputFile.close();

	}

} //end main

//Create a Train class to be the super class.  It will have 3 private
//data fields, a string for the type, a string for the name, and a 
//double for the speed of the train.  Then, 4 subclasses of train, 
//each will override the benefits() method of the Train superclass
//to change what they display

class Train {
	
	private String type;
	private String name;
	private double topSpeed;
	
	public Train(String type, String name, double topSpeed) {
		
		this.type = type;
		this.name = name;
		this.topSpeed = topSpeed;
		
	}
	
	public String getType() {
		
		return type;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public double getTopSpeed() {
		
		return topSpeed;
		
	}
	
	public String benefit() {
		
		return "benefit";
		
	}
}

class Highspeed extends Train {
	
	public Highspeed(String name, double topSpeed) {
		
		super("highspeed", name, topSpeed);
		
	}
	
	@Override
	public String benefit() {
		
		return "Travels at speeds between 125 and 267 mph";
		
	}
	
	
}

class Monorail extends Train {
	
	public Monorail(String name, double topSpeed) {
		
		super("monorail", name, topSpeed);
		
	}
	
	@Override
	public String benefit() {
		
		return "minimal footprint and quieter";
		
	}
	
	
}

class Lightrail extends Train {
	
	public Lightrail(String name, double topSpeed) {
		
		
		super("lightrail", name, topSpeed);
		
	}
	
	@Override
	public String benefit() {
		
		return "Tighter turning radius";
		
	}
	
	
}

class CogTrain extends Train {
	
	
	public CogTrain(String name, double topSpeed) {
		
		super("cog", name, topSpeed);
		
	}
	
	@Override
	public String benefit() {
		
		return "Can climb grades up to 48%";
		
	}
	
}

