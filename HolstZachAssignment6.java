/*
 * Zach Holst
 * CS1150
 * October 10, 2019
 * Assignment 6
 *The purpose of this code is to simulate a self-service bike share station.  
 *This will allow the user to make transactions with the bike station, both 
 *renting and returning bikes and receiving receipts based on their input.
 *The simulation will close when 999 is input and will display final results.
 */
//import scanner
import java.util.Scanner;

public class HolstZachAssignment6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create a new scanner
		Scanner input = new Scanner(System.in);

		//define all constants and variables
		int numBikesLeft = 7;
		int menuOption = 0;
		int numBikesSelected;
		double rentalType = 0;
		double rentalCost = 0;
		double totalSales = 0;
		int totalBikesRented = 0;
		String typePass = "";
		boolean stationInService = true;
		final int SINGLE_TRIP_PASS = 1;
		final int RETURN_BIKE = 3;
		final int singleTripPass = 2;
		final int dayPass = 8;

		//begin while loop for simulation
		while(stationInService) {

			//display a menu for the user with options and prices and display the number of bikes
			//still available.  This menu will display every time the while loop goes through
			System.out.println("********************************************");
			System.out.println("          Welcome to 1150 Bike Share");
			System.out.println("            " + numBikesLeft + " bikes are available");
			System.out.println("********************************************");
			System.out.println(" ");
			System.out.println("Pass Type                Fee");
			System.out.println("--------------------------------------------");
			System.out.println("Single Trip Pass:        $" + singleTripPass);
			System.out.println("24-Hour Pass:            $" + dayPass);
			System.out.println("--------------------------------------------");
			System.out.println("1) Single Trip Pass");
			System.out.println("2) 24-Hour Pass");
			System.out.println("3) Return Bike");
			System.out.println(" ");
			System.out.print("Select menu option 1, 2, or 3: ");

			//prompt the user for an input for the menu options
			menuOption = input.nextInt();

			//create an if statement to determine if 999 has been input to close
			//the simulation and end the while loop
			if(menuOption == 999) {
				stationInService = false;
				System.out.println(" ");
			}
			
			else {

				//create a while loop to deal with invalid inputs for menu options after 
				//it has been determined that 999 has not been entered
				while((menuOption < 1) || (menuOption > 3)) {
					System.out.print("Invalid entry.  Enter 1, 2, or 3: ");

					menuOption = input.nextInt();
				}

				//once a valid menu option has been entered, now a nested if statement is used.
				//the first if statement determines if option 3 was chosen, whose option is to
				//return a bike.  If the number of bikes doesn't equal 7, it will allow 1 bike
				//at a time to be returned, and then the while loop will begin again
				if((menuOption == RETURN_BIKE) && (numBikesLeft != 7)) {

					System.out.println("Bike was succesfully returned");
					System.out.println(" ");
					numBikesLeft = numBikesLeft + 1;
				}

				//if option 3 was chosen, but the number of bikes does equal 7, it will not
				//allow a bike to be returned and will display an error message.  The while
				//loop will begin again after
				else if((menuOption == RETURN_BIKE) && (numBikesLeft == 7)) {
					System.out.println("Station is full.  Please use another station.");
					System.out.println(" ");
				}

				//if option 3 was not chosen, then the program will proceed to prompt the user
				//to enter how many bikes they would like to rent. limit of 5 bikes 
				else {

					System.out.print("How many bikes?  Limit is 5: ");

					numBikesSelected = input.nextInt();

					//another while loop is used to deal with invalid entries for number of bikes
					while((numBikesSelected < 1) || (numBikesSelected > 5)) {
						System.out.print("Invalid Entry.  Enter number between 1 and 5: ");

						numBikesSelected = input.nextInt();
					}

					//an if statement is used in the case of a valid input being entered, but the number
					//of bikes being less than the number of bikes desired to be rented.  An error message
					//will be displayed letting the user know there isn't enough bikes in the station and 
					//that they should use another station. The while loop will begin again
					if((numBikesLeft < numBikesSelected) && ((numBikesSelected >= 1) || (numBikesSelected <= 5))) {

						System.out.println("There are only " + numBikesLeft + " bikes available.");
						System.out.println("Request cannot be fulfilled.  Please use a different station.");
						System.out.println("");
					}

					//if a valid number of bikes was entered and it did not exceed the number of bikes left,
					//the program will proceed
					else {

						//an if statement is used to determine what type of pass is used. this
						//will be stored as a string for the receipt to display it.
						//the if statement will also determine what price to use for 
						//calculating the total cost of the order
						if(menuOption == SINGLE_TRIP_PASS) {
							typePass = "Single Trip Pass";
							rentalType = singleTripPass;
						}
						else {
							typePass = "24-hour Pass";
							rentalType = dayPass;
						}

						//calculate total cost of the rental order
						rentalCost = numBikesSelected * rentalType;

						//subtract the number of bikes selected from the number of bikes left
						numBikesLeft = numBikesLeft - numBikesSelected;

						//create a receipt for the user with information about their purchase
						System.out.println(" ");
						System.out.println("-----------------------------------------");
						System.out.println("----------------Receipt------------------");
						System.out.println("-----------------------------------------");
						System.out.println("    " + typePass + " for " + numBikesSelected + " bikes");
						System.out.println(" ");

						//each purchased bike is given an unlock code. A switch statement is used to determine
						//how many bikes are selected, and therefore how many unlock codes must be displayed
						switch(numBikesSelected) {
						case 1:
							System.out.println("   Unlock code for bike# 1:" + (int)(10000 + Math.random()*90000));
							break;

						case 2:
							System.out.println("   Unlock code for bike# 1:" + (int)(10000 + Math.random()*90000));
							System.out.println("   Unlock code for bike# 2:" + (int)(10000 + Math.random()*90000));
							break;

						case 3:
							System.out.println("   Unlock code for bike# 1:" + (int)(10000 + Math.random()*90000));
							System.out.println("   Unlock code for bike# 2:" + (int)(10000 + Math.random()*90000));
							System.out.println("   Unlock code for bike# 3:" + (int)(10000 + Math.random()*90000));
							break;

						case 4:
							System.out.println("   Unlock code for bike# 1:" + (int)(10000 + Math.random()*90000));
							System.out.println("   Unlock code for bike# 2:" + (int)(10000 + Math.random()*90000));
							System.out.println("   Unlock code for bike# 3:" + (int)(10000 + Math.random()*90000));
							System.out.println("   Unlock code for bike# 4:" + (int)(10000 + Math.random()*90000));
							break;

						case 5:
							System.out.println("   Unlock code for bike# 1:" + (int)(10000 + Math.random()*90000));
							System.out.println("   Unlock code for bike# 2:" + (int)(10000 + Math.random()*90000));
							System.out.println("   Unlock code for bike# 3:" + (int)(10000 + Math.random()*90000));
							System.out.println("   Unlock code for bike# 4:" + (int)(10000 + Math.random()*90000));
							System.out.println("   Unlock code for bike# 5:" + (int)(10000 + Math.random()*90000));
							break;
						}

						System.out.println("");
						System.out.printf("       Rental Cost: $%.2f", rentalCost);
						System.out.println("");
						System.out.println(" ");
						System.out.println("     Thank you for your business!");
						System.out.println("-----------------------------------------");
						System.out.println(" ");

						//edit the variables for the total amount of bikes rented and total sales
						//from the station. these values will be displayed once the station
						//has been closed by entering 999
						totalBikesRented = totalBikesRented + numBikesSelected;
						totalSales = totalSales + rentalCost;

					}
				}
			}
		}

		//if the station was closed by entering 999, display results of the station
		System.out.println("Bike Station was successfully shut down");
		System.out.println("Total Bikes Rented = " + totalBikesRented);
		System.out.printf("Total Sales = $%.2f", totalSales);

		//close the scanner and end program
		input.close();
	}

}
