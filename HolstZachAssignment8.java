/*
 * Zach Holst
 * CS1150
 * November 1, 2019
 * Assignment 8
 * the purpose of this assignment is to learn to create arrays
 * and be able to take values from them to compute and display
 * different results. specifically in this assignment it was
 * to create two arrays to hold city names and amount of rainfall
 * in each city and then to compute certain values based off
 * the values in the arrays
 * The second part of the assignment creates a method that
 * makes dogs bark.  This method gives practice in creating
 * a class and objects, storing the objects in the array
 * to be later displayed in the method
 */
public class HolstZachAssignment8 {

	public static void main(String[] args) {

		//create an array for city names
		String[] cities = new String[7];
		cities[0] = "Denver";
		cities[1] = "Seattle";
		cities[2] = "Orlando";
		cities[3] = "Austin";
		cities[4] = "Vancouver";
		cities[5] = "Chicago";
		cities[6] = "Miami";

		//create an array for rainfall in each city
		double[] rainfall = new double[7];
		rainfall[0] = 15.54;
		rainfall[1] = 37.19;
		rainfall[2] = 53.19;
		rainfall[3] = 34.25;
		rainfall[4] = 46.00;
		rainfall[5] = 35.82;
		rainfall[6] = 61.90;

		//call displayRainfalls method
		displayRainfalls(rainfall, cities);

		//call computeTotalRainfall method to display total rainfall
		System.out.println("Total rainfall for all cities is " + computeTotalRainfall(rainfall));

		//call computeAverageRainfall method to display average rainfall
		System.out.printf("Average rainfall for all cities is %.2f", computeAverageRainfall(rainfall));
		System.out.println("");
		System.out.println("");

		//call findCityWithMostRainfall to determine which city in the array
		//gets the most rainfall and display the results
		System.out.print("City with most rain is " + cities[findCityWithMostRainfall(rainfall)]);
		System.out.println(" with " + rainfall[findCityWithMostRainfall(rainfall)] + " inches per year");
		System.out.println("");

		//call displayCitiesWithAboveAverageRainfall to display results
		//for all cities with an above average rainfall
		displayCitiesWithAboveAverageRainfall(rainfall, cities, computeAverageRainfall(rainfall));
		System.out.println("");
		System.out.println("");

		
		
		//create dogArray, taking information from the created dog class
		Dog[] dogArray = new Dog[5];

		//create 5 dog objects with different information within the object.
		//the first string is dog name and the second string is the dog's bark
		Dog dog1 = new Dog("Rover", "woof woof");
		Dog dog2 = new Dog("Max", "arf arf arf");
		Dog dog3 = new Dog("Tiny", "yap yap yap");
		Dog dog4 = new Dog("Trooper", "ruff ruff ruff");
		Dog dog5 = new Dog("Magoo", "bow wow bow wow");

		//store the dog objects in the array
		dogArray[0] = dog1;
		dogArray[1] = dog2;
		dogArray[2] = dog3;	
		dogArray[3] = dog4;
		dogArray[4] = dog5;

		//call the method to make them bark
		makeDogsBark(dogArray);

	}

	//this method displays the information from the previously created arrays
	//specifically the city names and the amount of rainfall in each city
	public static void displayRainfalls (double[] rainfall, String[] cities) {

		System.out.println("-----------------------------------");
		System.out.println("City                Yearly Rainfall");
		System.out.println("-----------------------------------");
		System.out.println(cities[0] + "              " + rainfall[0]);
		System.out.println(cities[1] + "             " + rainfall[1]);
		System.out.println(cities[2] + "             " + rainfall[2]);
		System.out.println(cities[3] + "              " + rainfall[3]);
		System.out.println(cities[4] + "           " + rainfall[4]);
		System.out.println(cities[5] + "             " + rainfall[5]);
		System.out.println(cities[6] + "               " + rainfall[6]);
		System.out.println("");

	}

	//this method computes the total rainfall of all the cities from the
	//information given in the array
	public static double computeTotalRainfall (double[] rainfall) {

		int rainfallLength = rainfall.length;

		double totalRainfall = 0;

		for (int count = 0; count < rainfallLength; count++) {

			totalRainfall = totalRainfall + rainfall[count];
		}

		return totalRainfall;

	}

	//this method computes average rainfall from all the cities from the
	//information given in the array
	public static double computeAverageRainfall (double[] rainfall) {

		int rainfallLength = rainfall.length;

		return computeTotalRainfall(rainfall) / rainfallLength;
	}

	//this method finds which city gets the largest amount of rain and
	//returns the array index of that city
	public static int findCityWithMostRainfall(double[] rainfall) {

		int rainfallLength = rainfall.length;

		int indexNum = 0;

		double amountRain = 0;

		for (int count = 0; count < rainfallLength; count++) {

			if(rainfall[count] > amountRain) {

				amountRain = rainfall[count];
				indexNum = count;

			}
		}
		return indexNum;
	}

	//this method displays all cities that have a rainfall amount above the average
	public static void displayCitiesWithAboveAverageRainfall(double[] rainfall, String[] cities, double average) {

		int rainfallLength = rainfall.length;

		average = computeAverageRainfall(rainfall);

		for (int count = 0; count < rainfallLength; count++) {

			if(rainfall[count] > average) {

				System.out.println(cities[count] + " gets " + rainfall[count] + " inches which is above average");

			}
		}


	}

	//this method displays the information from the dogArray.  it looks
	//at each array to determine which dog object to display
	public static void makeDogsBark (Dog[] dogArray) {

		System.out.println("create an array of dog objects");
		System.out.println("------------------------------");

		int dogArrayLength = dogArray.length;

		for (int count = 0; count < dogArrayLength; count++) {

			System.out.println(dogArray[count].getName() + " barks like this: " + dogArray[count].getBark());

		}

	}

}//Assignment 8

//create Dog class after class 8 with different methods to create the dog objects
class Dog {
	private String name;
	private String bark;

	public Dog (String name, String bark) {
		this.name = name;
		this.bark = bark;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBark() {
		return bark;
	}

	public void setBark(String bark) {
		this.bark = bark;
	}
} //end Dog class


