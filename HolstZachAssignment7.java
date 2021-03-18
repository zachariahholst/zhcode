/*
 * Zach Holst
 * CS1150
 * October 17, 2019
 * Assignment 7
 * The purpose of this code is to create a calorie calculator using different
 * methods throughout the program in order to obtain needed values
 * so the user's bmr can be calculated.
 */
//import scanner
import java.util.Scanner;
public class HolstZachAssignment7 {

	//declare main method
	public static void main(String[] args) {

		//create new scanner input
		Scanner input = new Scanner(System.in);

		//begin user menu
		System.out.println("How many calories do you need?");
		System.out.println("--------------------------------");

		//call displayResults method
		//this method calls the methods getGender, getAge, getHeight, getWeight, and basalMetabolicRate
		displayResults(basalMetabolicRate (getGender(input), getAge(input), getHeight(input), getWeight(input)));

		//after all other methods have been called, method continueProcessing is called
		//this method determines if the user would like to begin again and call the 
		//method displayResults again
		boolean processing = continueProcessing (input);

		//a while loop is used to call the method displayResults for every time
		//the user enters that they wish to begin again
		while (processing) {

			System.out.println("");

			displayResults(basalMetabolicRate (getGender(input), getAge(input), getHeight(input), getWeight(input)));

			processing = continueProcessing (input);

		}

		//display a goodbye message once user is finished
		System.out.println("Have a healthy day!");

	}

	//this method is used to prompt the user to enter their gender.
	//a while loop is used to handle invalid user inputs.
	//once a valid input is given, the value will be returned.
	//possible values are 'M' for Male and 'F' for Female, not case sensitive.
	public static char getGender (Scanner input) {

		final char MALE = 'M';
		final char FEMALE = 'F';

		System.out.print("Enter your gender (M/F): ");

		char userGender = input.next().toUpperCase().charAt(0);

		while((userGender != MALE) && (userGender != FEMALE)) {
			System.out.print(userGender + " is not a valid gender.  Reenter gender (M/F): ");
			userGender = input.next().toUpperCase().charAt(0);
		}
		if(userGender == 'M') {
			return MALE;
		}

		else {
			return FEMALE;
		}

	}

	//this method is used to prompt the user to enter their age.
	//a while loop is used to handle invalid entries.
	//age must be in the range of 1-100 or it is invalid.
	//the user age is returned at the end of the method
	public static double getAge (Scanner input) {

		System.out.print("Enter your age: ");

		double userAge = input.nextDouble();

		while((userAge < 1) || (userAge > 100)) {
			System.out.print(userAge + " is not a valid age.  Reenter your age: ");
			userAge = input.nextDouble();
		}
		return userAge;
	}

	//this method prompts the user to enter their height.
	//height is taken in both feet and inches.
	//feet is converted to inches within the method.
	//a while statement is used to handle invalid entries.
	//the total height entered must be no less than 1 foot and no greater than 9 feet.
	//entering a number less than 0 or greater than 11 is considered invalid for inches.
	//entering a number less than 1 or greater than 9 is considered invalid for feet.
	//the total height is returned at the end of the method, all units converted to inches
	public static double getHeight (Scanner input) {

		final int INCHES_PER_FOOT = 12;

		System.out.print("Enter height in feet: ");

		double heightFeet = input.nextDouble();

		System.out.print("Enter height in inches: ");

		double heightInches = input.nextDouble();

		double totalHeight = heightInches + (heightFeet * INCHES_PER_FOOT);

		while((totalHeight < 12 ) || (totalHeight > 108) || ((heightInches < 0) || (heightInches >= 12)) || ((heightFeet < 1) || (heightFeet > 9))) {
			System.out.println(heightFeet + " feet " + heightInches + " inches is not a valid height.  Reenter your height: ");

			System.out.print("Enter height in feet: ");

			heightFeet = input.nextDouble();

			System.out.print("Enter height in inches: ");

			heightInches = input.nextDouble();

			totalHeight = heightInches + (heightFeet * INCHES_PER_FOOT);

		}
		return totalHeight;
	}

	//this method prompts the user to enter their weight in pounds
	//a while loop is used to handle invalid entries
	//weight must be a positive number or it is invalid
	//0 pounds is also considered invalid
	//user weight is returned at the end of the method
	public static double getWeight (Scanner input) {

		System.out.print("Enter weight in pounds: ");

		double userWeight = input.nextDouble();

		while(userWeight < 1) {

			System.out.print(userWeight + " is not a valid weight.  Reenter your weight: ");

			userWeight = input.nextDouble();
		}

		return userWeight;
	}

