/*
 * Zach Holst
 * CS1150
 * December 5, 2019
 * Assignment 12
 * The purpose of this assignment is to get to practice using
 * classes and creating a superclass and subclasses to that superclass,
 * as well as polymorphism and inheritance.  Also gives experience
 * in reading from a file to get information for objects rather than
 * hardcoding it in.
 */

//import needed utilities and ios for reading from a text file
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class HolstZachAssigment12 {

	public static void main(String[] args) throws IOException {

		//create constant for how many animals are in the array
		final int NUM_ANIMALS = 5;

		//create a file object
		File inputFileName = new File("assignment12.txt");

		//create a scanner object
		Scanner inputFile = new Scanner (inputFileName);

		//create an array that allocates memory for animal objects
		Animal[] animals = new Animal[NUM_ANIMALS];


		String type = " ";

		//use a for loop to fill the animal array with animal objects
		//read from txt file to construct each object
		//use if else statements to determine which type of animal
		//it is to make the correct type of animal object
		for(int count = 0; count < animals.length; count++) {

			type = inputFile.next();
			String name = inputFile.next();
			double weight = inputFile.nextDouble();
			String food = inputFile.next();
			String location = inputFile.next();



			if(type.equals("Bear")) {

				animals[count] = new Bear(name, weight, food, location);

			}

			else if(type.equals("Wolf")) {

				animals[count] = new Wolf(name, weight, food, location);

			}

			else if(type.equals("Panther")) {

				animals[count] = new Panther(name, weight, food, location);

			}

			else if(type.equals("Penguin")) {

				animals[count] = new Penguin(name, weight, food, location);

			}


		}

		//use a for loop to go through each object in the array and print
		//out needed information
		//also print eat sleep and swim methods for each animal
		//each is different based off the different type of animal
		//use instanceof and if else statements to determine
		//what kind of animal the current object is so that
		//it can detremine which overrides to use when 
		//printing 
		for(int count = 0; count < animals.length; count++) {

			if(animals[count] instanceof Bear) {

				type = "Bear";
			}
			else if(animals[count] instanceof Wolf) {

				type = "Wolf";
			}
			else if(animals[count] instanceof Penguin) {

				type = "Penguin";
			}
			else if(animals[count] instanceof Panther) {

				type = "Panther";
			}


			System.out.println("Animal [" + count + "] is a " + type);
			animals[count].toString();
			animals[count].eat();
			animals[count].sleep();
			animals[count].swim();
			System.out.println("");

		}

	}

}

//create animal class with constructor for animal objects and getters for 
//each part of the object
//data fields are private and only usable within the animal class
//create public methods for eat sleep and swim for the default
//message for each animal
class Animal {

	private String name;
	private double weight;
	private String food;
	private String location;

	public Animal(String name, double weight, String food, String location) {

		this.name = name;
		this.weight = weight;
		this.food = food;
		this.location = location;

	}

	public String getName() {

		return name;

	}

	public double getWeight() {

		return weight;

	}

	public String getFood() {

		return food;

	}

	public String getLocation() {

		return location;

	}

	public void eat() {

		System.out.println("Animal is eating");

	}

	public void sleep() {

		System.out.println("Animal is sleeping - do not disturb");

	}

	public void swim() {

		System.out.println("Animal is swimming");

	}
}

//create class for different types of animals as a subclass of the Animal class
//Make a constructor for the specific type of animal objects, and use the super
//command to access the private data fields from the Animal
//class
//override needed public methods of eat sleep and swim based
//off the animal as well as override the toString method
class Panther extends Animal {

	public Panther(String name, double weight, String food, String location) {

		super(name, weight, food, location);
	}


	@Override
	public void eat() {

		System.out.println("Panther is eating " + getFood());

	}

	@Override
	public void sleep() {

		System.out.println("Panther is sleeping");

	}

	@Override
	public String toString() {

		return "Panther:  name: " + getName() + "weighs: " + getWeight() + "lbs - location: " + getLocation();
	}

}

class Bear extends Animal {

	public Bear(String name, double weight, String food, String location) {

		super(name, weight, food, location);
	}

	@Override
	public void eat() {

		System.out.println("Bear is eating " + getFood());

	}

	@Override
	public void swim() {

		System.out.println("Bear is swimming");

	}

	@Override
	public void sleep() {

		System.out.println("Bear is sleeping");

	}

	@Override
	public String toString() {

		return "Bear:  name: " + getName() + "weighs: " + getWeight() + "lbs - location: " + getLocation();
	}

}

class Penguin extends Animal {

	public Penguin(String name, double weight, String food, String location) {

		super(name, weight, food, location);
	}

	@Override
	public void eat() {

		System.out.println("Penguin is eating " + getFood());

	}

	@Override
	public void swim() {

		System.out.println("Penguin is swimming");		
	}

	@Override
	public String toString() {

		return "Penguin:  name: " + getName() + "weighs: " + getWeight() + "lbs - location: " + getLocation();
	}

}

class Wolf extends Animal {

	public Wolf(String name, double weight, String food, String location) {

		super(name, weight, food, location);
	}

	@Override
	public String toString() {

		return "Wolf:  name: " + getName() + "weighs: " + getWeight() + "lbs - location: " + getLocation();
	}
}