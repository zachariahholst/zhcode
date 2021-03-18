/*
 * Zach Holst
 * CS1150
 * November 21, 2019
 * Assignment 11
 * The purpose of this assignment is to get more practice with
 * classes and objects, now putting a class within a class.
 * the program will simulate a race, with race car objects
 * and an odometer object within each race car object. 
 * methods within the class will be used to manipulate the objects
 * in order to display certain information about each object.
 * this assignment also introduces writing to a text file
 * from within the clas, writing the information about each
 * car object to a text file
 */
//import io packages to for the write to text file method
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class HolstZachAssignment11 {

	public static void main(String[] args) throws IOException {

		//define constants
		final int NUM_RACE_CARS = 5;
		final int RACE_END = 500;

		//create raceCars array
		RaceCar[] raceCars = new RaceCar[NUM_RACE_CARS];

		//call method to create race car objects and give them
		//their driver, driver number, and average speed
		setUpRaceCars(raceCars);

		//print out information of the car objects at the beginning of the race
		//miles driven in the odometer should be zero
		//call method to print out objects
		System.out.println("Race cars at start of race");
		System.out.println("------------------------------------------------------");
		System.out.println("Car            Driver            Average      Miles");
		System.out.println("Number                           Speed        Completed");
		System.out.println("------------------------------------------------------");

		printRaceCars(raceCars);

		//begin the race
		//a while loop is used that will continue to run until
		//a car has reached 500 miles.  Every 4 increments the miles
		//each driver has driven will be updated based on their 
		//average speed
		//after each increment the total time the race has been
		//going on will print
		//methods within the odometer class will be used to update the miles
		//for each driver
		//once a driver has reached 500 miles the program will break out of the
		//while loop and the race will end
		System.out.println("");
		System.out.println("Allcars are ready - let the race begin in 5..4..3..2..1");

		boolean keepRacing = true;	
		int timeIterations = 1;
		double hours = 0.25;

		while(keepRacing) {


			System.out.println("Racing time ... " + hours + " hours");

			if(timeIterations %4 == 0) {

				for(int count = 0; count < raceCars.length; count++) {

					raceCars[count].updateOdometer();

					if(raceCars[count].getOdometer().getMiles() >= RACE_END) {

						keepRacing = false;

					}

				}

			}

			timeIterations++;
			hours = hours + 0.25;

		}

		//print all information about the car objects again this time
		//with how many miles they drove, based on the info from
		//the while loop and updated odometer
		System.out.println("");
		System.out.println("Race cars at end of race");
		System.out.println("------------------------------------------------------");
		System.out.println("Car            Driver            Average      Miles");
		System.out.println("Number                           Speed        Completed");
		System.out.println("------------------------------------------------------");

		printRaceCars(raceCars);

		//write details about each driver to a text file
		writeCarDetailsToFile(raceCars);

	}

	//this method sets up the race cars array by creating
	//new race car objects and giving them their
	//driver, driver number, and average speed
	public static void setUpRaceCars(RaceCar[] raceCars) {

		raceCars[0] = new RaceCar("Shrek", 8, 67);
		raceCars[1] = new RaceCar("Fiona", 11, 85);
		raceCars[2] = new RaceCar("Donkey", 7, 118);
		raceCars[3] = new RaceCar("Farquaad", 24, 71);
		raceCars[4] = new RaceCar("Dragon", 43, 108);

	}

	//print information from a race car array
	//printing the driver number, driver name, average speed, and
	//amount of miles on the odometer
	public static void printRaceCars(RaceCar[] raceCars) {

		for(int count = 0; count < raceCars.length; count++) {

			System.out.printf("%-15d%-18s%-13.2f%-1.2f", raceCars[count].getNumber(), raceCars[count].getDriver(), raceCars[count].getSpeed(), raceCars[count].getOdometer().getMiles());
			System.out.println("");
		}

	}

	//prints the details about each race car object within the 
	//race cars array to a text file 
	public static void writeCarDetailsToFile(RaceCar[] raceCars) throws IOException {

		File fileName = new File("Assignment11.txt");

		PrintWriter resultsFile = new PrintWriter (fileName);

		resultsFile.println("Race Car Details");
		for (int count = 0; count < raceCars.length; count++) {
			resultsFile.println(raceCars[count].getNumber());
			resultsFile.println(raceCars[count].getDriver());
			resultsFile.println(raceCars[count].getOdometer().getMiles());
			resultsFile.println();
		}
		resultsFile.close();
	}


}

//creates a race car class
//constructs race car objects with a driver,
//speed, and driver number
//an odometer object is created within the class
class RaceCar {

	private String driver;
	private double speed;
	private int number ;
	private Odometer odometer;

	public RaceCar(String driver, int number, int speed) {
		this.driver = driver;
		this.number = number;
		this.speed = speed;

		//creates a new odometer object
		odometer = new Odometer();

	}

	public String getDriver() {

		return driver;

	}

	public int getNumber() {

		return number;

	}

	public double getSpeed() {

		return speed;

	}

	public Odometer getOdometer() {

		return odometer;

	}

	public void updateOdometer() {

		odometer.incrementMiles(speed);

	}
}

//Creates an Odometer class to hold information
//within an odometer object
//has method to return miles within the odometer
//object as well as increment it based on the
//speed in the race car object
class Odometer {

	private double miles;

	public Odometer() {

		this.miles = 0;

	}

	public double getMiles() {

		return miles;

	}

	public void incrementMiles(double speed) {

		miles = miles + speed;


	}

}