	//this method uses the values returned from the previous methods to calculate the user's bmr
	//the Harris Benedict formula is used to calculate the bmr
	//Male bmr = 66 + (6.23 * weight in pounds) + (12.7 * height in inches) - (6.8 * age)
	//female bmr = 66 + (4.35 * weight in pounds) + (4.7 * height in inches) - (4.7 * age)
	//returns bmr at the end of the method
	public static double basalMetabolicRate (char gender, double age, double height, double weight) {

		double userCaloriesNeeded = 0;

		if(gender == 'M') {

			userCaloriesNeeded = 66 + (6.23 * weight) + (12.7 * height) - (6.8 * age);
		}
		else {
			userCaloriesNeeded = 655 + (4.35 * weight) + (4.7 * height) - (4.7 * age);
		}

		return userCaloriesNeeded;
	}

	//this method is used to calculate bmr based on different activity levels
	//a switch statement is used to determine what value to return for bmr
	//based on the activity level
	public static double dailyCaloriesWithActivity (double bmr, int activityLevel) {

		final int SEDENTARY = 1;
		final int LIGHTLY_ACTIVE = 2;
		final int MODERATELY_ACTIVE = 3;
		final int VERY_ACTIVE = 4;
		final int EXTRA_ACTIVE = 5;

		switch (activityLevel) {

		case SEDENTARY:
			bmr = bmr * 1.2;
			return bmr;

		case LIGHTLY_ACTIVE:
			bmr = bmr * 1.375;
			return bmr;

		case MODERATELY_ACTIVE:
			bmr = bmr * 1.55;
			return bmr;

		case VERY_ACTIVE:
			bmr = bmr * 1.725;
			return bmr;

		case EXTRA_ACTIVE:
			bmr = bmr * 1.9;
			return bmr;
		}
		return bmr;

	}

	//this method is void and does not return any results
	//this method displays all of the calculated information from previous methods,
	//such as the bmr based on different activity levels
	public static void displayResults(double bmr) {	

		System.out.println("******************************************");
		System.out.printf("Your BMR is %.2f", bmr);
		System.out.println(" Calories/Day");
		System.out.println("******************************************");
		System.out.println(" ");
		System.out.println("Daily calorie needs based on activity level");
		System.out.println(" ");
		System.out.println("-------------------------------------------------");
		System.out.println("Activity Level                       Calories");
		System.out.println("-------------------------------------------------");
		System.out.printf("Sedentary: little or no exercise    %.2f", dailyCaloriesWithActivity (bmr, 1));
		System.out.println("");
		System.out.printf("Exercise 1-3 times/week             %.2f", dailyCaloriesWithActivity (bmr, 2));
		System.out.println("");
		System.out.printf("Exercise 4-5 times/week             %.2f", dailyCaloriesWithActivity (bmr, 3));
		System.out.println("");
		System.out.printf("Intense exercise 6-7 times/week     %.2f", dailyCaloriesWithActivity (bmr, 4));
		System.out.println("");
		System.out.printf("Very intense daily or physical job  %.2f", dailyCaloriesWithActivity (bmr, 5));
		System.out.println("");
		System.out.println("------------------------------------------------");
		System.out.println("");
		System.out.println("");
	}

	//this method is used to prompt the user to decide if they want to
	//go through the program again and do another calorie check.
	//a while loop is used to handle invalid entries.
	//if yes is entered, the boolean is true and the while loop at 
	//the start of the program will begin and another calorie check
	//will commence.
	//if no is entered, the boolean is false, a goodbye message is
	//displayed, and no more processing happens.
	public static boolean continueProcessing (Scanner input) {

		System.out.print("Perform another calorie check (Y/N)? ");
		char userAnswer = input.next().toUpperCase().charAt(0);


		while((userAnswer != 'Y') && (userAnswer != 'N')) {
			System.out.println(userAnswer + " is not a valid entry - try again");
			System.out.println("");
			System.out.print("Perform another calorie check (Y/N)? ");
			userAnswer = input.next().toUpperCase().charAt(0);

		}

		if(userAnswer == 'Y') {
			return true;
		}

		else {
			return false;
		}
	}
	//end program
}